import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from "app/app-config.module";
import { LoggerService } from "app/services/logger.service";
import { HttpClient } from "@angular/common/http";
import { HttpResponse } from "@angular/common/http";
import { CONSTANTS } from "app/app.const";
import { Observable } from "rxjs/Rx";
import { RestResult } from "app/beans/restResult";

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
        const returnFlag = false;
        const url = `${this.apiUrl}/api/user/add`;
        return this.http.post(url, body)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

}
