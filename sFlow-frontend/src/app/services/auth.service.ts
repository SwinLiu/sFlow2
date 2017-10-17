import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/map';

import { APP_CONFIG, AppConfig } from '../app-config.module';

import { LoggerService } from './logger.service';

import 'rxjs/add/operator/toPromise';
import { UserSession } from 'app/beans/userSession';
import { TokenService } from "@core/net/token/token.service";
import { _HttpClient } from "@core/services/http.client";
import { CONSTANTS } from "app/app.const";
import { Router, NavigationExtras } from '@angular/router';

@Injectable()
export class AuthService {

  private apiUrl: string;

  loginUserName = '';
  isLoggedIn = false;
  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(
    @Inject(APP_CONFIG) private config: AppConfig,
    private router: Router,
    private tokenService: TokenService,
    private http: _HttpClient,
    private loggerService: LoggerService
  ) {
    this.apiUrl = config.apiUrl;
    const idToken = localStorage.getItem('id_token');
    if (idToken != null) {
      // sessionId
      this.isLoggedIn = true;
      this.loginUserName = localStorage.getItem('loginUserName');
    }
  }

  checkToken(data): void {

  }

  getCaptchaSrc(): Promise<any> {
    const url = `${this.apiUrl}/api/captcha/160x30x6x0`;
    return new Promise((resolve, reject) => {
      this.http.get(url)
        .subscribe(data => resolve(data));
    });
  }

  getRSAPublicKey(): Promise<string> {
    const url = `${this.apiUrl}/api/secret`;
    return this.http.get(url)
      .toPromise()
      .then(response => response)
      .catch(this.loggerService.handleError);
  }

  setLoginInfo(userSession: UserSession): void {
    this.tokenService.login(userSession.jwtToken);
    // this.isLoggedIn = true;
    // this.loginUserName = userSession.userName;
    // localStorage.setItem('id_token', userSession.jwtToken);
    // localStorage.setItem('loginUserName', this.loginUserName);
  }

  setLogoutInfo(): void {
    this.tokenService.logout();
    // this.isLoggedIn = false;
    // this.loginUserName = '';
    // localStorage.removeItem('id_token');
    // localStorage.removeItem('session_id');
    // localStorage.removeItem('loginUserName');
  }

  login(data): Promise<any> {
    const url = `${this.apiUrl}/${CONSTANTS.API_URL.login}`;
    return new Promise((resolve, reject) => {
      this.http.post(url, data).subscribe(response => resolve(response));
    });
  }

  logout(): void {
    const url = `${this.apiUrl}/${CONSTANTS.API_URL.logout}`;
    this.http.get(url).subscribe(response => {
      this.setLogoutInfo();
      // Get the redirect URL from our auth service
      // If no redirect has been set, use the default
      const redirect = CONSTANTS.ROUTE_URL.signin;
      
      // Set our navigation extras object
      // that passes on our global query params and fragment
      const navigationExtras: NavigationExtras = {
        preserveQueryParams: true,
        preserveFragment: true
      };
      // Redirect the user
      this.router.navigate([redirect], navigationExtras);
    });
    
  }

}
