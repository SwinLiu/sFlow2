import { Injectable } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";

import { LoggerService } from './logger.service';

import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AppTranslateService {

  constructor(private translateService: TranslateService,
    private loggerService: LoggerService
  ) {

    defineLocale('zh', zhCn);

    this.translateService.addLangs(["zh", "en"]);
    this.translateService.setDefaultLang("zh");
    const browserLang = this.translateService.getBrowserLang();
    this.loggerService.debug("Browser Lang : " + browserLang);
    let systemLang: string = localStorage.getItem('systemLang');
    systemLang = systemLang ? systemLang : browserLang;
    systemLang = systemLang.match(/zh|en/) ? systemLang : 'zh';
    this.translateService.use(systemLang);
    this.loggerService.debug("Setup Lang : " + systemLang);
  }

  setLang(lang?: string): void {
    lang = lang || localStorage.getItem('systemLang');
    lang = lang || this.translateService.getBrowserLang();
    const systemLang = lang.match(/zh|en/) ? lang : 'zh';
    this.translateService.use(systemLang);
    localStorage.setItem('systemLang', systemLang);
    this.loggerService.debug("Setup Lang : " + systemLang);
  }

  getLang(): string {
    return "lang-" + this.getSystemLang();
  }

  getSystemLang(): string {
    let lang = localStorage.getItem('systemLang');
    lang = lang || this.translateService.getBrowserLang();
    lang = lang.match(/zh|en/) ? lang : 'zh';
    return lang;
  }

}
