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
              disabled="true"
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
              color="success"
              type="submit"
              block
              class="mt-2"
              @click="onMoveUpdate()"
              >수정</v-btn
            >
            <v-btn
              color="error"
              type="submit"
              block
              class="mt-2"
              @click="onMoveDelete()"
              >삭제</v-btn
            >
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
  mounted() {
    axios
      .post("/member/info", {
        userid: this.$store.state.userId,
      })
      .then(({ data }) => {
        console.log(data);
        this.userid = data.userid;
        this.usernm = data.usernm;
        this.useremail = data.useremail;
      })
      .catch((e) => {
        throw new Error(e);
      });
  },
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
    onMoveUpdate: function () {
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
        .post("/member/update", {
          userid: this.userid,
          userpw: this.userpw,
          usernm: this.usernm,
          useremail: this.useremail,
          lastupduserid: this.userid,
          frstinptuserid: this.userid,
        })
        .then(({ data }) => {
          if (data.userid) {
            alert("수정완료되었습니다.");
            this.$router.push("/");
            this.$router.go();
          }
        })
        .catch((e) => {
          throw new Error(e);
        });
    },
    onMoveDelete: function () {
      axios
        .post("/member/delete", {
          userid: this.userid,
        })
        .then(({ data }) => {
          if (data === 1) {
            window.localStorage.removeItem("token");
            window.localStorage.removeItem("userRight");
            this.$store.commit("delUserInfo");
            alert("삭제되었습니다.");
            this.$router.push("/");
            this.$router.go();
          }
        })
        .catch((e) => {
          throw new Error(e);
        });
    },
  },
};
</script>

<style></style>
