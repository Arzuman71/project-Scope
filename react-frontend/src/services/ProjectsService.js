import axios from "axios";


class ProjectsService {

    getProjects(searchProjects) {
        return axios({
            method: 'post', url: '/userLeader/projects', data: searchProjects,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })

    }

    saveProject(project) {
        return axios({
            method: 'post', url: '/projects', data: project,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }
    projectsDelete(id) {

        return axios({
            method: 'delete', url: '/projects/' + id,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
    }
}


export default new ProjectsService();