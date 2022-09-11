import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public currentUser:any;
  public role:any;
  constructor(private loginServ:LoginService) { }

  ngOnInit(): void {
    this.currentUser=this.loginServ.returnUser();
    console.log(this.currentUser,"admin ");

    this.role=this.loginServ.getRoleTagged();
    console.log("role",this.role)
  }

}
