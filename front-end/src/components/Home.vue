<template>
  <div>

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
    <b-table striped hover responsive :items="All_universities" :fields="fields">
      <template #cell(actions)="row">
        <b-button size="sm" v-b-modal.edit-modal @click="edit(row.item, row.index, $event.target)">
          Edit
        </b-button>
      </template>
    </b-table>
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
      {key: 'University', label: 'University Name', sortable: true},
 
      {key: 'actions', label: 'Actions'}],
      form: {
          email: '',
          first_name: '',
          last_name: '',
          id: ''
        },
    }
  },
  mounted () {
    this.init();
  },
  methods: {
    init() {
      axios
        .get('http://localhost:8085/universities/{uniname}')
        .then(response => (this.All_universities = response.data))
    },

    search(searchTerm){
      if (searchTerm){
        axios
        .get('http://localhost:8085/students/search/'+searchTerm)
        .then(response => (this.students = response.data) )
        .catch(function (error){
          if (error.response){
            console.log(error.response.data);
          }
          
        })
      }
      console.log(searchTerm)

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
