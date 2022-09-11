import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isUserAct=false;
  username:any=null;
  name=null;

  constructor(public loginServ:LoginService) { }

  ngOnInit(): void {
    this.isUserAct=this.loginServ.isLogin();
    this.username=this.loginServ.returnUser();
    this.loginServ.loginStatus.asObservable().subscribe(
      (data: any)=>{
        this.isUserAct=this.loginServ.isLogin();
        this.username=this.loginServ.returnUser();
        
       
        console.log(this.username,"user nav");
      }
    )
   
  }

  logout(){
    localStorage.clear();
    this.loginServ.CurrentUser=null;
    this.username=null;
    this.isUserAct=false;
    window.location.reload();
  }
}
