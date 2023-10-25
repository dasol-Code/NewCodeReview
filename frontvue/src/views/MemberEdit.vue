<template>
  <v-container>
    <v-card class="mx-auto formline" width="400">
      <v-sheet width="300" class="mx-auto formMargin">
        <v-form @submit.prevent>
          <v-text-field
            v-model="userId"
            :rules="rules"
            label="ID"
            disabled="true"
          ></v-text-field>
          <v-text-field
            v-model="password"
            :rules="rules"
            label="비밀번호"
          ></v-text-field>
          <v-text-field
            v-model="passwordCheck"
            :rules="rules"
            label="비밀번호확인"
          ></v-text-field>
          <v-text-field
            v-model="userName"
            :rules="rules"
            label="이름"
          ></v-text-field>
          <v-combobox label="성별" :items="['남자', '여자']"></v-combobox>
          <v-text-field
            v-model="userPhoneNum"
            :rules="rules"
            label="핸드폰번호"
          ></v-text-field>
          <v-text-field
            v-model="userAddr"
            :rules="rules"
            label="주소"
          ></v-text-field>
          <v-btn color="success" type="submit" block class="mt-2">수정</v-btn>
          <v-btn color="error" type="submit" block class="mt-2">취소</v-btn>
        </v-form>
      </v-sheet>
    </v-card>
  </v-container>
</template>
<script>
export default {
  data: () => ({
    userId: "",
    password: "",
    passwordCheck: "",
    userName: "",
    rules: [
      (value) => {
        if (!value) return "입력해주세요";
      },
    ],
  }),
};
</script>

<style></style>
