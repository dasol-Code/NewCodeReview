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
        if (value.length > 50) return "ID가 너무 깁니다.";
      },
      (value) => {
        const korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        if (korean.test(value)) {
          return "한글을 입력하지마세요";
        }
      },
    ],
    userPass_rules: [
      // (value) => {
      //   const validatePassword =
      //     /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      //   if (!validatePassword.test(value))
      //     return "비밀번호는 영문, 숫자, 특수문자 만 입력 가능합니다.";
      // },
      (value) => {
        if (value.length > 8) return "비밀번호는 8자리만 입력해주세요.";
      },
    ],
    userNmae_rules: [
      (value) => {
        const korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        if (!korean.test(value)) {
          return "한글만 입력하세요.";
        }
      },
    ],
    userEmail_rules: [
      (value) => {
        const validateEmail =
          /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
        if (!validateEmail.test(value))
          return "이메일 형식이 일치하지않습니다.";
      },
    ],
  }),
  methods: {
    onMoveRegister: function () {
      if (this.userid === "") {
        alert("ID를 입력하세요");
        return;
      } else if (this.userpw === "") {
        alert("비밀번호를 입력하세요");
        return;
      } else if (this.userpw !== this.userpwCheck) {
        alert("비밀번호 확인과 일치하지않습니다.");
        return;
      } else if (this.usernm === "") {
        alert("이름을 입력해주세요");
        return;
      } else if (this.useremail === "") {
        alert("이메일을 입력해주세요");
        return;
      }
      axios
        .post("/main/login", {
          // userId: this.userId,
          // userPass: this.userPass,
        })
        .then((r) => {})
        .catch((e) => {
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
