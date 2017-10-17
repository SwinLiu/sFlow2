import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from 'app/app-config.module';
import { LoggerService } from 'app/services/logger.service';
import { CONSTANTS } from 'app/app.const';
import { RestResult } from 'app/beans/restResult';
import { _HttpClient } from "@core/services/http.client";

@Injectable()
export class EmployeeService {

    private apiUrl: string;

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: _HttpClient,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    addEmployee(data): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.employee.add}`;
        return new Promise((resolve, reject) => {
            this.http.post(url, data).subscribe(response => resolve(response));
        });
        // return this.http.post(url, data)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);

    }

    deleteEmployee(empId): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.employee.delete}/${empId}`;
        return new Promise((resolve, reject) => {
            this.http.delete(url).subscribe(response => resolve(response));
        });
        // return this.http.delete(url)
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);
    }

    getEmployeeList(compId: string): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.employee.list}`;
        return new Promise((resolve, reject) => {
            this.http.get(url, {compId : compId}).subscribe(response => resolve(response));
        });
        // const params = new HttpParams()
        // .set('compId', compId);
        // return this.http.get(url, {params})
        //     .toPromise()
        //     .then(response => response)
        //     .catch(this.loggerService.handleError);

    }

}
