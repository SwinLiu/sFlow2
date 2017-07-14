import { Injectable } from '@angular/core';

@Injectable()
export class ConsoleService {
    log(message:any) : void {
        console.log(message);
    }
    warn(message:any) : void {
        console.warn(message);
    }
    error(message:any) : void {
        console.error(message);
    }
    info(message:any) : void {
        console.info(message);
    }
    debug(message:any) : void {
        console.debug(message);
    }
}