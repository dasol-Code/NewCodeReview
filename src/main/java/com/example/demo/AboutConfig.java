package com.example.demo;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.NewMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class AboutConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    @Autowired
    public AboutConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public MemberRepository memberRepository() {
        return new NewMemberRepository(em);
    }
}
