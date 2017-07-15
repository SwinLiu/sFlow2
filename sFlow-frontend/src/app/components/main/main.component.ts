import { Component, OnInit } from '@angular/core';
import { LoggerService }   from '../../services/logger.service';

@Component({
  selector: 'sFlow-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  systemLang : string;

  constructor(private loggerService : LoggerService) {
    this.loggerService.debug("sFlow MainComponent Start ...");
  }

  ngOnInit(): void {

  }

}
