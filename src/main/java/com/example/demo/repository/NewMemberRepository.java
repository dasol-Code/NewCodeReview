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
        return em.createQuery("select m from About m where m.usedyn = :usedyn", About.class)
                .setParameter("usedyn","Y")
                .getResultList();
    }

    public void delete(About about){
        em.createQuery("update About a set a.usedyn = :usedyn,a.lastupduserid = :lastupduserid where a.userid = :userid")
                .setParameter("usedyn","N")
                .setParameter("lastupduserid", about.getUserid())
                .setParameter("userid", about.getUserid())
                .executeUpdate();
        em.clear();
    }
    @Modifying
    public void update(About about){
        em.createQuery("update About a set a.usernm = :usernm, a.useremail = :useremail, a.userpw = :userpw, a.lastupduserid = :lastupduserid where a.userid = :userid")
                .setParameter("usernm", about.getUsernm())
                .setParameter("useremail", about.getUseremail())
                .setParameter("userpw", about.getUserpw())
                .setParameter("lastupduserid", about.getUserid())
                .setParameter("userid", about.getUserid())
                .executeUpdate();
        em.clear();
    }
}
