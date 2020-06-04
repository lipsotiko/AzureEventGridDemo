import Vue from 'vue'
import VueRouter from 'vue-router'
import ServiceA from '../views/ServiceA.vue'
import ServiceB from '../views/ServiceB.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'ServiceA',
    component: ServiceA
  }, {
    path: '/service-b',
    name: 'ServiceB',
    component: ServiceB
  }
]

const router = new VueRouter({
  base: process.env.BASE_URL,
  routes
})

export default router
