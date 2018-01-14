package com.demo.spring_security.repositories;

import com.demo.spring_security.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Parisana
 */
public interface MessageRepo extends JpaRepository<Message, Long>{
    @Query("select m from Message m where m.to.id = ?#{principal.id}")
    Iterable<Message> inbox();

//    ?#{} is a SpEL expression and the expression evaluates to Authentication.getPrincipal().getId()
    @Query("select m from Message m where m.from.id = ?#{principal.id}")
    Iterable<Message> sent();

//    Message findOne(@Param("id") Long id);

    Message findBySummary(@Param("summary") String summary);

    <S extends Message> S save(S message);

}
