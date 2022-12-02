<template>
    <div class="hello">
      <h2> Times rankings </h2>
  
  
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
            
          </b-button>
        
        
        
        <!-- </template>
        <template #cell(more)="row">
          <router-link :to="{ path: '/u' }">
          <b-button size="sm" v-b-modal.edit-modal @click="clickedU(row.item)">
          Go
          </b-button>
          </router-link>
            -->
          
        </template>
      </b-table>
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
        
        {key: 'More', label: 'More'}, 
        {key: 'times_Id', label: 'times_Id'},
        {key: 'times_Rank', label: 'Times_Rank'},
        {key: 'university_Name', label: 'University_Name'},
        {key: 'Country', label: 'Country'},
        {key: 'Teaching_Score', label: 'Teaching_Score'},
        {key: 'International_Score', label: 'International_Score'},
        {key: 'Research_Score', label: 'Research_Score'},
        {key: 'Citations_Score', label: 'Citations_Score'},
        {key: 'Income_Score', label: ' Income_Score'},
        {key: 'Total_Score', label: 'Total_Score'},
        {key: 'Number_Of_Students', label: 'Number_Of_Students'},
        {key: 'Student_Staff_Ratio', label: 'Student_Staff_Ratio'},
        {key: 'International_Student_Ratio', label: 'International_Student_Ratio'},
        
      ],

      clickedUTimes(item){
      //console.log(item.institution_Name)
      localStorage.setItem('currentU', item.institution_Name)
      console.log(localStorage.getItem('currentU'))
    },


        form: {
            University_name: '',
            QS_ranking: '',
            Location:'',
            Academic_Reputation_Score:'',
            Employer_Reputation_Score:'',
            Faculty_Student_Score:'',
            Citations_per_Faculty_Score:'',
            International_Faculty_Score:'',
            International_Students_Score:'',
            International_Research_Network_Score:'',
            Employment_Outcomes_Score:'',
            Overall_Score:'',
            actions:'',
            more:''
          },
      }
    },
    mounted () {
      this.init();
    },
    methods: {
      init() {
        axios
          .get('http://localhost:8085/timesrankings')
          .then(response => (this.Qs_rankings = response.data))
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
        //console.log(item.institution_Name)
        localStorage.setItem('currentU', item.institution_Name)
        console.log(localStorage.getItem('currentU'))
      },
      add(item, index, button){
        let user = VueCookies.get("user")
        if (user == null) {
          alert("User is not logged in");
        }
        let userId = user.userId;
        let qsId = item.qs_ranking_id;
        axios.post(`http://localhost:8085/university/collect?userId=${userId}&qsId=${qsId}`).then(res => {
  
        })
        // if (item){
        //   axios
        //   .post('http://localhost:8085/university/interestlist/add',
        //   {
        //     "listId": (parseInt(localStorage.getItem('length'))+1),
        //     "universityName": item.institution_Name,
        //     "comment": ""
        //   })
        //   .then(() => this.init() )
        //   .catch(function (error){
            
        //       console.log(error);
            
            
        //   })
        // }
  
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
  