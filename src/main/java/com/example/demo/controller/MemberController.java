package com.example.demo.controller;

import com.example.demo.domain.About;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController (MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping(value = "/member/new")
    public String create(@RequestBody About about){
       memberService.join(about);
       return "success";
    }

    @PostMapping("/member/findAll")
    public List<About> selectMemberAll(){
        List<About> memberList = memberService.findAll();
        return memberList;
    }

    @PostMapping("/member/info")
    public Optional<About> findByInfo(@RequestBody About about){
        log.debug("stop");
        Optional<About> memberInfo = memberService.findByInfo(about);
        return memberInfo;
    }

    @PostMapping("/member/login")
    public ResponseEntity<Map<String,Object>> loginId(@RequestBody About about){
        Map<String,Object> data = new HashMap<>();
        try{
            data = memberService.login(about);
        }catch (Exception e) {
            data.put("errorMsg", e.getMessage());
            log.error("loginIdError : {}", e.getMessage());
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PostMapping("/member/update")
    public ResponseEntity<About> updateMember(@RequestBody About param){
        About updateUser = memberService.updateMember(param);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @PostMapping("/member/delete")
    public ResponseEntity<Integer> deleteMember(@RequestBody About about){
        int reuslt = memberService.deleteMember(about);
        return  new ResponseEntity<>(reuslt,HttpStatus.OK);
    }
    public class MemberForm{
        private String Id;

        public String getId(){
            return Id;
        }

        public void setId(String Id){
            this.Id = Id;
        }
    }
}
