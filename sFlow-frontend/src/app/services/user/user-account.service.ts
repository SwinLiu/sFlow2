import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from 'app/app-config.module';
import { LoggerService } from 'app/services/logger.service';
import { HttpClient } from '@angular/common/http';
import { CONSTANTS } from 'app/app.const';
import { RestResult } from 'app/beans/restResult';

@Injectable()
export class UserAccountService {

    private apiUrl: string;

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: HttpClient,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    addNewUser(body): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.user.add}`;
        return this.http.post(url, body)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

    deleteUser(uid): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.user.delete}/${uid}`;
        return this.http.delete(url)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);
    }

    getUserList(): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.user.list}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

}
