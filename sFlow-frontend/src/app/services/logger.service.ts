import { Injectable, Inject } from '@angular/core';
import { APP_CONFIG, AppConfig } from '../app-config.module';
import { ConsoleService } from './console.service';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class LoggerService {
    enable: boolean;
    constructor(@Inject(APP_CONFIG) private config: AppConfig, private consoleService: ConsoleService) {
        this.enable = config.enableLog;
    }
    log(message: any) {
        if (this.enable) {
            this.consoleService.log(message);
        }
    }
    warn(message: any) {
        if (this.enable) {
            this.consoleService.warn(message);
        }
    }
    error(message: any) {
        if (this.enable) {
            this.consoleService.error(message);
        }
    }
    info(message: any) {
        if (this.enable) {
            this.consoleService.info(message);
        }
    }
    debug(message: any) {
        if (this.enable) {
            this.consoleService.debug(message);
        }
    }

    handleError(error: any): Promise<any> {
        this.error(error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

}
