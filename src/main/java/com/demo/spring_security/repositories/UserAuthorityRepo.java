package com.demo.spring_security.repositories;

import com.demo.spring_security.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Parisana
 */
public interface UserAuthorityRepo extends JpaRepository<UserAuthority, Long> {
}
