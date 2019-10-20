<template>
  <div>
    <v-layout>
      <v-layout ali gn-center>
        <v-flex xs6>
          <v-layout align-center column>
            <v-card outlined width="100%" height="100%">
              <v-layout class="mt-5 mb-5" align-center column>
                <v-card flat min-height="30">Current Fee</v-card>
                <v-card raised>
                  <v-layout align-center column>
                    <v-card-title>130 $</v-card-title>
                  </v-layout>
                </v-card>
                <v-checkbox label="Allow auto-payement"></v-checkbox>
              </v-layout>
            </v-card>
          </v-layout>
        </v-flex>
        <v-flex xs6>
          <v-layout align-center column>
            <v-card outlined width="100%" height="100%">
              <v-layout class="mt-5 mb-5" align-center column>
                <v-card flat min-height="30">Due</v-card>
                <v-card raised>
                  <v-layout align-center column>
                    <v-card-title>200 $</v-card-title>
                  </v-layout>
                </v-card>
                <v-checkbox label="Verified"></v-checkbox>
              </v-layout>
            </v-card>
          </v-layout>
        </v-flex>
      </v-layout>

      <v-tooltip left>
        <template v-slot:activator="{ on }">
          <v-icon class="notificationIcon" v-on="on">fas fa-bell</v-icon>
        </template>
        <span>Notifications</span>
      </v-tooltip>
      <v-tooltip left>
        <template v-slot:activator="{ on }">
          <v-icon class="servicesIcon" v-on="on">fab fa-stripe-s</v-icon>
        </template>
        <span>Standing Services</span>
      </v-tooltip>
    </v-layout>
    <!-- <v-layout>
      <v-flex>
        <v-card outlined width="100%" height="100%">
          <v-layout>
            <v-flex>
              <v-card flat>
                <v-layout align-center column>
                  <div style="width:50%">
                    <v-textarea label="Send message to the owner" hint="Don't be rude ;)"></v-textarea>
                  </div>
                </v-layout>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="primary" @click="login">Send</v-btn>
                </v-card-actions>
              </v-card>
            </v-flex>
            <v-flex></v-flex>
          </v-layout>
        </v-card>
      </v-flex>
    </v-layout>-->

    <div style="width: 50%">
      <v-layout align-center column>
        <div style="width: 40%;">
          <v-textarea label="Send message to the owner" hint="Don't be rude ;)"></v-textarea>
          <v-btn color="primary" @click="login">Send</v-btn>
        </div>
      </v-layout>
    </div>
    <div style="width: 50%"></div>
  </div>
</template>

<script>
import ServerService from "../services/ApiService";
export default {
  name: "tenant",
  beforeRouteEnter(to, from, next) {
    document.getElementById("htmlId").style.overflowY = "hidden";

    next();
  },
  created() {
    ServerService.get;
  }
};
</script>
<style>
.notificationIcon {
  position: absolute;

  right: 10px;
  top: 10px;
}
.servicesIcon {
  font-size: 22px !important;
  position: absolute;

  right: 45px;
  top: 11px;
}
.borderTop {
  border-top: 1px solid black !important;
}
</style>