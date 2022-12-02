import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Universities from '@/components/Universities'
import Qs_rankings from '@/components/Qs_rankings'
import Login from '@/components/Login'
import University_Page from '@/components/University_Page'
import Events from '@/components/Events'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/Login',
      name: 'login',
      component: Login
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
    },
    {
      path: '/u',
      name: 'university_Page',
      component: University_Page
    },
    {
      path: '/events',
      name: 'Events',
      component: Events
    }
   
  ]
})
