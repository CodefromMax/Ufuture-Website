<template>
  <div class="hello">
    <h2> CWUR rankings </h2>
    <div :style="{'background-color':'#F0F8FF'}">

    <div class = "search container">
      <b-input-group prepend="Search a University">
      <b-form-input type = "text" placeholder = "Name of the University" v-model = "query" @keydown.enter = "search(query)"/>
      <template #append>
        <b-button class = "search-button" @click = "search(query)">
          <b-icon-search></b-icon-search>
        </b-button>
      </template>
    
      </b-input-group>

    </div>



    <b-table striped hover responsive :items="CWUR_rankings" :fields="fields">
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
  name: 'CWUR_rankings',
  data () {
    return {
      CWUR_rankings: null,
      query: "",
      fields: [
      {key: 'actions', label: 'Actions'}, 
      {key: 'more', label: 'More'}, 
      {key: 'institution_Name', label: 'University Name'},
      {key: 'cwur_Id', label: 'Ranking'},
      {key: 'alumni_Employment', label: 'alumni Employment'},
      {key: 'citations', label: 'citations'},
      {key: 'quality_of_education', label: 'quality of education'},
      {key: 'score', label: 'Overall Score'},
      
    ],
    }
  },
  mounted () {
    this.init();
  },
  methods: {
    init() {
      axios
        .get('http://localhost:8085/cwurrankings')
        .then(response => {this.CWUR_rankings = response.data})
    },

    search(searchTerm){
      if (searchTerm){
        axios
        .get('http://localhost:8085/cwurrankings/searchname/'+searchTerm)
        .then(response => (this.CWUR_rankings = response.data) )
        .catch(function (error){
          if (error.response){
            console.log(error.response.data);
          }
          
        })
      }

    },
    clickedU(item){
      localStorage.setItem('currentU', item.institution_Name)
    },
    add(item, index, button){
      let user = VueCookies.get("user")
      if (user == null) {
        alert("User is not logged in");
      }
      let userId = user.userId;
      axios
        .get('http://localhost:8085/universitiesByType/1/'+ item.cwur_Id)
        .then(response => {
          this.All_universities = response.data[0].universityId;
        }).then(() => {
          axios
        .post('http://localhost:8085/university/interestlist/add',
        {
          "studentId": String(userId),
          "comment": "",
          "universityId": String(this.All_universities),
          "type": 1,
          "listOrder": item.cwur_Id
        })
        .then((response) => {
            let data = response.data;
            if (data == "") {
              alert("already added");
              return;
            }
            this.init();
            alert("Add Success") 
        })
        .catch(function (error){
          alert("Add Exception") 
            console.log(error);
        })
        })
      
      
    },
    
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
