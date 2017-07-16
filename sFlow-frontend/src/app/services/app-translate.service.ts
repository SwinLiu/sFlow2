import { Injectable, Inject } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { TranslateService } from "@ngx-translate/core";

import { LoggerService } from './logger.service';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AppTranslateService {

  constructor(private translateService: TranslateService,
    private loggerService : LoggerService
  ) {
    this.translateService.addLangs(["zh", "en"]);
    this.translateService.setDefaultLang("zh");
    const browserLang = this.translateService.getBrowserLang();
    this.loggerService.debug("Browser Lang : " + browserLang);
    const systemLang = browserLang.match(/zh|en/) ? browserLang : 'zh';
    this.translateService.use(systemLang);
    this.loggerService.debug("Setup Lang : " + systemLang);
  }

  setLang(lang?:string): void{
    lang = lang || this.translateService.getBrowserLang();
    const systemLang = lang.match(/zh|en/) ? lang : 'zh';
    this.translateService.use(systemLang);
    this.loggerService.debug("Setup Lang : " + systemLang);
  }

}
