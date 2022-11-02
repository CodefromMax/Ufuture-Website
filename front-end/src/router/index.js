import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
//import Students from '@/components/Students'
import Professors from '@/components/Professors'
// import HomePage from '@/components/HomePage'
import interest_list from '@/components/interest_list'
import Qs_rankings from '@/components/Qs_rankings'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    //{
      //path: '/students',
      //name: 'Students',
      //omponent: Students
    //},
    {
      path: '/professors',
      name: 'Professors',
      component: Professors
    },
    {
      path: '/interest_list',
      name: 'interest_list',
      component: interest_list
    },
    {
      path: '/qsrankings',
      name: 'Qs_rankings',
      component: Qs_rankings
    }
   
  ]
})
