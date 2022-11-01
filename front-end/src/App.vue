<template>
  <div id="app">
  <b-container>
    <div>
      <b-navbar toggleable type="dark" variant="dark">
        <router-link :to="{ path: '/interest_list' }">
      <b-button size="lg" style="height: 50px;">Interest List</b-button></router-link>
        <b-navbar-brand href="#"><b-icon icon="book" variant="warning" style="margin-left: 20px;"></b-icon> U Future </b-navbar-brand>

      <b-navbar-toggle target="sidebar-menu">
      </b-navbar-toggle>
      <b-sidebar id="sidebar-menu" title="MIE350 University" backdrop-variant="dark" backdrop shadow>
        <b-nav vertical align="center">
          <b-nav-item to="/" exact exact-active-class="exact-active">Home</b-nav-item>
          <b-nav-item to="/students" exact exact-active-class="exact-active">QS Rankings</b-nav-item>
          <b-nav-item to="/professors" exact exact-active-class="exact-active">Professors</b-nav-item>
          <b-nav-item to="/interest_list" exact exact-active-class="exact-active">Interest list</b-nav-item>
          <b-nav-item disabled>Admin</b-nav-item>
        </b-nav>
      </b-sidebar>
    </b-navbar>
    </div>
    <br />
    <router-view/>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #426991;
  margin-top: 20px;
}
</style>
