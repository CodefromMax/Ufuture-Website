<template>
  <div class="login-container">
    <form @submit.prevent="login">
      <h2 class="mb-3">Login</h2>
      <div class="input">
        <label for="id">User ID</label>
        <input
          class="form-control"
          type="text"
          name="id"
          placeholder="Student/University ID"
          required
        />
      </div>
      <div class="input">
        <label for="password">Password</label>
        <input
          class="form-control"
          type="password"
          name="password"
          placeholder="password"
        />
      </div>
      <div class="alternative-option mt-4">
        You don't have an account? <span @click="moveToRegister">Register</span>
      </div>
      <button type="submit" class="mt-4 btn-pers" id="login_button">
        Login
      </button>
      <div
        class="alert alert-danger fade show mt-5 d-none"
        role="alert"
        id="alert_1"
      >
        Lorem ipsum dolor sit amet consectetur, adipisicing elit.
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="alert"
          aria-label="Close"
        ></button>
      </div>
    </form>
  </div>
</template>

<script>
import VueCookies from 'vue-cookies'
import axios from 'axios';
export default {
  name: 'Login',
  data() {
    return {
      id: "",
      password: "",
    };
  },
  methods: {
    login(submitEvent) {
      this.id = submitEvent.target.elements.id.value;
      this.password = submitEvent.target.elements.password.value;
    
      axios.post('http://localhost:8085/UniversityUsers/login', {userName: this.id, pasword: this.passwrd}).then(res => {
          let data = res.data;
          if (data !== null) {
            this.$root.$emit("authenticated", true);
              this.$root.$emit("authorized", true);
              this.$root.$emit('User',data);
              VueCookies.set("user", data);
              this.$router.push("/");
              this.$router.go(0);
          }
      })

      // axios.all([
      //     axios.get('http://localhost:8085/StudentUsers/'+submitEvent.target.elements.id.value)
      //     .catch(
      //         function(e){console.log('Error', e);}), 
      //    axios.get('http://localhost:8085/UniversityUsers/'+submitEvent.target.elements.id.value)
      //     .catch(
      //        function(e){console.log('Error', e);})
      //     ])
      //     .then(axios.spread((data1, data2) => {
      //     console.log('data1', data1, 'data2', data2)
      //     if (typeof data1 !== 'undefined'){
      //         // this.$emit("authenticated", true);
      //         // this.$emit("authorized", false);
      //         // this.$emit('User',data1.data);
      //         data1.data.isStudent = true;
      //         this.$root.$emit("authenticated", true);
      //         this.$root.$emit("authorized", false);
      //         this.$root.$emit('User',data1.data);
      //         VueCookies.set("user", data1.data);
      //         this.$router.push("/");
      //         this.$router.go(0);
      //     }
      //     else if(typeof data2 !== 'undefined'){
      //         // this.$emit("authenticated", true);
      //         // this.$emit("authorized", true);
      //         // this.$emit('User',data2.data);
      //         data2.data.isStudent = false;
      //         this.$root.$emit("authenticated", true);
      //         this.$root.$emit("authorized", true);
      //         this.$root.$emit('User',data2.data);
      //         VueCookies.set("user", data2.data);
      //         this.$router.push("/");
      //         this.$router.go(0);
      //     }
      //     else{
      //         let alert_1 = document.querySelector("#alert_1");
      //         alert_1.classList.remove("d-none");
      //         alert_1.innerHTML = 'User Does Not Exist!';
      //     }
      // }));

    },
    moveToRegister() {
      this.$router.push("/register");
    },
  },
};
</script>

<style scoped>
.login-container{
  max-width: 70%;
  margin: auto;
}
</style>