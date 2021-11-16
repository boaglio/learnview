export default class QuestionService {

    constructor(http,api_url) { 
        this._http = http;
        this._api_url = api_url;
    }
 
    exportJSON(question) {
         
        const questionStr = JSON.stringify(question);
        
       // console.log("service JSON:"+questionStr);
       console.log(this.api_url + '/api/question/new')

        return this._http
            .post(this._api_url + '/api/question/new',questionStr)
            .then(res => res.json(),
                  err => {
                    console.log(err);
                    throw new Error('Unable to submit question');
                }
            )
            
    }

}