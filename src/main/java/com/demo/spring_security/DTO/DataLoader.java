package com.demo.spring_security.DTO;

import com.demo.spring_security.domain.AuthorityRole;
import com.demo.spring_security.domain.Message;
import com.demo.spring_security.domain.User;
import com.demo.spring_security.domain.UserAuthority;
import com.demo.spring_security.repositories.MessageRepo;
import com.demo.spring_security.repositories.UserAuthorityRepo;
import com.demo.spring_security.repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Parisana
 */

@Component
public class DataLoader implements CommandLineRunner {
	private final UserRepo userRepo;
	private final MessageRepo messageRepo;
	private final UserAuthorityRepo userAuthorityRepo;

    public DataLoader(UserRepo userRepo, MessageRepo messageRepo, UserAuthorityRepo userAuthorityRepo) {
		this.userRepo = userRepo;
		this.messageRepo = messageRepo;
        this.userAuthorityRepo = userAuthorityRepo;
    }
	@Override
	public void run(String... args) throws Exception {

		User rob= new User("Rob", "Winch", "rob@a.com", "password");
		UserAuthority robAuthority= new UserAuthority(rob, "ROLE_USER");
		rob.setAuthorities(Stream.of(robAuthority).collect(Collectors.toSet()));

		User joe= new User("Joe", "Grandja", "joe@a.com", "password");
        UserAuthority joeAuthority= new UserAuthority(joe, "ROLE_USER");
        joe.setAuthorities(Stream.of(joeAuthority).collect(Collectors.toSet()));

		User pari= new User("Pari", "Ngangom", "pari@a.com", "password");
        UserAuthority pariAuthority= new UserAuthority(pari, "ROLE_USER");
        pari.setAuthorities(Stream.of(pariAuthority).collect(Collectors.toSet()));

		User a= new User("Apple", "Messaging", "a@a.com", "password");
        UserAuthority aAuthority= new UserAuthority(a, "ROLE_USER");
        a.setAuthorities(Stream.of(aAuthority).collect(Collectors.toSet()));

		User admin= new User("Admin", "Messaging", "admin@a.com", "password");
        UserAuthority adminAuthority= new UserAuthority(admin, "ROLE_USER,ROLE_ADMIN");
        admin.setAuthorities(Stream.of(adminAuthority).collect(Collectors.toSet()));

        List<User> userList= Stream.of(rob, joe, pari, a, admin).collect(Collectors.toList());
        userRepo.saveAll(userList);
        userAuthorityRepo.saveAll(Stream.of(robAuthority, joeAuthority, pariAuthority, aAuthority, adminAuthority).collect(Collectors.toSet()));

        List<Message> messageList= Stream.of(new Message("Hello Joe", "This message is for Joe", joe, pari),
                new Message("Hello Joe", "This message is for Joe from Pari", joe, pari),
                new Message("Hello Joe", "This message is for Joe from rob", joe, rob),
                new Message("Hello Joe", "This message is for Joe from a", joe, a),
                new Message("Hello Joe", "This message is for Joe from admin", joe, admin),

                new Message("Hello Pari", "This message is for Pari from rob", pari, rob),
                new Message("Hello Pari", "This message is for Pari from Joe", pari, joe),
                new Message("Hello Pari", "This message is for Pari from Apple", pari, a),
                new Message("Hello Pari", "This message is for Pari from admin", pari, admin),

                new Message("Hello Rob", "This message is for Rob from pari", rob, pari),
                new Message("Hello Rob", "This message is for Rob from joe", rob, joe),
                new Message("Hello Rob", "This message is for Rob from a", rob, a),
                new Message("Hello Rob", "This message is for Rob from admin", rob, admin),

                new Message("Hello Apple", "This message is for Apple from pari", a, pari),
                new Message("Hello Apple", "This message is for Apple from admin", a, admin),
                new Message("Hello Admin", "This message is for Admin from Pari", admin, pari)
                ).collect(Collectors.toList());
        messageRepo.saveAll(messageList);
	}
}
