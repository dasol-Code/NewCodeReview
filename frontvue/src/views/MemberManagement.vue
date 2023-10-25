<template>
  <v-container>
    <v-card
      class="mx-auto formline"
      width="1500"
      v-for="item in userList"
      :key="item"
      :value="item"
    >
      <v-list v-model="open">
        <v-list-group value="Users">
          <template v-slot:activator="{ props }">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-account-circle"
              :title="item.id"
            ></v-list-item>
          </template>
          <v-list-item prepend-icon="mdi-file-outline">
            <v-dialog width="500">
              <template v-slot:activator="{ props }">
                <v-btn class="btn" v-bind="props" text="정보"> </v-btn>
              </template>
              <template v-slot:default="{ isActive }">
                <v-card>
                  <v-table>
                    <thead>
                      <tr>
                        <th class="text-left"><h1>항목</h1></th>
                        <th class="text-left"><h1>정보</h1></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>ID</td>
                        <td>{{ item.id }}</td>
                      </tr>
                      <tr>
                        <td>이름</td>
                        <td>{{ item.name }}</td>
                      </tr>
                      <tr>
                        <td>나이</td>
                        <td>{{ item.age }}</td>
                      </tr>
                      <tr>
                        <td>생일</td>
                        <td>{{ item.birdth }}</td>
                      </tr>
                      <tr>
                        <td>주소</td>
                        <td>{{ item.adr }}</td>
                      </tr>
                      <tr>
                        <td>전화번호</td>
                        <td>{{ item.phone }}</td>
                      </tr>
                    </tbody>
                  </v-table>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text="확인" @click="isActive.value = false"></v-btn>
                  </v-card-actions>
                </v-card>
              </template>
            </v-dialog>
          </v-list-item>
          <v-list-item prepend-icon="mdi-update">
            <v-dialog width="500">
              <template v-slot:activator="{ props }">
                <v-btn class="btn" v-bind="props" text="수정"> </v-btn>
              </template>
              <template v-slot:default="{ isActive }">
                <v-card title="수정">
                  <v-card-text> 수정하시겠습니까? </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text="확인" @click="isActive.value = false"></v-btn>
                    <v-btn text="취소" @click="isActive.value = false"></v-btn>
                  </v-card-actions>
                </v-card>
              </template>
            </v-dialog>
          </v-list-item>
          <v-list-item prepend-icon="mdi-delete">
            <v-dialog width="500">
              <template v-slot:activator="{ props }">
                <v-btn class="btn" v-bind="props" text="삭제"> </v-btn>
              </template>
              <template v-slot:default="{ isActive }">
                <v-card title="삭제">
                  <v-card-text> 정말 삭제하시겠습니까? </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text="확인" @click="isActive.value = false"></v-btn>
                    <v-btn text="취소" @click="isActive.value = false"></v-btn>
                  </v-card-actions>
                </v-card>
              </template>
            </v-dialog>
          </v-list-item>
        </v-list-group>
      </v-list>
    </v-card>
  </v-container>
</template>
<script>
export default {
  data: () => ({
    infoType: [
      "아이디 :",
      "이름 :",
      "나이 :",
      "생일 :",
      "주소 :",
      "핸드폰번호 :",
    ],
    open: ["Users"],
    cruds: [
      ["Read", "mdi-file-outline"],
      ["Update", "mdi-update"],
      ["Delete", "mdi-delete"],
    ],
    userList: "",
  }),
  mounted() {
    let list = [
      {
        id: "유저1",
        name: "나해준",
        age: "30",
        birdth: "920630",
        adr: "수원",
        phone: "010-xxx-0001",
      },
      {
        id: "유저2",
        name: "장훈",
        age: "20",
        birdth: "950812",
        adr: "서울",
        phone: "010-xxx-0002",
      },
      {
        id: "유저3",
        name: "신익수",
        age: "22",
        birdth: "920930",
        adr: "남천동",
        phone: "010-xxx-0003",
      },
    ];
    this.userList = list;
  },
};
</script>
<style>
.btn {
  width: 200px;
  font-size: 16px;
  font-weight: 600;
  color: #fff;
  cursor: pointer;
  margin: 20px;
  height: 55px;
  text-align: center;
  border: none;
  background-size: 300% 100%;
  background-image: linear-gradient(
    to right,
    #e6f1f6,
    #9fb7f1,
    #486af3,
    #f94c29
  );
  box-shadow: 0 4px 15px 0 rgba(31, 67, 66, 0.75);
  border-radius: 50px;
  moz-transition: all 0.4s ease-in-out;
  -o-transition: all 0.4s ease-in-out;
  -webkit-transition: all 0.4s ease-in-out;
  transition: all 0.4s ease-in-out;
}
.btn:hover {
  background-position: 100% 0;
  moz-transition: all 0.4s ease-in-out;
  -o-transition: all 0.4s ease-in-out;
  -webkit-transition: all 0.4s ease-in-out;
  transition: all 0.4s ease-in-out;
}

.btn:focus {
  outline: none;
}
</style>
