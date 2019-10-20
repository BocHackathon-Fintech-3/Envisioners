import axios from "axios";

const ApiService = {

    get(resource) {
        return axios.get(`${resource}`).catch(error => {
            throw new Error(`[Dealio] ApiService ${error}`);
        });
    },

    post(resource, params) {
        return axios.post(resource, params);
    },

};

export default ApiService;

export const serverRequests = {
    get(resource) {
        return ApiService.get(resource);
    },
    post(params) {
        return ApiService.post("/api/v1/auth/login", params);
    },
    postCode(params) {
        return ApiService.post("/api/v1/users/linkAccount", params);
    }
};
