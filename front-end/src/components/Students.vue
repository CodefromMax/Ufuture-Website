<template>
  <div class="hello">
    <h2> Students </h2>


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



    <b-table striped hover responsive :items="students" :fields="fields">
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
  name: 'HelloWorld',
  data () {
    return {
      students: null,
      query: "",
      fields: [
      {key: 'id', label: 'Student ID', sortable: true},
      {key: 'lastName', label: 'Last Name', sortable: true},
      {key: 'firstName', label: 'First Name', sortable: true},
      {key: 'email', label: 'Email', sortable: true, sortable: true},
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
        .get('http://localhost:8085/students')
        .then(response => (this.students = response.data))
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

    },
    edit(item, index, button) {
      this.form.id = item.id
      this.form.email = item.email
      this.form.first_name = item.firstName
      this.form.last_name = item.lastName
    },
    resetEditModal() {
      this.form.id=''
      this.form.email=''
      this.form.first_name=''
      this.form.last_name=''
    },
    onSave(event) {
      var numId;
      numId = parseInt(this.form.id);
      axios
        .put('http://localhost:8085/students/' + numId, {
          "id": numId,
          "firstName": this.form.first_name,
          "lastName": this.form.last_name,
          "email": this.form.email,
        })
        .then(() => this.init())
        .catch(function (error) {
          console.log(error);
        });
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
