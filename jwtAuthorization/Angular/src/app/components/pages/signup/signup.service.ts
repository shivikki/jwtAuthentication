import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { addUser, baseUrl } from 'src/app/helper/api-constant';
@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http:HttpClient) { }
  //baseUrl="http:/localhost:8080/";

  //call on registering the user
  public addUser(user:any){
    return this.http.post(`${baseUrl}${addUser}`,user);
    //pass url & data
  }

}
