package com.example.demo.repository;

import com.example.demo.domain.About;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    About save(About about);
    Optional<About> findById(String userid);

    About findLogin(String userid);
    List<About> findAll();

    void delete(About about);

    void update(About about);

}
