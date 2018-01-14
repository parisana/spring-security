package com.demo.spring_security.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Parisana
 */
@Getter
@Setter
@ToString
public class MessageDTO {
    @NotEmpty(message = "Message is required.")
    private String message;

    private String summary;

    @NotEmpty(message = "Email is required.")
    @Email
    private String toEmail;
}
