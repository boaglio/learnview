import axios from "axios";

export default class ExamService {

    constructor(api_url) { 
        this._api_url = api_url;
    }

    list() { 

      console.log("GET: "+this._api_url + '/api/exams')

        return axios
            .get(this._api_url + '/api/exams')
            .then(
                res => res.data,
                err => {
                    console.log(err);
                    throw new Error('Unable to get exams list');
                }
            )
            
    }

}