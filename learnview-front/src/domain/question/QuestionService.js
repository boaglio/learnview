export default class QuestionService {

    constructor(http) { 
        this._http = http;
    }
 
    exportJSON(question) {
         
        const questionStr = JSON.stringify(question);

       // console.log("service JSON:"+questionStr);

        return this._http
            .post('http://localhost:9000/api/question/new',questionStr)
            .then(res => res.json(),
                  err => {
                    console.log(err);
                    throw new Error('Unable to submit question');
                }
            )
            
    }

}