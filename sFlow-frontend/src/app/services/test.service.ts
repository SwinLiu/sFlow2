import { Injectable, Inject } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';

import { APP_CONFIG, AppConfig } from '../app-config.module';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class TestService {

  private apiUrl: string;

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(@Inject(APP_CONFIG) private config: AppConfig, private http: Http) {
    this.apiUrl = config.apiUrl;
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  getTest(): void {
    const url = `${this.apiUrl}/api/hello1`;// `${this.heroesUrl}/13`;//`${this.testUrl}`;
    console.log(url);
    this.http.get(url)
      .toPromise()
      .then(res => console.log(res))
      .catch(this.handleError);
  }

}