import { Injectable, Inject } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';

import { APP_CONFIG, AppConfig } from '../app-config.module';

import { LoggerService } from './logger.service';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class TestService {

  private apiUrl: string;

  private headers = new Headers({'Content-Type': 'application/json'});

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
      .then(res => console.log(res))
      .catch(this.loggerService.handleError);
  }

}