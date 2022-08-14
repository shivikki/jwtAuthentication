import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url="http://localhost:8080";
  
  //import httpClient in service
  constructor(private httpClient:HttpClient) { }

  generateToken(credentials:any){
    console.log("token is generated");
    return this.httpClient.post(`${this.url}/token`,credentials);
    //return Observable
  }

  //set token in localstorage
  loginUser(token: string){
    localStorage.setItem("token",token);
    console.log("token set in local storage");
    return true;
  }

  //to check user logged in or not
  isLoggedIn(){
    let token=localStorage.getItem("token");
    if(token==undefined || token=='' || token==null){
      return false;
    }
    else{
      return true;
    }
  }

  //log out user
  logout(){
    localStorage.removeItem('token');
    return true;
  }

  getToken(){
    return localStorage.getItem('token');
  }
}
