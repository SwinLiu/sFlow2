import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import { LoggerService } from "app/services/logger.service";
import { CompanyService } from "app/services/company/company.service";

@Component({
  selector: 'sFlow-company-container',
  templateUrl: './company-list.component.html'
})
export class CompanyListComponent implements OnInit {

  private companyList;

  constructor(private companyService: CompanyService,
    private loggerService: LoggerService,
    private location: Location) { }

  ngOnInit() {

    this.companyService.getCompanyList().then(data => this.renderListPage(data));

  }

  renderListPage(res) {
    this.companyList = res.result;
  }

  goToBack() {
    this.location.back();
  }



}

