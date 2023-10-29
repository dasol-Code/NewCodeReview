<template>
  <v-app>
    <v-card color="basil">
      <v-container v-show="loginBtn">
        <v-dialog width="500">
          <template v-slot:activator="{ props }">
            <v-btn v-bind="props" text="Login"> </v-btn>
          </template>
          <template v-slot:default="{ isActive }">
            <v-container class="container">
              <v-card class="screen">
                <div class="screen__content">
                  <form class="login">
                    <div class="login__field">
                      <i class="login__icon fas fa-user"></i>
                      <input
                        type="text"
                        class="login__input"
                        placeholder="User name / Email"
                        v-model="userId"
                      />
                    </div>
                    <div class="login__field">
                      <i class="login__icon fas fa-lock"></i>
                      <input
                        type="password"
                        class="login__input"
                        placeholder="Password"
                        v-model="userPw"
                      />
                    </div>
                    <v-card-actions>
                      <v-btn
                        class="button login__submit"
                        @click="setLogin(isActive)"
                      >
                        <span class="button__text">로그인</span>
                        <i class="button__icon fas fa-chevron-right"></i>
                      </v-btn>
                    </v-card-actions>
                    <v-card-actions>
                      <v-btn
                        class="button login__submit"
                        @click="setMemberRegister(isActive)"
                      >
                        <span class="button__text">회원가입</span>
                        <i class="button__icon fas fa-chevron-right"></i>
                      </v-btn>
                    </v-card-actions>
                  </form>
                </div>
                <div class="screen__background">
                  <span
                    class="screen__background__shape screen__background__shape4"
                  ></span>
                  <span
                    class="screen__background__shape screen__background__shape3"
                  ></span>
                  <span
                    class="screen__background__shape screen__background__shape2"
                  ></span>
                  <span
                    class="screen__background__shape screen__background__shape1"
                  ></span>
                </div>
              </v-card>
            </v-container>
          </template>
        </v-dialog>
      </v-container>
      <v-container v-show="logoutBtn">
        <v-dialog width="500" id="logoutBtn">
          <template v-slot:activator="{ props }">
            <v-btn v-bind="props" text="LogOut"> </v-btn>
          </template>
          <template v-slot:default="{ isActive }">
            <v-card title="로그아웃">
              <v-card-text> 로그아웃 하시겠습니까? </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  text="예"
                  color="success"
                  @click="setLogout(isActive)"
                ></v-btn>
                <v-btn
                  text="아니오"
                  color="error"
                  @click="isActive.value = false"
                ></v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>
      </v-container>
      <v-tabs v-model="tab" bg-color="transparent" color="basil" grow>
        <!-- <v-tab v-for="item in items" :key="item" :value="item">
          {{ item }}
        </v-tab> -->
        <v-tab
          v-for="item in items"
          :key="item"
          :value="item"
          :to="item.to"
          class="tapText"
        >
          {{ item.title }}
        </v-tab>
      </v-tabs>
    </v-card>
    <v-main>
      <v-container>
        <router-view />
      </v-container>
    </v-main>
  </v-app>
</template>
4f
<script>
import axios from "axios";
export default {
  data() {
    return {
      userId: "",
      userPw: "",
      loginBtn: true,
      logoutBtn: false,
      tab: "Appetizers",
      // items: ["Appetizers", "Entrees", "Deserts", "Cocktails"], // for문으로 돌리는법
      items: "",
    };
  },
  mounted() {
    const token = this.$store.state.token;
    const userRight = this.$store.state.userRight;
    // 로그인 상태에 따른 메뉴 구분
    let viewList = [];
    viewList.push({ title: "Home", to: "/" });
    console.log(token);
    if (token) {
      this.loginBtn = false;
      this.logoutBtn = true;
      if (userRight === "N") {
        viewList.push({ title: "회원정보수정", to: "/memberedit" });
      } else {
        viewList.push({ title: "회원관리", to: "/member" });
      }
    } else {
      this.loginBtn = true;
      this.logoutBtn = false;
      viewList.push({ title: "회원가입", to: "/register" });
    }
    this.items = viewList;
  },
  methods: {
    // storgage.js Mutations 함수 등록
    // ...mapMutations(["setLoginYn"]),
    setLogin: function (param) {
      axios
        .post("/member/login", {
          userid: this.userId,
          userpw: this.userPw,
        })
        .then(({ data }) => {
          if (data.token) {
            window.localStorage.setItem("token", data.token);
            window.localStorage.setItem("userRight", data.userRight);
            param.value = false;
            location.reload();
          } else {
            alert(data.errorMsg);
          }
        })
        .catch((e) => {
          throw new Error(e);
        });
    },
    setMemberRegister: function (param) {
      // 로그인창에서 회원가입 클릭시 회원가입 창으로 이동
      this.$router.push("/register");
      param.value = false;
    },
    setLogout: function (param) {
      window.localStorage.removeItem("token");
      window.localStorage.removeItem("userRight");
      location.reload();
      param.value = false;
    },
  },
};
</script>
<style>
.tapText {
  font-size: 25px;
}
/* Helper classes */
.bg-basil {
  background-color: #fffbe6 !important;
}
.text-basil {
  color: #356859 !important;
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-family: Raleway, sans-serif;
}

body {
  background: linear-gradient(90deg, #619485, #356859);
}

.container {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}

.screen {
  background: linear-gradient(90deg, #63c8aa, #356859);
  position: relative;
  height: 600px;
  width: 360px;
  box-shadow: 0px 0px 24px #3d7967;
}

.screen__content {
  z-index: 1;
  position: relative;
  height: 100%;
}

.screen__background {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 0;
  -webkit-clip-path: inset(0 0 0 0);
  clip-path: inset(0 0 0 0);
}

.screen__background__shape {
  transform: rotate(45deg);
  position: absolute;
}

.screen__background__shape1 {
  height: 520px;
  width: 520px;
  background: #fffbe6;
  top: -50px;
  right: 120px;
  border-radius: 0 72px 0 0;
}

.screen__background__shape2 {
  height: 220px;
  width: 220px;
  background: #356859;
  top: -172px;
  right: 0;
  border-radius: 32px;
}

.screen__background__shape3 {
  height: 540px;
  width: 190px;
  background: linear-gradient(270deg, #5ca891, #356859);
  top: -24px;
  right: 0;
  border-radius: 32px;
}

.screen__background__shape4 {
  height: 400px;
  width: 200px;
  background: #6aa493;
  top: 420px;
  right: 50px;
  border-radius: 60px;
}

.login {
  width: 320px;
  padding: 30px;
  padding-top: 156px;
}

.login__field {
  padding: 20px 0px;
  position: relative;
}

.login__icon {
  position: absolute;
  top: 30px;
  color: #3bc79d;
}

.login__input {
  border: none;
  border-bottom: 2px solid #d1d1d4;
  background: none;
  padding: 10px;
  padding-left: 24px;
  font-weight: 700;
  width: 75%;
  transition: 0.2s;
}

.login__input:active,
.login__input:focus,
.login__input:hover {
  outline: none;
  border-bottom-color: #356859;
}

.login__submit {
  background: #fff;
  font-size: 14px;
  margin-top: 30px;
  padding: 16px 20px;
  border-radius: 26px;
  border: 1px solid #d4d3e8;
  text-transform: uppercase;
  font-weight: 700;
  display: flex;
  align-items: center;
  width: 100%;
  color: #4c489d;
  box-shadow: 0px 2px 2px #5c5696;
  cursor: pointer;
  transition: 0.2s;
}

.login__submit:active,
.login__submit:focus,
.login__submit:hover {
  border-color: #6a679e;
  outline: none;
}

.button__icon {
  font-size: 24px;
  margin-left: auto;
  color: #7875b5;
}

.social-login {
  position: absolute;
  height: 140px;
  width: 160px;
  text-align: center;
  bottom: 0px;
  right: 0px;
  color: #fff;
}

.social-icons {
  display: flex;
  align-items: center;
  justify-content: center;
}

.social-login__icon {
  padding: 20px 10px;
  color: #fff;
  text-decoration: none;
  text-shadow: 0px 0px 8px #7875b5;
}

.social-login__icon:hover {
  transform: scale(1.5);
}
</style>
