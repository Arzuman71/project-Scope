import axios from "axios";

const USER_REGISTER_API_URL = '/user';
const USER_LOGIN_API_URL = '/user/auth';

class UserService {


    getUser() {
        return axios({
            method: 'get', url: '/user',
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }

    getUsers() {
        return axios({
            method: 'get', url: '/users',
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }

    register(user) {
        return axios.post(USER_REGISTER_API_URL, user);
    }

    login(user) {
        return axios.post(USER_LOGIN_API_URL, user)
            .then(res => {
                localStorage.setItem('token', res.data.token)
                localStorage.setItem('type', res.data.type)
            })
            .catch(err => {
                console.log()
            })
    }

}


export default new UserService();