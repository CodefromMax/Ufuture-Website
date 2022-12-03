<template>
  <div class="hello">
    <h2> Times rankings </h2>
    <div :style="{'background-color':'#F0F8FF'}">

    <div class = "search container">
      <b-input-group prepend="Search a University">
        <!--triger 1-->
      <b-form-input type = "text" placeholder = "Name of the University" v-model = "query" @keydown.enter = "search(query)"/>
      <template #append>
        <!--triger 2-->
        <b-button class = "search-button" @click = "search(query)">
          <b-icon-search></b-icon-search>
        </b-button>
      </template>
    
      </b-input-group>

    </div>



    <b-table striped hover responsive :items="Times_rankings" :fields="fields">
      <template #cell(actions)="row">
        <b-button size="sm" v-b-modal.edit-modal @click="add(row.item, row.index, $event.target)">
          Add to interest list
        </b-button>
      
      
      
      </template>
      <template #cell(more)="row">
        <router-link :to="{ path: '/u' }">
        <b-button size="sm" v-b-modal.edit-modal @click="clickedU(row.item)">
        Go
        </b-button>
        </router-link>
         
        
      </template>
    </b-table>
  </div>
  </div>
</template>

<script>
import axios from 'axios';
import VueCookies from 'vue-cookies'
export default {
  name: 'Times_rankings',
  data () {
    return {
      Times_rankings: null,
      query: "",
      fields: [
        {key: 'actions', label: 'Actions'}, 
        {key: 'more', label: 'More'}, 
        {key: 'times_Rank', label: 'Times Rank'},
        {key: 'university_Name', label: 'University Name'},  
        {key: 'teaching_Score', label: 'Teaching Score'},
        {key: 'international_Score', label: 'International Score'},
        {key: 'research_Score', label: 'Research Score'},
        {key: 'citations_Score', label: 'Citations Score'},
        {key: 'income_Score', label: ' Income Score'},
        {key: 'number_Of_Students', label: 'Number Of Students'},
        {key: 'student_Staff_Ratio', label: 'Student Staff Ratio'},
        {key: 'international_Student_Ratio', label: 'International Student Ratio'},
        {key: 'total_Score', label: 'Total Score'}
        
    ],
    }
  },
  mounted () {
    this.init();
  },
  methods: {
    init() {
      axios
        .get('http://localhost:8085/timesrankings')
        .then(response => {this.Times_rankings = response.data, console.log(response.data)})
    },

    search(searchTerm){
      if (searchTerm){
        axios
        .get('http://localhost:8085/qsrankings/searchname/'+searchTerm)
        .then(response => (this.Qs_rankings = response.data) )
        .catch(function (error){
          if (error.response){
            console.log(error.response.data);
          }
          
        })
      }

    },
    clickedU(item){
      
      localStorage.setItem('currentU', item.university_Name)
      console.log(localStorage.getItem('currentU'))
    },
    add(item, index, button){
      if (item){
        let user = VueCookies.get("user")
      if (user == null) {
        alert("User is not logged in");
      }
      let userId = user.userId;
      let qsId = item.qs_ranking_id;
      axios.post(`http://localhost:8085/university/collect?userId=${userId}&qsId=${qsId}`).then(res => {

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
  color: #030404;
}

.search-container{
  padding: 5%
}
</style>
