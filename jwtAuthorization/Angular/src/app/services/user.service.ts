import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl="http://localhost:8080/api";
  url:any;
  httpOptions={
    headers:new HttpHeaders({
      "Content-Type":"application/json",
    })
  }
  constructor(private http:HttpClient) { }

  getWelcomeText(){
    this.url='/welcome';
    return this.http.get(`${this.baseUrl}${this.url}`,{responseType:'text'});
    //eturn this.httpClient.post(`${this.url}/token`,credentials);
  }
}
