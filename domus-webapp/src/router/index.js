import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Tenant from '../views/Tenant.vue'
import Manager from '../views/Manager.vue'
import LoginPage from '../views/LoginPage.vue'
import app from '../App.vue'
import { nextTick } from 'q'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/tenant',
    name: 'tenant',
    component: Tenant
  },
  {
    path: '/manager',
    name: 'manager',
    component: Manager
  },
  {
    path: '/login',
    name: 'loginpage',
    component: LoginPage
  },
  {
    path: '/linkAccount',
    name: 'loginpage',
    component: LoginPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
// router.beforeEach((to, from, next) => {
//   var Auser = JSON.parse(localStorage.getItem('authenticatedUser'));
//   if (to.path != "/login" && Auser != null && Auser != undefined && to.path != "/linkAccount") next('/login')
//   else next();

// })
export default router
