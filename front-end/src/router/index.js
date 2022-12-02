import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Universities from '@/components/Universities'
import Qs_rankings from '@/components/Qs_rankings'
import CWUR_ranking from '@/components/CWUR_ranking'
import Times_ranking from '@/components/Times_ranking'
import Login from '@/components/Login'
import University_Page from '@/components/University_Page'
import Events from '@/components/Events'
import MyEvents from '@/components/MyEvents'
import Times_rankings from '@/components/Times_rankings'
import CWUR_rankings from '@/components/CWUR_rankings'
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
      path: '/cwurrankings',
      name: 'CWUR_ranking',
      component: CWUR_ranking
    },
    {
      path: '/timesrankings',
      name: 'times_ranking',
      component: Times_ranking
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
    },
    {
      path: '/myEvents',
      name: 'MyEvents',
      component: MyEvents
    },
    {
      path: '/times_rankings',
      name: 'Times_rankings',
      component: Times_rankings
    },
    {
      path: '/CWUR_rankings',
      name: 'CWUR_rankings',
      component: CWUR_rankings
    }
   
  ]
})
