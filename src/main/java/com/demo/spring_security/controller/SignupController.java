package com.demo.spring_security.controller;

import com.demo.spring_security.config.CustomUserDetails;
import com.demo.spring_security.domain.User;
import com.demo.spring_security.domain.UserAuthority;
import com.demo.spring_security.repositories.UserAuthorityRepo;
import com.demo.spring_security.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class SignupController{
	private final UserRepo userRepo;
	private final UserAuthorityRepo userAuthorityRepo;

	SignupController(UserRepo userRepo, UserAuthorityRepo userAuthorityRepo) {
		this.userRepo = userRepo;
        this.userAuthorityRepo = userAuthorityRepo;
	}

	@GetMapping("/signup")
	public String signupForm(Model model){
		log.debug("***Getting signup Form");

		if (!model.containsAttribute("user"))
			model.addAttribute("user", new User());
		return "user/signup";
	}
	@PostMapping("/signup")
	public String signup(@Valid User user, BindingResult result,
						 RedirectAttributes redirect) {
		if (result.hasErrors()) {
			// redirect to getMapping with the errors and the user, so that form field is still populated. Using redirect clears the fields on refresh.
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			redirect.addFlashAttribute("user", user);
			return "redirect:/signup";
		}

		user = userRepo.save(user);

		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setUser(user);
		userAuthority.setAuthority("ROLE_USER");

		Set<UserAuthority> set= new HashSet<>();
		set.add(userAuthority);
		user.setAuthorities(set);

		userAuthorityRepo.save(userAuthority);

		UserDetails userDetails= new CustomUserDetails(user);


		redirect.addFlashAttribute("globalMessage", "Successfully signed up");

		List<GrantedAuthority> authorities =
				AuthorityUtils.createAuthorityList("ROLE_USER");
		// user-details is required to set up the principal ##Null AuthenticationPrincipal otherwise.
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(),authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
		log.debug(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return "redirect:/messages/inbox";
	}
}
