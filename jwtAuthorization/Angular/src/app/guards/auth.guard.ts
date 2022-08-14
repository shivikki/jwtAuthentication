import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  //make constructor
  constructor(private loginService:LoginService,private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {


      //code added starts
      if(this.loginService.isLoggedIn()){
        return true;
      }
      //add auth guard in routing file for the url u want to protect

      //if not authorized take back user ro login screen
      console.log("Auth gueard checked")
      //this.router.navigate(['login'])
      //code added ends

    return false;
  }
  
}
