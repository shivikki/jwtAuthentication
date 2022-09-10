import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, map, Observable, throwError } from "rxjs";
import { LoginService } from "./login.service";

const TOKEN_HEADER = "Authorization";
// export const AuthInterceptorProviders=[
//     {
//         provide:HTTP_INTERCEPTORS,
//         useClass:AuthInterceptor,
//         multi:true
//     }
// ]


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    //implement HttpInterceptor then add unimplemenyed m/d
    constructor(private loginService: LoginService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let newReq = req;
        let token = this.loginService.getToken();
        console.log("interceptor", token);
        console.log("get url called", req.url);
        if (req.url != 'http://localhost:8080/token' && req.url != 'http://localhost:8080/token') { //authorixation header no need to pass in /token url
            if (token != null && token.length != 0) {
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
}
//configure this const in app.module
export const AuthInterceptorProviders=[
    {
        provide:HTTP_INTERCEPTORS,
        useClass:AuthInterceptor,
        multi:true
    }
]
