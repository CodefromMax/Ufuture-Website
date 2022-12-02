<template>
    <div class="hello">
      <h2> Events </h2>
  
  
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
  
  
  
      <b-table striped hover responsive :items="Events" :fields="fields">
        <template #cell(actions)="row">
          <b-button size="sm" v-b-modal.edit-modal @click="add(row.item, row.index, $event.target)">
            Add to My Event
          </b-button>
        
        
        
        </template>
        <template #cell(more)="row">
          <router-link :to="{ path: '/events' }">
          <b-button size="sm" v-b-modal.edit-modal @click="clickedU(row.item)">
          Go
          </b-button>
          </router-link>
           
          
        </template>
      </b-table>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'Events',
    data () {
      return {
        Events: null,
        query: "",
        fields: [
        {key: 'actions', label: 'actions'}, 
        
        {key: 'eventCode', label: 'Code'},
        {key: 'eventName', label: 'Name'},
        {key: 'eventDate', label: 'Date'},
        
        
      ],
        form: {
            Code: '',
            Name: '',
            Date:'',
           
            actions:''
          },
      }
    },
    mounted () {
      this.init();
    },
    methods: {
      init() {
        axios
          .get('http://localhost:8085/events')
          .then(response => (this.Events = response.data))
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
  