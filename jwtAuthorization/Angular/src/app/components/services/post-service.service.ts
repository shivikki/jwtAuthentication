import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { baseUrl } from 'src/app/helper/api-constant';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {
 
  constructor(private http: HttpClient) { }

  //passing string in RequestBody
  public getJsonPassString(request: string, url: string) {
    JSON.stringify(request);
    return this.http.post(`${baseUrl}${url}`, request);
  }

  //passing sint in RequestBody for deleting
  public deleteJsonPassInt(request: Number, url: string) {
   // JSON.stringify(request);
    let id=Number(request);
    console.log(request)
    return this.http.post(`${baseUrl}${url}`,request);
  }

  //get mapping
  public getMapping(url: string) {
    return this.http.get(`${baseUrl}${url}`);
  }

  //passing json in requestBody
  public postJsonPassData(request: any, url: string) {
    JSON.stringify(request);
    return this.http.post(`${baseUrl}${url}`, request);
  }

  //pass id with url
  public postDataFromUrl(req: any, url: string) {
    //JSON.stringify(request);
    const params = new HttpParams().set('id', req);
    return this.http.post(`${baseUrl}${url}`,{params})
  }

}
