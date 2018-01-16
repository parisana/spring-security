package com.demo.spring_security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * @author Parisana
 */
@Data
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Message is required")
    private String text;

    private String summary;

    @Version
    private Calendar created = Calendar.getInstance();

    @OneToOne
    @NotNull
    private User to= new User();

    @OneToOne
    @NotNull
    private User from= new User();

    public Message(@NotEmpty(message = "Message is required") String text, String summary,@NotNull User to, @NotNull User from) {
        this.text = text;
        this.summary = summary;
        this.to = to;
        this.from = from;
    }
}
