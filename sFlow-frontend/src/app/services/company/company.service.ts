import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from "app/app-config.module";
import { LoggerService } from "app/services/logger.service";
import { HttpClient } from "@angular/common/http";
import { CONSTANTS } from "app/app.const";
import { RestResult } from "app/beans/restResult";

@Injectable()
export class CompanyService {

    private apiUrl: string;

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: HttpClient,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    addCompany(data): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.add}`;
        return this.http.post(url, data)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

    deleteCompany(compId): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.delete}/${compId}`;
        return this.http.delete(url)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);
    }

    getCompanyList(): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.list}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

    getCompanyDropdownList(): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.select}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

}
