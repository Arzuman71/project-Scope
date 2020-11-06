import axios from "axios";


class MainService {

    getImage(name) {
        return axios({
            method: 'get', url: '/userLeader/projects',data: name
        })
    }   
         }
         export default new MainService();