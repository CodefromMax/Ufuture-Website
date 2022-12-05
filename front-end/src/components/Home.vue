<template>
  <div>
    <div :style="{'background-color':'#F0F8FF'}">
    
    <b-carousel
      id="carousel-1"
      :interval="4000"
      controls
      indicators
      fade
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
    >
      <router-link :to="{ path: '/events' }">
      <b-carousel-slide
        img-src="https://i.ibb.co/MhYfDL0/12-C2-FA67-BFA7-4-D42-BF46-EADFA5-EA63-B5.jpg"
      ></b-carousel-slide>
      <b-carousel-slide
        img-src="https://i.ibb.co/72kvZB3/66-AC7522-F756-489-B-A5-BA-97-F2854-DF37-E.jpg"
      ></b-carousel-slide>
      <b-carousel-slide
        img-src="https://i.ibb.co/4FnRfbS/1-AFBED6-D-CBCF-45-CE-ACE0-791-C13517329.jpg"
      ></b-carousel-slide>
    </router-link>
    </b-carousel>
    
    <div class = "search-container">
      <b-input-group prepend="Search a University">
      <b-form-input type = "text" placeholder = "Name of the University" v-model = "query" @keydown.enter = "search(query)"/>
      <template #append>
        <b-button class = "search-button" @click = "search(query)">
          <b-icon-search></b-icon-search>
        </b-button>
      </template>
    
      </b-input-group>
    </div>

    <b-table size="lg" striped hover responsive :items="All_universities" :fields="fields">
      <template #cell(actions)="row">
        <router-link :to="{ path: '/u' }">
        <b-button size="sm" v-b-modal.edit-modal @click="clickedU(row.item)">
        Go
        </b-button>
        </router-link>
         
        
      </template>
    </b-table>

    
    <div style="height: 200px; background-color: rgba(237, 215, 199, 0.2);">
    <h2 style="height: 100px;">Finding Rankings of all the Universities</h2>
    <b-button-group>


      <router-link :to="{ path: '/qsrankings' }">
      <b-button size="lg" style="height: 50px;" >QS</b-button></router-link>
   
    </b-button-group>
    <b-button-group>
      <router-link :to="{ path: '/cwurrankings' }">
      <b-button size="lg" style="height: 50px;" >CWUR</b-button></router-link>
    </b-button-group>    
    <b-button-group>
      <router-link :to="{ path: '/timesrankings' }">
    <b-button size="lg" style="height: 50px;" >Times</b-button></router-link>
    </b-button-group>
  </div>
  </div>

  </div>
  

</template>

<script>

import axios from 'axios';

export default {
  name: 'Home',
  data () {
    return {
      All_universities: null,
      query: "",
      
      fields: [
      {key: 'universityId', label: 'University ID'},
      {key: 'uniName', label: 'University Name', sortable: true},     
      {key: 'actions', label: 'Action'}],
    }
  },
  mounted () {
    this.init();
  },
  methods: {
    init() {
      
    },

    clickedU(item){
     
     localStorage.setItem('currentU', item.uniName)
    
   },

    search(searchTerm){
      if (searchTerm){
        axios
        .get('http://localhost:8085/universities/'+searchTerm)
        .then(response => (this.All_universities = response.data) )
        .catch(function (error){
          if (error.response){
            console.log(error.response.data);
          }
          
        })
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}


.search-container{
  padding: 5%
}
</style>
