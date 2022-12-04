<template>
  <div class="hello">
    <h2> Interest List </h2>
    <b-table striped hover responsive :items="Interest_list" :fields="fields">
      <template #cell(actions)="row">
        <b-button size="sm" v-b-modal.edit-modal @click="edit(row.item, row.index, $event.target)">
          Edit
        </b-button>
      </template>
    </b-table>
    <b-modal id="edit-modal" v-if="isShow" title="Edit note" @hide="resetEditModal" hide-footer>
      <b-form>
        <label class="sr-only" for="id">id</label>
        <b-form-input
          id='input-listId'
          v-model="form.id"
          placeholder="id"
          readonly
        ></b-form-input>

        <label class="sr-only" for="input-comment">Note</label>
        <b-input-group>
          <b-form-input id="input-comment" v-model="form.comment" placeholder="Note" required></b-form-input>
        </b-input-group>
        
        <br />
        <b-button type="button" @click="onSave" variant="primary">Submit</b-button>
        <b-button type="reset" @click="reset" variant="warning">Reset</b-button>
        <b-button type="button" @click = "deletel" variant="danger">Remove University</b-button>
      </b-form>
    
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios';
import VueCookies from 'vue-cookies'
export default {
  name: 'HelloWorld',
  data () {
    return {
      isShow: true,
      Interest_list: null,
      fields: [
      {key: 'id', label: 'id', sortable: true},
      {key: 'niversityName', label: 'University Name', sortable: true},
      {key: 'comment', label: 'Note', sortable: true, sortable: true},
      {key: 'actions', label: 'Actions'}],
      form: {
          comment: '',
          University_name: '',
          listId: ''
        },
    }
  },
  mounted () {
    this.init();
  },
  methods: {
    reset() {
      this.form.comment = ""
    },
    init() {
      let user = VueCookies.get("user");
      if (user == null) {
        alert("User is not logged in");
        return;
      }
    
      axios
        .get('http://localhost:8085/university/interestlist/' + user.userId)
        .then(response => {
          console.log(response.data);

          let data = response.data;
          let preferenceOrder = 0;
          for(let item of data) {
            item.id = ++preferenceOrder;
            let university = item.university;
            if (item.type == 0) {
              item.niversityName = university.qs_rankings && university.qs_rankings.institution_Name;
            }
            if (item.type == 1) {
              item.niversityName = university.cwur_rankings && university.cwur_rankings.institution_Name;
            }
            if (item.type == 2) {
              item.niversityName = university.times_rankings && university.times_rankings.university_Name;
            }
          }
        

          this.Interest_list = data;
        })

    },
    edit(item, index, button) {
      this.isShow = true;
      this.$set(this.form, 'id', item.id);
      this.form.id = item.id;
      this.form.listId = item.listId
      this.form.comment = item.comment;
      this.form.listOrder = item.listKey.interestListOrder;
      this.form.studentId = item.listKey.studentId;
    },
    resetEditModal() {
      this.form.listId=''
      this.form.comment=''
    },
    onSave(event) {
    
      axios
        .put(`http://localhost:8085/university/interestlist/comment/${this.form.studentId}/${this.form.listOrder}`, {
          "comment": this.form.comment,
        })
        .then(() => this.init(), this.isShow = false)
        .catch(function (error) {
          console.log(error);
        });
    },
    deletel(event) {
      axios
        .delete(`http://localhost:8085/university/interestlist/${this.form.studentId}/${this.form.listOrder}`)
        .then(() => this.init(), this.isShow = false)
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
</style>
