import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public loggedIn=false;

  constructor(private loginServe:LoginService) { }

  ngOnInit(): void {
    this.loggedIn=this.loginServe.isLoggedIn();
  }

  logoutUser(){
    this.loginServe.logout();
    //relaod page then only token changes will reflect'
    //reloads automatically after logout
    location.reload();
  }
}
