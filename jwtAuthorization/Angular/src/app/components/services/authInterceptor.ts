import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";

const   TOKEN_HEADER='Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private loginServ:LoginService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("inside intercep")
        let authReq=req;
        const token=this.loginServ.getToken();
        console.log(token+"token inter");
        if(req.url!='http://localhost:8080/token'){
            if(token!='' && token !=null && token!=undefined){
                authReq = authReq.clone({ setHeaders: { 
                'Content-Type': 'application/json; charset=utf-8',
                Accept: 'application/json',
                Authorization: `Bearer ${token}`} });

                //authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER, 'Bearer ' + token) });
            }
        }
        
        return next.handle(authReq);
    }
}
export const AuthInterceptorProviders=[
    {
        provide:HTTP_INTERCEPTORS,
        useClass:AuthInterceptor,
        multi:true
    }
]