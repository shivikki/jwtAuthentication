import { Component, OnInit } from '@angular/core';
import { roleDetails } from 'src/app/helper/api-constant';
import { LoginService } from '../../services/login.service';
import { PostServiceService } from '../../services/post-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public userRole:any;
  public profileMsg="";
  constructor(private loginServe:LoginService,private apiPost:PostServiceService) { }

  ngOnInit(): void {
    this.userRole=this.loginServe.getRoleTagged();
    console.log(this.userRole,"userRolw")
    // if(this.userRole.roleName=='Admin'){
    //   this.profileMsg="Welcome to Admin portal Dashboard";
    // }
    // else{
    //   this.profileMsg="Welcome to Exam Portal";
    // }
    this.getUserRoles();
  }

  getUserRoles() {
    this.loginServe.getRole('ria@gmail.com', roleDetails).subscribe(
      (data) => {
        var roleData: any = data;
       // this.loginServ.setRoleTagged(roleData);
        console.log("roles", data);
       

      },
      (error) => {
        console.error(error);
      }
    )
  }

  call(){
    this.getUserRoles();
  }

}
