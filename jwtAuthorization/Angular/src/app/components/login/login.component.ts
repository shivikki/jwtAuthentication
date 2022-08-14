import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  credentials={
    username:"",
    password:""
  }
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }

  submitData(){
    if(this.credentials.username!='' && this.credentials.username!=null && this.credentials.password!='' &&
    this.credentials.password!=null )
    {
      console.log("form submitted"+this.credentials);
      //wi;l return observable so have to subscribe
      this.loginService.generateToken(this.credentials).subscribe(
        response=>
        {var token:any=response;
          console.log(token.token,"response");
          this.loginService.loginUser(token.token); //save token in local storage
          window.location.href='/dashboard';
          
        },
        error=>{
          console.error(error,"error")
        }
      )
      //generate token when login successfully
      //htpp://localhost:8080/token generates token. send username & password
    }
    else{
      console.log("fields r empty")
    }
  
  }

}
