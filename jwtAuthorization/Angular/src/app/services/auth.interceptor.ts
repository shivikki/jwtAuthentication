import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, map, Observable, throwError } from "rxjs";
import { LoginService } from "./login.service";


//simply declare a class using export class <class name>
//implement the m/d of HttpInterceptor
//add annotation @Injectable - used for dependency injection
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private loginService: LoginService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let newReq = req;
        let token = this.loginService.getToken();
        console.log("interceptor", token);
        console.log("get url called", req.url);
        if(req.url!='http://localhost:8080/token'){ //authorixation header no need to pass in /token url
            if (token != null && token.length!=0) {
                console.log("token not null");
                newReq = newReq.clone({ setHeaders: { Authorization: `Bearer ${token}` } });
            }
            else {
                console.log("no token present");
                window.location.href = '/login';
            }
        }
       
        // return next.handle(newReq).pipe(catchError(err=>{
        //     if (err instanceof HttpErrorResponse){
        //         if(err.status===401){
        //             console.log("Error"+err.error);
        //         }
        //     }
        // }

        return next.handle(newReq)
            .pipe(
                map(res => {
                    console.log("Passed through the interceptor in response");
                    return res
                }),
                catchError((error: HttpErrorResponse) => {
                    let errorMsg = '';
                    if (error.error instanceof ErrorEvent) {
                        console.log('This is client side error');
                        errorMsg = `Error: ${error.error.message}`;
                    } else {
                        console.log('This is server side error');
                        errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
                    }
                    console.log(errorMsg);
                    localStorage.clear;
                  // window.location.href = '/login';
                    return throwError(errorMsg);
                })
            );


    }
    //set it in providers of app.module.ts

    // intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    //     console.log("Passed through the interceptor in request");

    //     return next.handle(request)
    //         .pipe(
    //             map(res => {
    //                 console.log("Passed through the interceptor in response");
    //                 return res
    //             }),
    //             catchError((error: HttpErrorResponse) => {
    //                 let errorMsg = '';
    //                 if (error.error instanceof ErrorEvent) {
    //                     console.log('This is client side error');
    //                     errorMsg = `Error: ${error.error.message}`;
    //                 } else {
    //                     console.log('This is server side error');
    //                     errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
    //                 }
    //                 console.log(errorMsg);
    //                 return throwError(errorMsg);
    //             })
    //         )
    // }
}

