import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from 'app/app-config.module';
import { LoggerService } from 'app/services/logger.service';
import { CONSTANTS } from 'app/app.const';
import { RestResult } from 'app/beans/restResult';
import { _HttpClient } from "@core/services/http.client";

@Injectable()
export class CompanyService {

    private apiUrl: string;

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: _HttpClient,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    addCompany(data): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.add}`;
        return new Promise((resolve, reject) => {
            this.http.post(url, data).subscribe(response => resolve(response));
        });
        // return this.http.post(url, data)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);

    }

    deleteCompany(compId): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.delete}/${compId}`;
        return new Promise((resolve, reject) => {
            this.http.delete(url, {compId: compId}).subscribe(response => resolve(response));
        });
        // return this.http.delete(url)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);
    }

    getCompanyList(): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.list}`;
        return new Promise((resolve, reject) => {
            this.http.get(url).subscribe(response => resolve(response));
        });
        // return this.http.get(url)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);

    }

    getCompanyDropdownList(): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.company.select}`;
        return new Promise((resolve, reject) => {
            this.http.get(url).subscribe(response => resolve(response));
        });
        // return this.http.get(url)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);

    }

}
