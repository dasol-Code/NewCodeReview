package com.example.demo.repository;

import com.example.demo.domain.About;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Slf4j
public class NewMemberRepository implements MemberRepository {

    private final EntityManager em;

    public NewMemberRepository(EntityManager em){
        this.em = em;
    }

    public About save(About about){
        em.persist(about);
        return about;
    }
    public Optional<About> findById(String userid){
        About about = em.find(About.class, userid);
        return Optional.ofNullable(about);
    }

    public About findLogin (String userid){
        return em.find(About.class, userid);
    }
    public List<About> findAll(){
        return em.createQuery("select m from About m", About.class).getResultList();
    }

    public void delete(About about){
        em.createQuery("delete from About a where a.ser = :ser and a.userid = :userid")
                .setParameter("ser", about.getSer())
                .setParameter("userid", about.getUserid())
                        .executeUpdate();
        em.clear();
    }
    @Modifying
    public void update(About about){
        em.createQuery("update About a set a.usernm = :usernm, a.useremail = :useremail, a.userpw = :userpw, a.lastupduserid = :lastupduserid where a.ser = :ser and a.userid = :userid")
                .setParameter("usernm", about.getUsernm())
                .setParameter("useremail", about.getUseremail())
                .setParameter("userpw", about.getChgpassword())
                // userid로 최종수정자도 UPDATE ..?
                .setParameter("lastupduserid", about.getUserid())

                .setParameter("ser", about.getSer())
                .setParameter("userid", about.getUserid())
                .executeUpdate();
        em.clear();
    }
    
    // 이젠 userpw가 PK가 아님 위에 메소드에서 userpw도 수정할 수 있음 (일단 남겨둠 해당 메소드 삭제 예정)_2023.10.24_표세빈
    public void updatePassword(About about){
        em.createQuery("update About a set a.userpw = :chgpassword where a.ser = :ser and a.userid = :userid")
                .setParameter("chgpassword", about.getChgpassword())
                .setParameter("userid", about.getUserid())
                .executeUpdate();
        em.clear();
    }
    //import check File
}
