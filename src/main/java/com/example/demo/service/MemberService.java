package com.example.demo.service;

import com.example.demo.domain.About;
import com.example.demo.repository.MemberRepository;
import com.example.demo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Transactional
@Service
public class MemberService {

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expireTime = 1000 * 60 * 60L; // 1시간

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String join(About about){
        validateDuplicateMember(about);
        memberRepository.save(about);
        return about.getUserid();
    }

    public List<About> findAll(){
        return memberRepository.findAll();
    }
    public Map<String,Object> login(@NotNull About about) throws Exception{
        Map<String,Object> result = new HashMap<>();
        About findUser = memberRepository.findLogin(about.getUserid());
        if(findUser == null){
            throw new IllegalStateException("해당 아이디가 존재하지 않습니다.");
        }
        if(!findUser.getUserpw().equals(about.getUserpw())){
            throw new IllegalStateException("패스워드가 맞지 않습니다..");
        }
        if("N".equals(findUser.getUsedyn())){
            throw new IllegalStateException("삭제된 회원입니다.");
        }
        result.put("token",JwtUtil.createJwt(about.getUsernm(), secretKey, expireTime));
        result.put("userId",findUser.getUserid());
        result.put("userRight", findUser.getManageyn());
        return result;
    }

    public About updateMember(About about){
        About findUser = memberRepository.findLogin(about.getUserid());
        if(findUser == null){
            throw new IllegalStateException("해당 아이디가 존재하지 않습니다.");
        }
        memberRepository.update(about);
        return memberRepository.findLogin(about.getUserid());
    }

    public Integer deleteMember(About about){
        About findUser = memberRepository.findLogin(about.getUserid());
        if(findUser == null){
            throw new IllegalStateException("해당 아이디가 존재하지 않습니다.");
        }
        memberRepository.delete(about);
        return 1;
    }

    //Optional 겁나 까다로움...
    public Optional<About> findOne(About about){
        Optional<About> findUser =  memberRepository.findById(about.getUserid());
        String userPass;
        if(findUser.isPresent()){
            userPass = findUser.get().getUserpw();

            if(!userPass.equals(about.getUserpw())){
                throw new IllegalStateException("패스워드가 맞지 않습니다..");
            }
        }

        return  findUser;
    }
    public Optional<About> findByInfo(About about){
        Optional<About> findUser =  memberRepository.findById(about.getUserid());
        return  findUser;
    }
    private void validateDuplicateMember(About about) {
        memberRepository.findById(about.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
