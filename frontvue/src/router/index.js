import { createRouter, createWebHistory } from "vue-router";
// import HomeView from '../views/HomeView.vue';
import HomeView from "@/views/HomeView.vue";
import Member from "@/views/MemberManagement";
import Register from "@/views/RegisterView";
import MemberEdit from "@/views/MemberEdit";

const routes = [
  {
    path: "/",
    name: "homeView",
    component: HomeView,
  },
  {
    path: "/register",
    name: "register",
    component: Register,
  },
  {
    path: "/memberedit",
    name: "memberEdit",
    component: MemberEdit,
  },
  {
    path: "/member",
    name: "member",
    component: Member,
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/",
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
