<template>
    <div class="hello">
      <h2> MyEvents </h2>
  
  
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
  

      <div style="display: flex; padding: 10px 0 0 10px">
        <b-button size="sm" v-b-modal.edit-modal @click="add( $event.target); isAdd = true">
          Post
          </b-button>
      </div>
      <b-table striped hover responsive :items="Events" :fields="fields">
        <template #cell(actions)="row">
          <b-button size="sm" v-b-modal.edit-modal @click="edit(row.item, row.index, $event.target)">
            Edit
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
       <b-modal id="edit-modal" v-if="isShow" title="Edit MyEvent" @hide="resetEditModal" hide-footer>
      <b-form >

        <label class="sr-only" v-if="!isAdd" for="input-id">Code</label>
        <b-form-input
          id='input-code'
          v-if="!isAdd"
          v-model="form.eventCode"
          placeholder="code"
          readonly
        ></b-form-input>

        <label class="sr-only" for="input-first-name">Name</label>
        <b-form-input
          id='input-name'
          v-model="form.eventName"
          placeholder="name"
          required
        ></b-form-input>

        <label class="sr-only" for="input-last-name">Date</label>
        <b-form-input
          id="input-date"
          v-model="form.eventDate"
          placeholder="date"
          required
        ></b-form-input>


        <label class="sr-only" for="input-office">Location</label>
        <b-form-input
          id="input-location"
          v-model="form.location"
          placeholder="location"
          required
        ></b-form-input>
        
        <br />
        <b-button type="submit" @click="submitEdit" variant="primary">Submit</b-button>
        <b-button v-if="!isAdd" type="reset" @click="reset" variant="warning">Reset</b-button>
        <b-button v-if="!isAdd" type="button" @click="deleteEdit" variant="danger">Remove Event</b-button>
      </b-form>

    </b-modal>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import VueCookies from 'vue-cookies';
  export default {
    name: 'MyEvents',
    data () {
      return {
        isAdd: false,
        isShow: true,
        Events: null,
        query: "",
        fields: [
        {key: 'actions', label: 'actions'}, 
        
        {key: 'eventCode', label: 'Code'},
        {key: 'eventName', label: 'Name'},
        {key: 'eventDate', label: 'Date'},
        {key: 'universityName', label: 'University_Name'},
        {key: 'location', label: 'Location'}
        
        
      ],
        form: {
          eventCode: '',
          eventName: '',
          eventDate:'',
          universityName:'',
            location:'',
            actions:''
          },
      }
    },
    mounted () {
      this.init();
    },
    methods: {
      add() {
        this.isAdd = true;
        this.resetEditModal();
        this.isShow = true;
      },
      reset() {
        this.resetEditModal();
      },
      submitEdit() {
        if (this.isAdd) {
          let user = VueCookies.get("user");
          this.form.universityUserId = user.userId;
          axios.post(`http://localhost:8085/events/post`, this.form).then(res => {
            this.isShow = false;
            this.init();
          })

          return;
        }

        axios.put(`http://localhost:8085/events/${this.form.eventCode}`, this.form).then(res => {
          this.isShow = false;
          this.init();
        })
      },
      deleteEdit() {
        axios.delete(`http://localhost:8085/events/${this.form.eventCode}`, this.form).then(res => {
          this.isShow = false;
          this.init();
        })
      },
      edit(item, index, button) {
        this.isAdd = false;
        this.form = item;
        this.isShow = true;
      } ,
      resetEditModal() {
        this.form = {
            Code: '',
            Name: '',
            Date:'',
            University_Name:'',
            location:'',
            actions:''
        };
      },
      init() {
        let user = VueCookies.get("user");
        let url = "http://localhost:8085/events";
        if (user !== null) {
          url = `http://localhost:8085/events?userId=${user.userId}`;
        }

        axios
          .get(url)
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
  