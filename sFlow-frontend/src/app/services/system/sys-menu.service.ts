import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from 'app/app-config.module';
import { LoggerService } from 'app/services/logger.service';
import { CONSTANTS } from 'app/app.const';
import { RestResult } from 'app/beans/restResult';
import { _HttpClient } from "@core/services/http.client";
import { Page } from "app/beans/page";

@Injectable()
export class SysMenuService {

    private apiUrl: string;

    constructor(
        @Inject(APP_CONFIG) private config: AppConfig,
        private http: _HttpClient,
        private loggerService: LoggerService
    ) {
        this.apiUrl = config.apiUrl;
    }

    getMenuList(): Promise<RestResult> {
        const url = `${this.apiUrl}${CONSTANTS.API_URL.sys.menu.list}`;
        return new Promise((resolve, reject) => {
            this.http.get(url).subscribe(response => resolve(response));
        });
    }


}
