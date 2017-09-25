import { Injectable, Inject } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/map';

import { APP_CONFIG, AppConfig } from '../app-config.module';

import { LoggerService } from './logger.service';

import 'rxjs/add/operator/toPromise';
import { UserSession } from "app/beans/userSession";

@Injectable()
export class AuthService {

  private apiUrl: string;
  private headers;

  loginUserName = "";
  isLoggedIn = false;
  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(
    @Inject(APP_CONFIG) private config: AppConfig,
    private http: Http,
    private loggerService: LoggerService
  ) {
    this.apiUrl = config.apiUrl;
    const idToken = localStorage.getItem('id_token');
    this.headers = new Headers({
      'Content-Type' : 'application/json',
      'X-API-Token' : idToken
    });
    if (idToken != null) {
      // sessionId
      this.isLoggedIn = true;
      this.loginUserName = localStorage.getItem('loginUserName');
    }

  }

  checkToken(data): void {

  }

  getCaptchaSrc(): Promise<string> {
    const url = `${this.apiUrl}/api/captcha/160x30x6x0`;
    return this.http.get(url, {headers: this.headers})
      .toPromise()
      .then(response => response.json())
      .catch(this.loggerService.handleError);
  }

  getRSAPublicKey(): Promise<string> {
    const url = `${this.apiUrl}/api/secret`;
    return this.http.get(url, {headers: this.headers})
      .toPromise()
      .then(response => response.json())
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
    this.loginUserName = "";
    localStorage.removeItem('id_token');
    localStorage.removeItem('session_id');
    localStorage.removeItem('loginUserName');
  }

  login(data): Promise<any> {
    const url = `${this.apiUrl}/api/login`;
    return this.http.post(url, data)
      .toPromise()
      .then(response => response.json())
      .catch(this.loggerService.handleError);
  }

  logout(): Observable<boolean> {
    return Observable.of(true).do(val => this.setLogoutInfo());
  }

}
