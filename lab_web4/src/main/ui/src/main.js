import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Start from "@/pages/Start"
import Main from "@/pages/Main"
import FlashMessage from '@smartweb/vue-flash-message'

let config = {
  name: 'flashMessage',
  tag: 'FlashMessage',
  time: 2000,
  icon: true
}

Vue.use(FlashMessage, config);

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {path: '/', name: 'start', component: Start},
    {path: '/', name: 'main', component: Main },
  ],
  mode: "history"
})

Vue.config.productionTip = false

export const eventBus = new Vue()

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
