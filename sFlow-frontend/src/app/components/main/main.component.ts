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
  }

}
