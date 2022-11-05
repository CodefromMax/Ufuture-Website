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
    <b-modal id="edit-modal" title="Edit note" @hide="resetEditModal" hide-footer>
      <b-form>

        <label class="sr-only" for="input-listId">List ID</label>
        <b-form-input
          id='input-listId'
          v-model="form.listId"
          placeholder="List ID"
          readonly
        ></b-form-input>

        <label class="sr-only" for="input-University-name">University Name</label>
        <b-form-input
          id='input-University-name'
          v-model="form.University_name"
          placeholder="University Name"
          required
        ></b-form-input>


        <label class="sr-only" for="input-comment">Note</label>
        <b-input-group>
          <b-form-input id="input-comment" v-model="form.comment" placeholder="Note" required></b-form-input>
        </b-input-group>
        
        <br />
        <b-button type="button" @click="onSave" variant="primary">Save</b-button>
        <b-button type="reset" variant="warning">Reset</b-button>
        <b-button type="button" @click = "deletel" variant="danger">Remove University</b-button>
      </b-form>

    </b-modal>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HelloWorld',
  data () {
    return {
      Interest_list: null,
      fields: [
      {key: 'listId', label: 'List ID', sortable: true},
      {key: 'UniversityName', label: 'University Name', sortable: true},
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
    init() {
      axios
        .get('http://localhost:8085/university/interestlist/showall')
        .then(response => {this.Interest_list = response.data, console.log(response.data)})
    },
    edit(item, index, button) {
      this.form.listId = item.listId
      this.form.comment = item.comment
      this.form.University_name = item.UniversityName
    },
    resetEditModal() {
      this.form.listId=''
      this.form.comment=''
      this.form.University_name=''
    },
    onSave(event) {
      var numId;
      numId = parseInt(this.form.listId);
      axios
        .put('http://localhost:8085/university/interestlist/comment/' + numId, {
          "listId": numId,
          "UniversityName": this.form.University_name,
          "comment": this.form.comment,
        })
        .then(() => this.init())
        .catch(function (error) {
          console.log(error);
        });
    },
    deletel(event) {
      var numId;
      numId = parseInt(this.form.listId);
      axios
        .delete('http://localhost:8085/university/interestlist/' + numId, {
          "listId": numId,
          "UniversityName": this.form.University_name,
          "comment": this.form.comment,
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
</style>
