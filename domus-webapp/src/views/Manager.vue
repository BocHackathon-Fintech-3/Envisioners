<template>
  <v-layout>
    <v-tabs v-model="tabsModel" fixed-tabs>
      <v-tab v-for="tab in tabs" :key="tab.id">{{ tab.name }}</v-tab>

      <v-tabs-items v-model="tabsModel">
        <v-tab-item>
          <v-layout class="pt-5" ali gn-center>
            <v-flex>
              <v-layout align-center column>
                <v-card flat>
                  <v-card-title>This Month's Expenses</v-card-title>
                </v-card>
                <div style="border: 1px solid black;border-radius: 3px;width: 40%">
                  <v-list style="max-height: 250px;width:100%" class="overflow-y-auto">
                    <template>
                      <div v-for="(expense,index) in currentMonthExpenses" :key="index">
                        <v-list-item>
                          <v-list-item-content>
                            <v-list-item-title
                              style="text-align: center;font-size: 0.92em;"
                            >{{expense.name}}, Amount: {{expense.ammount}}</v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                        <v-divider></v-divider>
                      </div>
                    </template>
                  </v-list>

                  <div style="width:100%;" class="borderTop">
                    <p>This Month's Expenses: $2045</p>
                  </div>
                  <div style="width:100%" class="borderTop">
                    <p>Total Payed: $1000</p>
                  </div>

                  <v-list-group
                    class="borderTop"
                    id="list_group"
                    style="width: 100%"
                    v-model="toPayListMonth"
                    append-icon
                  >
                    <template v-slot:activator>
                      <v-list-item class="pl-0">
                        <v-list-item-content>
                          <v-list-item-title>
                            <v-layout>
                              <v-flex>
                                <p>To Pay: $1045</p>
                              </v-flex>
                              <v-flex>
                                <v-icon style="text-align: center;">fas fa-chevron-down</v-icon>
                              </v-flex>
                            </v-layout>
                          </v-list-item-title>
                        </v-list-item-content>
                      </v-list-item>
                    </template>
                    <v-list-item v-for="(expense,index) in thisMonthToPay" :key="index">
                      <v-list-item-content>
                        <v-list-item-title>{{ expense }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list-group>
                </div>
              </v-layout>
            </v-flex>
            <v-flex>
              <v-layout align-center column>
                <v-card flat>
                  <v-card-title>Whole Time Expenses</v-card-title>
                </v-card>
                <div style="border: 1px solid black;border-radius: 3px;width: 40%">
                  <v-list style="max-height: 250px;width:100%" class="overflow-y-auto">
                    <template>
                      <div v-for="(expense,index) in currentMonthExpenses" :key="index">
                        <v-list-item>
                          <v-list-item-content>
                            <v-list-item-title
                              style="text-align: center;font-size: 0.92em;"
                            >{{expense.name}}, Amount: {{expense.ammount}}</v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                        <v-divider></v-divider>
                      </div>
                    </template>
                  </v-list>

                  <div style="width:100%;" class="borderTop">
                    <p>Whole Time Expenses: $15000</p>
                  </div>
                  <div style="width:100%" class="borderTop">
                    <p>Total Payed: $9000</p>
                  </div>

                  <v-list-group
                    class="borderTop"
                    id="list_group"
                    style="width: 100%"
                    v-model="toPayListWhole"
                    append-icon
                  >
                    <template v-slot:activator>
                      <v-list-item class="pl-0">
                        <v-list-item-content>
                          <v-list-item-title>
                            <v-layout>
                              <v-flex>
                                <p>To Pay: $6000</p>
                              </v-flex>
                              <v-flex>
                                <v-icon style="text-align: center;">fas fa-chevron-down</v-icon>
                              </v-flex>
                            </v-layout>
                          </v-list-item-title>
                        </v-list-item-content>
                      </v-list-item>
                    </template>
                    <v-list-item v-for="(expense,index) in thisMonthToPay" :key="index">
                      <v-list-item-content>
                        <v-list-item-title>{{ expense }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list-group>
                </div>
              </v-layout>
            </v-flex>
            <v-flex>
              <v-layout class="pt-5" align-center column>
                <p>Your bank details:</p>
                <p>Iban: 111000278394271</p>
                <p>Name: Chris Paraskevas</p>
              </v-layout>
            </v-flex>
          </v-layout>
        </v-tab-item>
        <v-tab-item>
          <v-layout class="pt-5">
            <v-flex>
              <v-layout align-center column>
                <v-select
                  v-model="select"
                  :items="currentMonthExpenses"
                  menu-props="auto"
                  label="Flats"
                  hide-details
                  prepend-icon="far fa-building"
                >
                  <template v-slot:item="{item}">
                    <div>
                      <p>
                        Flat Number: item
                        <br />
                      </p>
                      <p>Tenant: item</p>
                    </div>
                    <v-divider></v-divider>
                  </template>
                </v-select>
              </v-layout>
            </v-flex>
            <v-flex>
              <v-layout align-center column>
                <v-layout align-center column>
                  <div style="border: 1px solid black;border-radius: 3px;width: 100%">
                    <v-list-group
                      class="borderTop"
                      style="width: 100%"
                      v-model="paymentsHistoryGroup"
                      append-icon
                    >
                      <template v-slot:activator>
                        <v-list-item>
                          <v-list-item-content>
                            <v-list-item-title>
                              <v-layout>
                                <v-flex>
                                  <h3>Payments History</h3>
                                </v-flex>
                                <v-flex>
                                  <v-icon style="text-align: center;">fas fa-chevron-down</v-icon>
                                </v-flex>
                              </v-layout>
                            </v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </template>
                      <v-list-item v-for="(expense,index) in thisMonthToPay" :key="index">
                        <v-list-item-content>
                          <!-- <v-list-item-title>{{ expense }}</v-list-item-title> -->
                          <v-list-group
                            class="borderTop"
                            style="width: 10%"
                            v-model="toPayListMonth"
                            append-icon
                          >
                            <template v-slot:activator>
                              <v-list-item>
                                <v-list-item-content>
                                  <v-list-item-title>
                                    <v-layout>
                                      <v-flex>
                                        <h3>Month</h3>
                                      </v-flex>
                                      <v-flex>
                                        <v-icon style="text-align: center;">fas fa-chevron-down</v-icon>
                                      </v-flex>
                                    </v-layout>
                                  </v-list-item-title>
                                </v-list-item-content>
                              </v-list-item>
                            </template>
                            <v-list-item v-for="(expense,index) in thisMonthToPay" :key="index">
                              <v-list-item-content>
                                <v-list-item-title>{{ expense }}</v-list-item-title>
                              </v-list-item-content>
                            </v-list-item>
                          </v-list-group>
                        </v-list-item-content>
                      </v-list-item>
                    </v-list-group>
                  </div>
                </v-layout>
              </v-layout>
            </v-flex>
            <v-flex>
              <v-layout align-center column>
                <v-layout align-center column>
                  <div style="border: 1px solid black;border-radius: 3px;width: 100%">
                    <v-list-group
                      class="borderTop"
                      style="width: 100%"
                      v-model="paymentsHistoryGroup"
                      append-icon
                    >
                      <template v-slot:activator>
                        <v-list-item>
                          <v-list-item-content>
                            <v-list-item-title>
                              <v-layout>
                                <v-flex>
                                  <h3>User Details</h3>
                                </v-flex>
                                <v-flex>
                                  <v-icon style="text-align: center;">fas fa-chevron-down</v-icon>
                                </v-flex>
                              </v-layout>
                            </v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </template>
                      <v-list-item v-for="(expense,index) in thisMonthToPay" :key="index">
                        <v-list-item-content>
                          <!-- <v-list-item-title>{{ expense }}</v-list-item-title> -->
                          <v-list-group
                            class="borderTop"
                            style="width: 10%"
                            v-model="toPayListMonth"
                            append-icon
                          >
                            <template v-slot:activator>
                              <v-list-item>
                                <v-list-item-content>
                                  <v-list-item-title>
                                    <v-layout>
                                      <v-flex>
                                        <h3>Month</h3>
                                      </v-flex>
                                      <v-flex>
                                        <v-icon style="text-align: center;">fas fa-chevron-down</v-icon>
                                      </v-flex>
                                    </v-layout>
                                  </v-list-item-title>
                                </v-list-item-content>
                              </v-list-item>
                            </template>
                            <v-list-item v-for="(expense,index) in thisMonthToPay" :key="index">
                              <v-list-item-content>
                                <v-list-item-title>{{ expense }}</v-list-item-title>
                              </v-list-item-content>
                            </v-list-item>
                          </v-list-group>
                        </v-list-item-content>
                      </v-list-item>
                    </v-list-group>
                  </div>
                </v-layout>
              </v-layout>
            </v-flex>
          </v-layout>
        </v-tab-item>
      </v-tabs-items>
    </v-tabs>
  </v-layout>
</template>

<script>
var sse;
export default {
  name: "manager",
  data() {
    return {
      tabs: [
        {
          name: "General Details",
          id: "1"
        },
        { name: "Specific Details", id: "0" }
      ],
      tabsModel: null,
      // currentMonthExpenses: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      currentMonthExpenses: [
        { name: "Airconditioning", ammount: 50 },
        { name: "Plumber", ammount: 100 },
        { name: "Doctor", ammount: 60 },
        { name: "Electrician", ammount: 50 },
        { name: "plumber", ammount: 20 },
        { name: "plumber", ammount: 15 },
        { name: "plumber", ammount: 30 },
        { name: "plumber", ammount: 33 }
      ],
      currentMonthExpensesPayed: [1, 3, 5, 7, 10],
      toPayListMonth: false,
      toPayListWhole: false,
      select: false,
      paymentsHistoryGroup: false
    };
  },
  computed: {
    thisMonthToPay() {
      return this.currentMonthExpenses.filter(
        el => !this.currentMonthExpensesPayed.includes(el)
      );
    }
  },
  mounted() {
    //     (async () => {
    //       sse = this.$sse('http://10.255.255.217:8080/'+ "/events", {
    //           format: "json"
    //         });
    // })();
  }
};
</script>
<style>
#list_group .v-list-group__header {
  padding-left: 0px !important;
}
</style>