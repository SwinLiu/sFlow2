import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from 'app/app-config.module';
import { LoggerService } from 'app/services/logger.service';
import { CONSTANTS } from 'app/app.const';
import { RestResult } from 'app/beans/restResult';
import { _HttpClient } from "@core/services/http.client";

@Injectable()
export class UserAccountService {

    private apiUrl: string;

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: _HttpClient,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    addNewUser(body): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.user.add}`;
        return new Promise((resolve, reject) => {
            this.http.post(url, body).subscribe(response => resolve(response));
        });
        // return this.http.post(url, body)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);

    }

    deleteUser(uid): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.user.delete}/${uid}`;
        return new Promise((resolve, reject) => {
            this.http.delete(url).subscribe(response => resolve(response));
        });
        // return this.http.delete(url)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);
    }

    getUserList(): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.user.list}`;
        return new Promise((resolve, reject) => {
            this.http.get(url).subscribe(response => resolve(response));
        });
        // return this.http.get(url)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);

    }

}
