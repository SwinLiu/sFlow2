import { Component, OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";

import { LoggerService }   from '../../services/logger.service';

@Component({
  selector: 'sFlow-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  systemLang : string;

  constructor(public translateService: TranslateService,
    private loggerService : LoggerService) {
    this.loggerService.debug("sFlow MainComponent Start ...");
  }

  ngOnInit(): void {
    // --- set i18n begin ---
    this.translateService.addLangs(["zh", "en"]);
    this.translateService.setDefaultLang("zh");
    const browserLang = this.translateService.getBrowserLang();
    this.loggerService.debug("Browser Lang : " + browserLang);
    this.systemLang = browserLang.match(/zh|en/) ? browserLang : 'zh';
    this.translateService.use(this.systemLang);
    this.loggerService.debug("Setup Lang : " + this.systemLang);
    // --- set i18n end ---
  }

}
