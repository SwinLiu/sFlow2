import { Injectable, Inject } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

import { APP_CONFIG, AppConfig } from '../app-config.module';

import { LoggerService } from './logger.service';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthService {

  private apiUrl: string;
  private headers = new Headers({'Content-Type': 'application/json'});

  loginUserName: string = "";
  isLoggedIn: boolean = false;
  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(
    @Inject(APP_CONFIG) private config: AppConfig,
    private http: Http,
    private loggerService : LoggerService
  ) {
    this.apiUrl = config.apiUrl;
  }

  getTest(): void {
    const url = `${this.apiUrl}/api/hello1`;// `${this.heroesUrl}/13`;//`${this.testUrl}`;
    console.log(url);
    this.http.get(url)
      .toPromise()
      .then(res => this.loggerService.log(res))
      .catch(this.loggerService.handleError);
  }

  setLoginInfo(): void{
    this.isLoggedIn = true;
    this.loginUserName = "Swin Liu";
  }

  setLogoutInfo(): void{
    this.isLoggedIn = false;
    this.loginUserName = "";
  }

  login(): Observable<boolean> {
    return Observable.of(true).do(val => this.setLoginInfo());
  }

  logout(): Observable<boolean> {
    return Observable.of(true).do(val => this.setLogoutInfo());
  }

}
