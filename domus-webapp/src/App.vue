<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="display-2 text-uppercase">
        <span>
          <p class="ma-0" style="color: #2E86C1">domus</p>
        </span>
      </v-toolbar-title>

      <v-spacer></v-spacer>
      <!-- <v-btn v-show="userLoggedIn" text @click="logoutnow">Log out</v-btn> -->
      <v-btn text href="https://github.com/vuetifyjs/vuetify/releases/latest" target="_blank">
        <span class="mr-2">{{username}}</span>
      </v-btn>
    </v-app-bar>

    <v-content>
      <router-view @loggedInInfo="login"></router-view>
    </v-content>
  </v-app>
</template>

<script>
import HelloWorld from "./components/HelloWorld";
import Axios from "axios";

export default {
  name: "App",

  data: () => ({
    username: "",
    loggedIn: false,
    showlogout: false
  }),
  computed: {
    // userLoggedIn() {
    //   if (window.location.pathname == "/login") {
    //     return false;
    //   } else return true;
    // }
  },
  methods: {
    logoutnow() {
      localStorage.removeItem("doublechecklogin");
      this.$router.push("login");
    },
    login(bankDetails) {
      // console.log("bankDetails" + JSON.stringify(bankDetails));
      // this.bankDetails = bankDetails;
      //send get request with a token
      // var responseData =
      // if (username == "tenant") {
      //   this.username = username;
      //   localStorage.setItem(
      //     "authenticatedUser",
      //     JSON.stringify({ name: username })
      //   );
      //   this.$router.push("tenant");
      // }
      // Axios({
      //   method: "post",
      //   url: "http://10.255.255.217:8080/api/v1/auth/login",
      //   headers: {},
      //   data: {
      //     username: "user",
      //     password: "user"
      //   }
      // }).then(response => {
      // });
    }
  }
};
</script>
