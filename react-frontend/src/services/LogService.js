import axios from "axios";

class LogService {

    getLogs() {
        return axios({
            method: 'get', url: '/logs',
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }


    addLog(log) {
        return axios({
            method: 'post', url: '/log', data: log,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }
    logDelete(id) {
        return axios({
            method: 'delete', url: '/log/' + id,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }
}


export default new LogService();