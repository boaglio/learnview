export default class TestService {
 
    constructor(http,api_url) { 
        this._http = http;
        this._api_url = api_url;
    }
 
    list() {
   
        return this._http
            .get(this._api_url + '/api/tests')  
            .then(res => res.json(),
                  err => {
                    console.log(err);
                    throw new Error('Unable to get tests');
                }
            )
            
    }

}