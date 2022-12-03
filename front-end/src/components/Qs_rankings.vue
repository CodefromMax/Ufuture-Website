<template>
  <div class="hello">
    <div :style="{'background-color':'#F0F8FF'}">
    <h2> QSrankings </h2>


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



    <b-table striped hover responsive :items="Qs_rankings" :fields="fields">
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
  name: 'Qs_rankings',
  data () {
    return {
      All_universities: null,
      Qs_rankings: null,
      query: "",
      fields: [
      {key: 'actions', label: 'Actions'}, 
      {key: 'more', label: 'More'}, 
      {key: 'institution_Name', label: 'University Name'},
      {key: 'qs_ranking_id', label: 'Ranking'},
      {key: 'location', label: 'Location'},
      {key: 'academic_Reputation_Score', label: 'Academic Reputation Score'},
      {key: 'employer_Reputation_Score', label: 'Employer Reputation Score'},
      {key: 'faculty_Student_Score', label: 'Faculty Student Score'},
      {key: 'citations_per_Faculty_Score', label: 'Citations per Faculty Score'},
      {key: 'international_Faculty_Score', label: 'International Faculty Score'},
      {key: 'international_Students_Score', label: 'International Students Score'},
      {key: 'international_Research_Network_Score', label: 'International Research Network Score'},
      {key: 'employment_Outcomes_Score', label: 'Employment Outcomes Score'},
      {key: 'overall_Score', label: 'Overall Score'},
      
    ],
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
        .get('http://localhost:8085/qsrankings')
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
     
      localStorage.setItem('currentU', item.institution_Name)
      console.log(localStorage.getItem('currentU'))
    },

    add(item, index, button){

      axios
        .get('http://localhost:8085/universities/'+ item.institution_Name)
        .then(response => {this.All_universities = response.data[0].universityId, console.log(response.data[0].universityId)} )
      
      let user = VueCookies.get("user")
      if (user == null) {
        alert("User is not logged in");
      }
      let userId = user.userId;
      console.log(userId)
      console.log(this.All_universities)
      axios
      .post('http://localhost:8085/university/interestlist/add',
      {
        "studentId": String(userId),
       "comment": "",
       "universityId": String(this.All_universities)
      
      })
      
       .then(() => this.init() )
      .catch(function (error){
          
           console.log(error);
          
      })

    // add(item, index, button){
    //   let user = VueCookies.get("user")
    //   if (user == null) {
    //     alert("User is not logged in");
    //   }
    //   let userId = user.userId;
    //   let qsId = item.qs_ranking_id;
    //   axios.post(`http://localhost:8085/university/collect?userId=${userId}&qsId=${qsId}`).then(res => {

    //   })

     
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
