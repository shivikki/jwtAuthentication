import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor(private loginServ:LoginService,private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    var getRole=this.loginServ.getRoleTagged();
   
    //cond valid user + admin then allow access to url
    if(this.loginServ.isLogin() && getRole[0].roleName=='Admin'){
      return true;
    }
    else{
      this.router.navigate(['login']);
      return false;
    }
  }
  
}
