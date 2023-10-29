<template>
  <div class="back">
    <v-container>
      <v-card class="mx-auto formline" width="400">
        <v-sheet width="300" class="mx-auto formMargin">
          <v-form @submit.prevent>
            <v-text-field
              v-model="userid"
              :rules="userId_rules"
              label="ID"
            ></v-text-field>
            <v-text-field
              v-model="userpw"
              :rules="userPass_rules"
              label="비밀번호"
              type="password"
            ></v-text-field>
            <v-text-field
              v-model="userpwCheck"
              :rules="userPass_rules"
              label="비밀번호확인"
              type="password"
            ></v-text-field>
            <v-text-field
              v-model="usernm"
              :rules="userNmae_rules"
              label="이름"
            ></v-text-field>
            <v-text-field
              v-model="useremail"
              :rules="userEmail_rules"
              label="이메일주소"
            ></v-text-field>
            <v-btn
              @click="onMoveRegister"
              color="success"
              type="submit"
              block
              class="mt-2"
              >가입</v-btn
            >
            <v-btn color="error" type="submit" block class="mt-2">취소</v-btn>
          </v-form>
        </v-sheet>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data: () => ({
    userid: "",
    userpw: "",
    userpwCheck: "",
    usernm: "",
    useremail: "",
    userId_rules: [
      (value) => {
        const korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        if (korean.test(value)) return "ID에는 한글을 입력할수 없습니다.";
        if (value.length > 50) return "ID는 50자를 넘어갈수없습니다.";
      },
    ],
    userPass_rules: [
      (value) => {
        if (value.length > 8) return "비밀번호는 8자리만 입력해주세요.";
      },
    ],
    userNmae_rules: [
      (value) => {
        const korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        if (!korean.test(value)) return "한글만 입력하세요.";
      },
    ],
    userEmail_rules: [
      (value) => {
        const validateEmail =
          /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
        if (!validateEmail.test(value))
          return "올바른 이메일 형식이 일치하지않습니다.";
      },
    ],
  }),
  methods: {
    koreanValueCheck: function (value) {
      const korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
      return korean.test(value);
    },
    emailCheck: function (value) {
      const validateEmail =
        /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
      return validateEmail.test(value);
    },
    onMoveRegister: function () {
      if (this.userid === "" || this.koreanValueCheck(this.userid)) {
        alert("ID를 다시 확인해주세요");
        return;
      } else if (this.userpw === "") {
        alert("비밀번호를 입력하세요");
        return;
      } else if (this.userpw !== this.userpwCheck) {
        alert("비밀번호가 일치하지않습니다.");
        return;
      } else if (this.usernm === "" || !this.koreanValueCheck(this.usernm)) {
        alert("이름이 올바르지 않습니다.");
        return;
      } else if (this.useremail === "" || !this.emailCheck(this.useremail)) {
        alert("이메일이 올바르지 않습니다.");
        return;
      }
      axios
        .post("/member/new", {
          userid: this.userid,
          userpw: this.userpw,
          usernm: this.usernm,
          useremail: this.useremail,
          lastupduserid: this.userid,
          frstinptuserid: this.userid,
        })
        .then((r) => {
          console.log(r);
          if (r.data === "success") {
            alert("회원가입이 완료되었습니다.");
            this.$router.push("/");
          }
        })
        .catch((e) => {
          alert("이미 등록된 회원ID 입니다.");
          throw new Error(e);
        });
    },
  },
};
</script>

<style>
.formMargin {
  margin: 15px;
}

.formline {
  border: solid 2px;
  border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
  color: #356859;
}
</style>
