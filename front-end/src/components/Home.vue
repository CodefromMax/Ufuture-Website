<template>
  <div>

    <div class = "search-container">
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

    <b-table size="lg" striped hover responsive :items="All_universities" :fields="fields">
      <template #cell(actions)="row">
        <b-button size="lg" v-b-modal.edit-modal @click="add(row.item, row.index, $event.target)">
          Add it to interest list
        </b-button>
      </template>
    </b-table>

    
    <div style="height: 150px; background-color: rgba(226, 197, 33, 0.776);">
    <h2 style="height: 100px;">Finding Rankings of all the Universities</h2>
    <b-button-group>
      <b-button size="lg" style="height: 50px;" @click = "QSRanking()">QS</b-button>
    </b-button-group>
    <b-button-group>
      <b-button size="lg" style="height: 50px;" @click = "CWRRanking()">CWR</b-button>
    </b-button-group>    
    <b-button-group>
    <b-button size="lg" style="height: 50px;" @click = "TimesRanking()">Times</b-button>
    </b-button-group>
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
        .get('http://localhost:8085/universities/')
        .then(response => (this.All_universities = response.data))
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
      console.log(searchTerm)

    //Adding a university to interest list with a note
    // add(item, index, button) {
    //   this.form.id = item.id
    //   this.form.Note = item.Note
    // },
    // onSave(event) {
    //   var Uid;
    //   Uid = parseInt(this.form.id);
    //   axios
    //     .post('http://localhost:8085/student/list/' + Uid, {
    //       "id": Uid,
    //       "firstName": this.form.Note,
   
    //     })
    //     .then(() => this.init())
    //     .catch(function (error) {
    //       console.log(error);
    //     });
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
