<template>
  
  <div class="un">
    <h1 >University Page</h1>

    <div :style="{'background-color':'#F0F8FF'}">

    <h3> QS Ranking </h3>
    <b-table striped hover responsive :items="Qs_rankings" :fields="fields1">
      <template #cell(actions)="row">
        <b-button size="sm" v-b-modal.edit-modal @click="add(row.item, row.index, $event.target)" variant="light">
          Add to interest list
        </b-button>
      </template>
    </b-table>
  
    <h3> CWUR Ranking </h3>
    <b-table striped hover responsive :items="CWUR_rankings" :fields="fields3">
      <template #cell(actions)="row">
        <b-button size="sm" v-b-modal.edit-modal @click="add(row.item, row.index, $event.target)" variant="light">
          Add to interest list
        </b-button>
      </template>
    </b-table>

    <h3> Times Ranking </h3>
    <b-table striped hover responsive :items="Times_rankings" :fields="fields2">
      <template #cell(actions)="row">
        <b-button size="sm" v-b-modal.edit-modal @click="add(row.item, row.index, $event.target)" variant="light">
          Add to interest list
        </b-button>
      </template>
    </b-table>

    <h3> Discussion </h3>
    <b-table striped hover responsive :items="Discussion" :fields="fieldsd">
      <template #cell(actions)="row">
        <b-button size="sm"  variant="light">
          Agree
        </b-button>
      </template>
    </b-table>

  </div>
  </div>
  

</template>

<script>
import axios from 'axios';
// let uni = localStorage.getItem('currentU');

// document.getElementById(uni).innerHTML = uni;

export default {
  name: 'Qs_rankings',
  data () {
    return {
      Qs_rankings: null,
      //query: "",
      fields1: [
      {key: 'actions', label: 'Actions'},
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
    Times_rankings: null,
    
      fields2: [
      {key: 'actions', label: 'Actions'}, 
      {key: 'more', label: 'More'}, 
      {key: 'university_Name', label: 'University Name'},
      {key: 'times_Rank', label: 'Ranking'},
      {key: 'international_Score', label: 'international Score'},
      {key: 'citations_Score', label: 'citations Score'},
      {key: 'teaching_Score', label: 'teaching Score'},
      {key: 'total_Score', label: 'Overall Score'},
      
    ],

    Discussion: null,
    
      fieldsd: [
      {key: 'actions', label: 'Action'},
      {key: 'user.userId', label: 'user'},
      {key: 'discussionContent', label: 'Discussion'},
  
    
    ],
    CWUR_rankings: null,

      fields3: [
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
        .get('http://localhost:8085/qsrankings/searchname/'+localStorage.getItem('currentU'))
        .then(response => (this.Qs_rankings = response.data))
      axios
        .get('http://localhost:8085/qsrankings/searchname/'+localStorage.getItem('currentU'))
        .then(response => (this.CWUR_rankings = response.data))
      axios
        .get('http://localhost:8085/qsrankings/searchname/'+localStorage.getItem('currentU'))
        .then(response => (this.Times_rankings = response.data))
      
      axios
        .get('http://localhost:8085/discussion/' + localStorage.getItem('currentU') )
        .then(response => {this.Discussion = response.data,console.log(response.data),localStorage.setItem('DisId', response.data[response.data.length-1].discussionId+1),console.log(localStorage.getItem('DisId')),this.cuurent_discussion = response.data[localStorage.getItem('DisId')]})

    },

    search(searchTerm){
      if (searchTerm){
        axios
        .get('http://localhost:8085/qsrankings/searchname/'+searchTerm)
        .then(response => (this.Qs_rankings = response.data),console.log(this.Qs_rankings))
        .catch(function (error){
          if (error.response){
            console.log(error.response.data);
          }

        })
      }

    },
    add(item, index, button){
      if (item){
        axios
        .post('http://localhost:8085/university/interestlist/add',
        {
          "listId": (parseInt(localStorage.getItem('length'))+1),
          "universityName": item.institution_Name,
          "comment": ""
        })
        .then(() => this.init() )
        .catch(function (error){

            console.log(error);


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