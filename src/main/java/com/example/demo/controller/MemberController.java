package com.example.demo.controller;

import com.example.demo.domain.About;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController (MemberService memberService){
        this.memberService = memberService;
    }

    //@GetMapping(value="/member")
    //public String RegisterForm(){
    //   return "success";
    //}

    @PostMapping(value = "/member/new")
    public String create(@RequestBody About about){
       //About about = new About();
       memberService.join(about);
       return "success";
    }

    @PostMapping("/about")
    public List<About> test(Model model){
        List<About> memberList = memberService.findAll();
        //model.addAttribute("testname",testName);
        //String nameList = memberList.get(0).getName();
        return memberList;
    }

    @PostMapping("/info")
    public Optional<About> findByInfo(@RequestBody About about){
        Optional<About> memberInfo = memberService.findByInfo(about);
        //model.addAttribute("testname",testName);
        //String nameList = memberList.get(0).getName();
        return memberInfo;
    }
    /*@GetMapping("/member/login")
    public String login(){
      return "login";
    }*/

    @PostMapping("/member/login")
    public Optional<About> loginId(@RequestBody About about){
        Optional<About> findOne = memberService.findOne(about);
        return findOne;
        /*if(memberService.login(about)){
            return "success";
        }
        return "fail";*/
    }

    @PostMapping("/member/update")
    public ResponseEntity<About> updateMember(@RequestBody About about){
        memberService.updateMember(about);
        return ResponseEntity.ok(about);
    }

    @PostMapping("/member/delete")
    public ResponseEntity<About> deleteMember(@RequestBody About about){
        memberService.deleteMember(about);
        return ResponseEntity.ok(about);
    }

    // 이젠 userpw가 PK가 아님 updateMember 메소드에서 userpw도 수정할 수 있음 (일단 남겨둠 해당 메소드 삭제 예정)_2023.10.24_표세빈
    @PostMapping("/member/chgPwd")
    public ResponseEntity<About> updatePassword(@RequestBody About about){
        memberService.updatePassword(about);
        return ResponseEntity.ok(about);
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
