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
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AuthService {

  private apiUrl: string;

  loginUserName = '';
  isLoggedIn = false;
  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(
    @Inject(APP_CONFIG) private config: AppConfig,
    private http: HttpClient,
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

  getCaptchaSrc(): any {
    const url = `${this.apiUrl}/api/captcha/160x30x6x0`;
    return this.http.get(url)
      .toPromise()
      .then(data => data)
      .catch(this.loggerService.handleError);
  }

  getRSAPublicKey(): Promise<string> {
    const url = `${this.apiUrl}/api/secret`;
    return this.http.get(url)
      .toPromise()
      .then(response => response)
      .catch(this.loggerService.handleError);
  }

  setLoginInfo(userSession: UserSession): void {
    this.isLoggedIn = true;
    this.loginUserName = userSession.userName;
    localStorage.setItem('id_token', userSession.jwtToken);
    localStorage.setItem('loginUserName', this.loginUserName);
  }

  setLogoutInfo(): void {
    this.isLoggedIn = false;
    this.loginUserName = '';
    localStorage.removeItem('id_token');
    localStorage.removeItem('session_id');
    localStorage.removeItem('loginUserName');
  }

  login(data): Promise<any> {
    const url = `${this.apiUrl}/api/login`;
    return this.http.post(url, data)
      .toPromise()
      .then(response => response)
      .catch(this.loggerService.handleError);
  }

  logout(): Observable<boolean> {
    return Observable.of(true).do(val => this.setLogoutInfo());
  }

}
