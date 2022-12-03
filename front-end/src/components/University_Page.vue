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
    <div style="display: flex; padding: 10px 0 0 10px">
        <b-button size="sm" v-b-modal.edit-modal @click="add1($event.target); isAdd = true">
          Post
          </b-button>
      </div>
    <b-table striped hover responsive :items="Discussion" :fields="fieldsd">
      <template #cell(actions)="row">
          <b-button size="sm" v-b-modal.edit-modal @click="edit(row.item, row.index, $event.target)">
            Edit
          </b-button>
      </template>
    </b-table>

    <b-modal id="edit-modal" v-if="isShow" title="Discussion" @hide="resetEditModal" hide-footer>
      <b-form >

        <label class="sr-only" v-if="!isAdd" for="input-id">Code</label>
        <b-form-input
          id='input-code'
          v-if="!isAdd"
          v-model="form.eventCode"
          placeholder="code"
          readonly
        ></b-form-input>

        <label class="sr-only" for="input-first-name">discussion Content</label>
        <b-form-input
          id='input-name'
          v-model="form.discussionContent"
          placeholder="Discussion"
          required
        ></b-form-input>

        

        
        <br />
        <b-button type="submit" @click="submitEdit" variant="primary">Submit</b-button>
        <b-button v-if="!isAdd" type="reset" @click="reset" variant="warning">Reset</b-button>
        <b-button v-if="!isAdd" type="button" @click="deleteEdit" variant="danger">Remove</b-button>
      </b-form>

    </b-modal>

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
      isAdd: false,
        isShow: true,
        Events: null,
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
        {key: 'times_Rank', label: 'Times Rank'},
        {key: 'university_Name', label: 'University Name'},  
        {key: 'teaching_Score', label: 'Teaching Score'},
        {key: 'international_Score', label: 'International Score'},
        {key: 'research_Score', label: 'Research Score'},
        {key: 'citations_Score', label: 'Citations Score'},
        {key: 'income_Score', label: ' Income Score'},
        {key: 'number_Of_Students', label: 'Number Of Students'},
        {key: 'student_Staff_Ratio', label: 'Student Staff Ratio'},
        {key: 'international_Student_Ratio', label: 'International Student Ratio'},
        {key: 'total_Score', label: 'Total Score'}
        
    ],
    Discussion: null,
    
      fieldsd: [
      {key: 'actions', label: 'Action'},
      {key: 'user.userId', label: 'user'},
      {key: 'discussionContent', label: 'Discussion'},
      {key: 'discussionId', label: 'Id'},
  
    
    ],
    form: {
        discussionContent: '',
          universityName:'',
          userId:'',
          discussionId:''
          },
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
        .get('http://localhost:8085/cwurrankings/'+localStorage.getItem('currentU'))
        .then(response => (this.CWUR_rankings = response.data))
      axios
        .get('http://localhost:8085/timesrankings/'+localStorage.getItem('currentU'))
        .then(response => (this.Times_rankings = response.data))
      
      axios
        .get('http://localhost:8085/discussion/' + localStorage.getItem('currentU') )
        .then(response => {this.Discussion = response.data,console.log(response.data)})
    },
    //
    add1() {
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
          console.log("user",user.userId)
          this.form.userId = user.userId;
          axios.post(`http://localhost:8085/discussion/post`, {
            "userId": this.form.userId,
            "DiscussionContent": this.form.discussionContent,
            "universityName": localStorage.getItem('currentU')
          },
          console.log({
            "userId": this.form.userId,
            "DiscussionContent": this.form.discussionContent,
            "universityName": this.form.universityName
          })
            ).then(res => {
            this.isShow = false;
            this.init();
          })
          return;
        }
        
        axios.put(`http://localhost:8085/discussion/${this.form.discussionId}`, this.form).then(res => {
          this.isShow = false;
          this.init();
        })
      },
      deleteEdit() {
        axios.delete(`http://localhost:8085/discussion/${this.form.discussionId}`).then(res => {
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
          discussionContent: '',
          universityName:'',
          userId:'',
          discussionId:''
        };
      },
    //
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