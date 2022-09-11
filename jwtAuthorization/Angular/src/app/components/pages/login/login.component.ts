import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs/internal/Subject';
import { getUserByEmail, roleDetails, token } from 'src/app/helper/api-constant';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLogin = {
    password: "",
    email: ""
  }
  public validUser=true;

  constructor(private loginServ: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUserRestCall() {
    this.loginServ.loginUser(this.userLogin, token).subscribe(
      (data) => {
        var tokenRX: any;
        tokenRX = data;
        this.validUser=true;
        if (tokenRX.token == undefined || tokenRX.token == '' || tokenRX.token == null ||
        tokenRX.token=='BAD CREDENTIALS') {

          console.log(tokenRX.token, "after login invalid user");
          this.validUser=false;
        }
        else {
          this.loginServ.setToken(tokenRX.token);
          //this.loginServ.setuserDetails(this.userLogin);
          this.validUser=true;
          
          this.getCurrentUserRest();
          //this.getUserRoles();

        }
        console.log(tokenRX.token, "adter login");

      },
      (error) => {
        console.error(error);
      }
    )
  }

  getUserRoles() {
    this.loginServ.getRole(this.userLogin.email, roleDetails).subscribe(
      (data) => {
        var roleData: any = data;
        this.loginServ.setRoleTagged(roleData);
        console.log("role set",this.loginServ.getRoleTagged())
        console.log("roles", data);
        if (roleData != null && roleData.length != 0) {
          if (roleData[0].roleName == 'Normal') {
            console.log("normal user");
            this.loginServ.loginStatus.next(true);
            this.router.navigate(["/profile"]);
          }
          else if (roleData[0].roleName == 'Admin') {
            console.log("Admin user");
            this.loginServ.loginStatus.next(true); //subcribe this in navbar
            this.router.navigate(["/admin"]);
          }
          else {
            console.log("No user role tagged");
          }
        }

      },
      (error) => {
        console.error(error);
      }
    )
  }

  submitRegistration(form: any) {
    if (form.status == "VALID") {
      console.log(this.userLogin, "userLogin");
      this.loginUserRestCall();

    }
    else {
      console.log("Invalid credebtials")
    }
  }

  getCurrentUserRest(){
    this.loginServ.getCurrentUser(this.userLogin.email).subscribe(
      (data)=>{
        console.log(data,"current user");
        var currUser:any=data;
        this.loginServ.setCurrentUser(currUser);
        this.getUserRoles();
       // this.router.navigate(["/profile"]);
      },
      (error)=>{
        console.error(error);
      }
    );
  }
}


