import axios from "axios";

export default class QuestionService {

    constructor(api_url) { 
        this._api_url = api_url;
    }
 
    exportJSON(question) {
          
       // console.log("service JSON:"+question );
       console.log("POST: "+ this._api_url + '/api/question/new')

        return  axios
            .post(this._api_url + '/api/question/new',question)
            .then(res => res.data,
                  err => {
                    console.log(err);
                    throw new Error('Unable to submit question');
                }
            )
            
    }

}