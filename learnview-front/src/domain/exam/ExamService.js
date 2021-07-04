export default class ExamService {

    constructor(http) { 
        this._http = http;
    }

    list() {
         
      //  console.log("chamou LIST!");
        
        return this._http
            .get('http://localhost:9000/api/exams')
            .then(
                res => res.json(),
                err => {
                    console.log(err);
                    throw new Error('Unable to get exams list');
                }
            )
            
    }

}