<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title>Login form</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="loginForm"
                    label="Login"
                    name="login"
                    prepend-icon="person"
                    type="text"
                  ></v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="lock"
                    type="password"
                    v-model="passwordForm"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <!-- <v-btn color="primary" style="color: white;" @click="register">register</v-btn> -->
                <p
                  v-if="accountNotLinked"
                  style="color: blue;max-width: 200px"
                >Please link your account with the Bank Of Cyprus!</p>
                <v-spacer v-if="accountNotLinked"></v-spacer>
                <v-btn v-if="accountNotLinked" color="primary" @click="linkAccount">Link Account</v-btn>
                <p class="ml-2" v-if="linkAccountUrl">Your accout is linking..</p>
                <v-progress-circular
                  class="mb-3 ml-3"
                  v-if="linkAccountUrl"
                  :size="30"
                  color="primary"
                  indeterminate
                ></v-progress-circular>
                <v-spacer v-if="!accountNotLinked"></v-spacer>
                <v-btn color="primary" @click="login">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import Axios from "axios";
import { serverRequests } from "../services/ApiService";
export default {
  props: {
    source: String
  },
  data: () => ({
    linkAccountUrl: false,
    drawer: null,
    loginForm: "",
    passwordForm: "",
    accountNotLinked: false,
    justlinked: false,
    authenticated: false
  }),
  methods: {
    linkAccount() {
      window.location.href =
        "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/oauth2/authorize?response_type=code&redirect_uri=http://localhost:8080/linkAccount&scope=UserOAuth2Security&client_id=9bd19082-b319-404d-b54e-556a791bb404&subscriptionid=Subid000001-1571498386224";
    },
    register() {},
    async login() {
      var parent = this;

      await serverRequests
        .post({
          username: this.loginForm,
          password: this.passwordForm
        })
        .then(response => {
          // parent.authenticated = true;
          Axios.interceptors.request.use(function(config) {
            const token = response.data.token;
            config.headers.Authorization = `Bearer ${token}`;

            var authUserNotParsed = localStorage.getItem("AuthUser");
            if (authUserNotParsed == null || authUserNotParsed == undefined) {
              var authUser = [];
            } else {
              var authUser = JSON.parse(authUserNotParsed);
            }

            authUser = {
              username: parent.loginForm,
              password: parent.passwordForm,
              token: token
            };

            localStorage.setItem("AuthUser", JSON.stringify(authUser));
            return config;
          });
        })
        .then(() => {
          localStorage.setItem(
            "CurrentLoggedInUser",
            JSON.stringify({
              username: this.loginForm,
              password: this.passwordForm
            })
          );
        })
        .catch(error => console.log(error));

      // if (this.authenticated) {
      if (
        localStorage.getItem("AuthUser") != null &&
        localStorage.getItem("AuthUser") != undefined
      ) {
        await serverRequests.get("/api/v1/users/me").then(response => {
          if (response.data.bankDetails == null) {
            this.accountNotLinked = true;
          } else {
            switch (this.loginForm) {
              case "user":
                localStorage.setItem(
                  "doublechecklogin",
                  JSON.stringify({
                    username: "ok"
                  })
                );
                this.$router.push("tenant");
                break;
              case "admin":
                localStorage.setItem(
                  "doublechecklogin",
                  JSON.stringify({
                    username: "ok"
                  })
                );
                this.$router.push("manager");
                break;
            }
          }
        });
      }

      this.authenticated = false;
    }
  },
  created() {
    // var a = localStorage.getItem("AuthUser", JSON.stringify(authUser));
    // Axios.interceptors.request.use(function(config) {
    //   const token = localStorage.getItem("AuthUser");
    //   config.headers.Authorization = `Bearer ${token}`;

    //   return config;
    // });
    if (window.location.pathname == "/linkAccount") {
      this.linkAccountUrl = true;
      var code = window.location.search.substring("?code=".length);
      var currentUser = JSON.parse(localStorage.getItem("CurrentLoggedInUser"));

      serverRequests.postCode({ code: code }).then(response => {
        if (response.data) {
          this.linkAccountUrl = false;
          this.accountNotLinked = false;
          var user = JSON.parse(localStorage.getItem("CurrentLoggedInUser"));
          switch (user.username) {
            case "user":
              this.$router.push("tenant");
            case "admin":
              this.$router.push("manager");
          }
        }
      });
    }
  }
};
</script>