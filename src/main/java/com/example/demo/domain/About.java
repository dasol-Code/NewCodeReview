package com.example.demo.domain;



import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "userinfotbl")
@DynamicInsert
@Setter
@Getter
public class About {
//    private String token;

    //@GeneratedValue(strategy = GenerationType.IDENTITY) 아이디를 자동생성 안할거라 주석
    private int ser;
    @Id
    private String userid;
    private String usernm;
    private String useremail;
    private String userpw;
    @ColumnDefault("N")
    private String manageyn;
    @ColumnDefault("Y")
    private String usedyn;
    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "frstinptymd")
    private LocalDateTime frstinptymd = LocalDateTime.now();
    private String frstinptuserid;
    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    @Column(name = "lastupdymd")
    private LocalDateTime lastupdymd = LocalDateTime.now();;
    private String lastupduserid;

    @Transient //DB에는 없는 컬럼이지만 엔티티에는 필요한 모델?VO? 설정
    private String chgpassword;

    public int getSer() {
        return ser;
    }

    public void setSer(int ser) {
        this.ser = ser;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) { this.userid = userid; }

    public String getUsernm() {
        return usernm;
    }

    public void setUsernm(String usernm) {
        this.usernm = usernm;
    }
    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getManageyn() {
        return manageyn;
    }

    public void setManageyn(String manageyn) {
        this.manageyn = manageyn;
    }
    public String getUsedyn() {
        return usedyn;
    }

    public void setUsedyn(String usedyn) {
        this.usedyn = usedyn;
    }

    public LocalDateTime getFrstinptymd() {
        return frstinptymd;
    } // 필요없을듯..

    public void setFrstinptymd(LocalDateTime frstinptymd) { this.frstinptymd = frstinptymd; } // 필요없을듯..
    public String getFrstinptuserid() { return frstinptuserid;}
    public void setFrstinptuserid(String frstinptuserid) { this.frstinptuserid = frstinptuserid; }
    public LocalDateTime getLastupdymd() { // 필요없을듯..
        return lastupdymd;
    }
    public void setLastupdymd(LocalDateTime lastupdymd) { // 필요없을듯..
        this.lastupdymd = lastupdymd;
    }
    public String getLastupduserid() {
        return lastupduserid;
    }
    public void setLastupduserid(String lastupduserid) { this.lastupduserid = lastupduserid; }

    public String getChgpassword() {
        return chgpassword;
    }

    public void setChgpassword(String chgpassword) {
        this.chgpassword = chgpassword;
    }
}
