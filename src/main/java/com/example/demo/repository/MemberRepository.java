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

    //Optional<About> findByInfo(String id);

    void delete(About about);

    void update(About about);

    // 이젠 userpw가 PK가 아님 위에 메소드에서 userpw도 수정할 수 있음 (일단 남겨둠 해당 메소드 삭제 예정)_2023.10.24_표세빈
    void updatePassword(About about);

}
