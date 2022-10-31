<template>
  <div class="hello">
    <h2> QSranking </h2>


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
        <b-button size="sm" v-b-modal.edit-modal @click="edit(row.item, row.index, $event.target)">
          
        </b-button>
      </template>
    </b-table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Students',
  data () {
    return {
      Qs_rankings: null,
      query: "",
      fields: [
      {key: 'Institution_Name', label: 'University Name'},
      {key: 'QS_ranking_id', label: 'Ranking'},
      {key: 'Location', label: 'Location'},
      {key: 'Academic_Reputation_Score', label: 'Academic Reputation Score'},
      {key: 'Employer_Reputation_Score', label: 'Employer Reputation Score'},
      {key: 'Faculty_Student_Score', label: 'Faculty Student Score'},
      {key: 'Citations_per_Faculty_Score', label: 'Citations per Faculty Score'},
      {key: 'International_Faculty_Score', label: 'International Faculty Score'},
      {key: 'International_Students_Score', label: 'International_Students_Score'},
      {key: 'International_Research_Network_Score', label: 'International_Research_Network_Score'},
      {key: 'Employment_Outcomes_Score', label: 'Employment Outcomes Score'},
      {key: 'Overall_Score', label: 'Overall Score'},
      {key: 'actions', label: 'Actions'}, 
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
          actoins:''
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
      console.log(searchTerm)

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
  color: #42b983;
}

.search-container{
  padding: 5%
}
</style>
