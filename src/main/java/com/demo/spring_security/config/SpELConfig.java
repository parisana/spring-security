package com.demo.spring_security.config;

/**
 * @author Parisana
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

/**
 * This bean is required for SpEL expression evaluations
 * used in repositories
 * */
@Configuration
public class SpELConfig {
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
