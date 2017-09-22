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

@Injectable()
export class AuthService {

  private apiUrl: string;
  private headers;

  loginUserName: string = "";
  isLoggedIn: boolean = false;
  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(
    @Inject(APP_CONFIG) private config: AppConfig,
    private http: Http,
    private loggerService: LoggerService
  ) {
    this.apiUrl = config.apiUrl;
    const idToken = localStorage.getItem('id_token');
    const sessionId = localStorage.getItem('id_session');
    this.headers = new Headers({
      'Content-Type' : 'application/json',
      'access-token' : idToken,
      'seesion-id' : sessionId
    });
    if (idToken != null) {
      // sessionId
      const url = `${this.apiUrl}/api/session/id`;
      this.http.get(url, {headers: this.headers})
        .toPromise()
        .then(response => this.checkToken(response.json()))
        .catch(this.loggerService.handleError);

    }

  }

  checkToken(data): void {
    if (data.isSuccess) {
      this.isLoggedIn = true;
      localStorage.setItem('id_session', data.result);
      const idToken = localStorage.getItem('id_token');
      const sessionId = localStorage.getItem('id_session');
      this.headers = new Headers({
        'Content-Type' : 'application/json',
        'access-token' : idToken,
        'seesion-id' : sessionId
      });
      this.loginUserName = localStorage.getItem('loginUserName');
    }
  }

  getCaptchaSrc(): string {
    return `${this.apiUrl}/api/captcha/160x30x6x0`;
  }

  getRSAPublicKey(): Promise<string> {
    const url = `${this.apiUrl}/api/secret`;
    return this.http.get(url, {headers: this.headers})
      .toPromise()
      .then(response => response.json().result)
      .catch(this.loggerService.handleError);
  }

  setLoginInfo(): void{
    this.isLoggedIn = true;
    this.loginUserName = "Swin Liu";
    localStorage.setItem('id_token', '123456789');
    localStorage.setItem('loginUserName', this.loginUserName);
  }

  setLogoutInfo(): void{
    this.isLoggedIn = false;
    this.loginUserName = "";
    localStorage.removeItem('id_token');
    localStorage.removeItem('session_id');
    localStorage.removeItem('loginUserName');
  }

  login(): Observable<boolean> {
    return Observable.of(true).do(val => this.setLoginInfo());
  }

  logout(): Observable<boolean> {
    return Observable.of(true).do(val => this.setLogoutInfo());
  }

}
