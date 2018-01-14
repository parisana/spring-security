package com.demo.spring_security.controller;

import com.demo.spring_security.domain.Message;
import com.demo.spring_security.DTO.MessageDTO;
import com.demo.spring_security.domain.User;
import com.demo.spring_security.repositories.MessageRepo;
import com.demo.spring_security.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

@Slf4j
@Controller
public class MessageController {
	private final MessageRepo messageRepo;
	private final UserRepo userRepo;

	public MessageController(MessageRepo messageRepo, UserRepo userRepo) {
		this.messageRepo = messageRepo;
		this.userRepo = userRepo;
	}
	@RequestMapping(value = "messages")
	public String redirectToInbox(){
		return "redirect:messages/inbox";
	}

	@RequestMapping(value = "messages/inbox")
	public String getMessages(@AuthenticationPrincipal UserDetails currentUser, Model model){
		log.debug(currentUser.getUsername());
		log.debug("***getting inbox messages for currentUser: "+ currentUser.getName());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("messages", messageRepo.inbox());

		return "messages/inbox";
	}
	@GetMapping(value = "messages/sent")
	public String sentMessages(@AuthenticationPrincipal UserDetails currentUser, Model model){
		log.debug("***getting sent messages");
		log.debug("***email of to: "+ messageRepo.sent());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("messages", messageRepo.sent());

		return "messages/sent";
	}
//	@GetMapping(value = "messages/compose", params="form")
//	public String createForm(@ModelAttribute Message message, @AuthenticationPrincipal UserDetails currentUser, Model model) {
	@GetMapping(value = "messages/compose")
	public String createForm(@AuthenticationPrincipal UserDetails currentUser, Model model) {
		log.debug("***Getting create message form page");
		model.addAttribute("currentUser", currentUser);
		if (model.containsAttribute("messageDTO"))
			model.addAttribute("messageDTO", new MessageDTO());
		return "messages/compose";
	}

	@PostMapping(value = "messages/compose")
	public String create(@AuthenticationPrincipal UserDetails currentUserDetails, @Valid MessageDTO messageDTO, BindingResult result, RedirectAttributes redirect) {
		log.debug("***Submitting created message");
		Optional<User> optionalUserTo = userRepo.findByEmail(messageDTO.getToEmail());
		if (!optionalUserTo.isPresent()){
			log.debug("***User to whom the message is to be sent is not found!"+messageDTO.getToEmail());
			result.rejectValue("toEmail", "toEmail", "User not found");
		}
		if (result.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.message", result);
			redirect.addFlashAttribute("messageDTO", messageDTO);
			return "redirect:/messages/compose";
		}

		Optional<User> optionalUserFrom = userRepo.findByEmail(currentUserDetails.getUsername());
		if (!optionalUserFrom.isPresent()){
			// todo throw an exception or pass an error message using redirect
			log.debug("###Current user not found, something went wrong horribly!");
			return "redirect:/messages/compose";
		}

		Message message= new Message();
		message.setFrom(optionalUserFrom.get());
		message.setTo(optionalUserTo.get());
		message.setCreated(Calendar.getInstance());
		message.setSummary(messageDTO.getSummary());
		message.setText(messageDTO.getMessage());

		Message savedMessage = messageRepo.save(message);
		redirect.addFlashAttribute("globalMessage", "Message added successfully");

//		redirect.addFlashAttribute("message.id", message.getId());
		return "redirect:/messages/show/"+message.getId();
	}
	@RequestMapping(value = "messages/show/{id}", method=RequestMethod.GET)
	public String view(@PathVariable("id") Long messageId, @AuthenticationPrincipal UserDetails currentUser, Model model) {
		log.debug("***Showing message with id: "+ messageId);
		log.debug("***Showing message with id: "+ currentUser.getUsername());
		log.debug("***Showing message with message: "+ messageRepo.findById(messageId).get());
		model.addAttribute("message", messageRepo.findById(messageId).get());
		model.addAttribute("currentUser", currentUser);
		return "messages/show";
	}

	@RequestMapping(value = "messages/{id}")
	public String delete(@PathVariable("id")Long id, RedirectAttributes redirect) {
		log.debug("***Deleting message with id: " + id);
		messageRepo.deleteById(id);
		redirect.addFlashAttribute("globalMessage", "Message removed successfully");
		return "redirect:/messages/inbox";
	}
}
