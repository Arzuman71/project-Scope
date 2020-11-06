import axios from "axios";

class UserProjectsService {

// member
    getProjects() {
        return axios({
            method: 'get', url: '/userMember/projects',
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }

    addUserProjects(projectsIdAndUserId) {
          return axios({
            method: 'post', url: '/projects/user/add', data: projectsIdAndUserId,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }
   
}


export default new UserProjectsService();