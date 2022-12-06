<template>
  
  <div class="un">
    <h1 >University Page</h1>

    <div :style="{'background-color':'#F0F8FF'}">

    <h3> QS Ranking </h3>
    <b-table striped hover responsive :items="Qs_rankings" :fields="fields1">
      <template #cell(actions)="row">
        <b-button size="sm"  @click="add111(row.item, row.index, $event.target)" variant="light">
          Add to interest list
        </b-button>
      </template>
    </b-table>
  
    <h3> CWUR Ranking </h3>
    <b-table striped hover responsive :items="CWUR_rankings" :fields="fields3">
      <template #cell(actions)="row">
        <b-button size="sm"  @click="add111(row.item, row.index, $event.target)" variant="light">
          Add to interest list
        </b-button>
      </template>
    </b-table>

    <h3> Times Ranking </h3>
    <b-table striped hover responsive :items="Times_rankings" :fields="fields2">
      <template #cell(actions)="row">
        <b-button size="sm" @click="add111(row.item, row.index, $event.target)" variant="light">
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

        <label class="sr-only" for="input-first-name">Discussion</label>
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
        {key: 'university_Name', label: 'University Name'},  
        {key: 'times_Rank', label: 'Times Rank'},
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
      {key: 'institution_Name', label: 'University Name'},
      {key: 'cwur_Id', label: 'Ranking'},
      {key: 'national_rank', label: 'national rank'},
      {key: 'country', label: 'Location'},
      {key: 'quality_of_Faculty',  label: 'quality of Faculty'},
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
        .then(response => {this.CWUR_rankings = response.data})
      axios
        .get('http://localhost:8085/timesrankings/'+localStorage.getItem('currentU'))
        .then(response => (this.Times_rankings = response.data))
      
      axios
        .get('http://localhost:8085/discussion/' + localStorage.getItem('currentU') )
        .then(response => {this.Discussion = response.data})
    },
  
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
          
          axios.post(`http://localhost:8085/discussion/post`, {
            "userId": user.userId,
            "discussionContent": this.form.discussionContent,
            "universityName": localStorage.getItem('currentU')
          },
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
        let user = VueCookies.get("user");
        console.log("user",user.isStudent)

          console.log(this.Discussion[index].user)
        if (user.isStudent != 0){
          if (this.Discussion[index].user.userId == user.userId) {
          this.isAdd = false;
          this.form = item;
          this.isShow = true;
          }
        }
        else
        { this.isAdd = false;
        this.form = item;
        this.isShow = true;
        }
      
      } ,
      resetEditModal() {
        this.form = {
          discussionContent: '',
          universityName:'',
          userId:'',
          discussionId:''
        };
      },
    
    
      add111(item, index, button){

let user = VueCookies.get("user")
if (user == null) {
  alert("User is not logged in");
}
let userId = user.userId;

axios
  .get('http://localhost:8085/universitiesByType/0/'+ item.qs_ranking_id)
  .then(response => {
    this.All_universities = response.data[0].universityId;
  }).then(() => {
    axios
    .post('http://localhost:8085/university/interestlist/add',
    {
      "studentId": String(userId),
      "comment": "",
      "universityId": String(this.All_universities),
      "type": 0,
      "listOrder": item.qs_ranking_id

    })
    .then((response) => {
      console.log(response);
      let data = response.data;
      if (data == "") {
        alert("already added");
        return;
      }
      this.init();
      alert("Add Success") 
    })
    .catch(function (error){
        alert("Add Exception") 
        console.log(error);
    })
  })

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