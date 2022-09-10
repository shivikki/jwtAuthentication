import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { baseUrl } from 'src/app/helper/api-constant';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  constructor(private http:HttpClient) { }

  //passing string in RequestBody
  public getJsonPassString(request:string,url:string){
    JSON.stringify(request);
    return this.http.post(`${baseUrl}${url}`,request);
  }
}
