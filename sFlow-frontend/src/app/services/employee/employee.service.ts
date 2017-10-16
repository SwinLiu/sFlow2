import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from 'app/app-config.module';
import { LoggerService } from 'app/services/logger.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CONSTANTS } from 'app/app.const';
import { RestResult } from 'app/beans/restResult';

@Injectable()
export class EmployeeService {

    private apiUrl: string;

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: HttpClient,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    addEmployee(data): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.employee.add}`;
        return this.http.post(url, data)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

    deleteEmployee(empId): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.employee.delete}/${empId}`;
        return this.http.delete(url)
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);
    }

    getEmployeeList(compId: string): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.employee.list}`;
        const params = new HttpParams()
        .set('compId', compId);
        return this.http.get(url, {params})
            .toPromise()
            .then(response => response)
            .catch(this.loggerService.handleError);

    }

}
