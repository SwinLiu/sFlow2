import { Component, OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";

import { LoggerService }   from './services/logger.service';
import { AppTranslateService }   from './services/app-translate.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  systemLang : string;

  constructor(public appTranslateService: AppTranslateService,
    private loggerService : LoggerService) {
    this.loggerService.debug("sFlow AppComponent Start ...");
  }

  ngOnInit(): void {
    this.loggerService.debug("Start Setup i18n ");
    // --- set i18n begin ---
    this.appTranslateService.setLang();
    // --- set i18n end ---
  }

}
