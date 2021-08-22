export default class ExamService {

    constructor(http,api_url) { 
        this._http = http;
        this._api_url = api_url;
    }

    list() { 
      console.log("NODE_ENV="+process.env.NODE_ENV);

      console.log(this.api_url + '/api/exams')

        return this._http
            .get(this._api_url + '/api/exams')
            .then(
                res => res.json(),
                err => {
                    console.log(err);
                    throw new Error('Unable to get exams list');
                }
            )
            
    }

}