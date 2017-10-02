import {Injectable} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import { Observable } from "rxjs/Rx";
import { HttpHeaders } from "@angular/common/http";
import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { CONSTANTS } from "app/app.const";
import { Router } from "@angular/router";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const idToken = localStorage.getItem('id_token') || "";
    const authReq = req.clone({headers: req.headers.set('Content-Type', 'application/json').set('X-API-Token', idToken)});
    return next.handle(authReq).catch(err => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === CONSTANTS.HTTPStatus.UNAUTHORIZED) {
              this.router.navigate([CONSTANTS.ROUTE_URL.signin]);
            } else if (err.status === CONSTANTS.HTTPStatus.FORBIDDEN) {
              console.error("No Access!");
            } else if (err.status === CONSTANTS.HTTPStatus.GATEWAY_TIMEOUT) {
              console.error("API server timeout!");
            } else if (err.status === CONSTANTS.HTTPStatus.INTERNAL_SERVER_ERROR) {
              console.error("API server 500 error!");
            } else if (err.status === CONSTANTS.HTTPStatus.SUCCESS) {
            } else {
            }
          }
          return Observable.throw(err);
      });
  }
}
