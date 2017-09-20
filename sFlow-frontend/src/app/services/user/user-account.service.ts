import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from "app/app-config.module";
import { Headers, Http, Response } from '@angular/http';
import { LoggerService } from "app/services/logger.service";

@Injectable()
export class UserAccountService {

    private apiUrl: string;
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: Http,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    addNewUser(data): Promise<string> {
        const url = `${this.apiUrl}/api/user/add`;
        return this.http.post(url, data)
            .toPromise()
            .then(response => response.json())
            .catch(this.loggerService.handleError);

    }

}
