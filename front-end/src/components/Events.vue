<template>
    <div class="hello">
      <h2> Events </h2>

  
      <b-table striped hover responsive :items="Events" :fields="fields">
        <template #cell(actions)="row">
          <b-button size="sm" v-b-modal.edit-modal @click="add(row.item, row.index, $event.target)">
            Add
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
        
        
        {key: 'eventCode', label: 'Code'},
        {key: 'eventName', label: 'Name'},
        {key: 'eventDate', label: 'Date'},
        {key: 'universityName', label: 'University_Name'},
        {key: 'location', label: 'Location'}
        
        
      ],
        form: {
            Code: '',
            Name: '',
            Date:'',
            University_Name:'',
            location:''
            
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
          .then(response => {
            let data = response.data.map(item => {
              let universityName = item.universityUser && item.universityUser.universityName;
              return {
                ...item,
                universityName
              }
            })
            this.Events = data;
          })
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
  