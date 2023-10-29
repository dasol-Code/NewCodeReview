import { createStore } from "vuex";

// state, getters, mutations, actions, modules
export default createStore({
  state: {
    token: window.localStorage.getItem("token"),
    userRight: window.localStorage.getItem("userRight"),
    userId: window.localStorage.getItem("userId"),
  },
  getters: {
    // storage에서 state의 직접적으로 접근할수있는 부분은 getters밖에없다.
    // 그래서 공통적으로 storage안에서 뭔가 로직을 통해 바뀐 state를 전달할수있는건 getters가유일하다.
    // sampleGetters(state) {
    //   return state.counter ** 2;
    // },
  },
  mutations: {
    // muations 함수는 매개변수로 storage에 있는 state를 첫번째 매개변수로 둔다
    // 두번째 매개변수는 화면에서 던져줄 파라미터를 매개변수로 둔다.
    // 화면으로부터 storage에있는 state값을 변경시켜줄수있는 유일한 매개체이다.
    setUserId(state, value) {
      state.userId = value;
    },
    delUserInfo(state) {
      state.token = "";
      state.userRight = "";
      state.userId = "";
    },
  },
  actions: {},
  modules: {},
});
