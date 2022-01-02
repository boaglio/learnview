import axios from "axios";

export default class TestService {
 
    constructor(api_url) { 
        this._api_url = api_url;
    }
 
    list() {
   
        console.log("GET: "+this._api_url + '/api/tests')

        return axios
            .get(this._api_url + '/api/tests')  
            .then(res => res.data,
                  err => {
                    console.log(err);
                    throw new Error('Unable to get tests');
                }
            )
            
    }

}