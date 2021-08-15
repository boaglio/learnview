export default class TestService {

    constructor(http) { 
        this._http = http;
    }
 
    newTest(test) {
         
        const testStr = JSON.stringify(test);

        return this._http
            .post('http://localhost:9000/api/test/',testStr)
            .then(res => res.json(),
                  err => {
                    console.log(err);
                    throw new Error('Unable to submit test');
                }
            )
            
    }

}