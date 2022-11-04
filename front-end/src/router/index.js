import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Universities from '@/components/Universities'
import Qs_rankings from '@/components/Qs_rankings'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/qsrankings',
      name: 'Qs_rankings',
      component: Qs_rankings
    },
    {
      path: '/uni',
      name: 'University',
      component: Universities
    }
   
  ]
})
