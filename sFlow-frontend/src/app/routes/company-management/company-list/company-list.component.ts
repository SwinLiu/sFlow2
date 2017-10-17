import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import { LoggerService } from "app/services/logger.service";
import { CompanyService } from "app/services/company/company.service";

@Component({
  selector: 'sFlow-company-container',
  templateUrl: './company-list.component.html'
})
export class CompanyListComponent implements OnInit {

  public companyList;

  constructor(
    private companyService: CompanyService,
    private loggerService: LoggerService,
    private location: Location) { }

  ngOnInit() {
    this.renderListPage();
  }

  renderListPage() {
    this.companyService.getCompanyList().then(data => this.companyList = data.result);
  }

  deleteCompany(compId: string) {
    this.companyService.deleteCompany(compId).then(data => {
      if (data.success) {
        this.renderListPage();
      } else {
        console.error(data.message);
      }
    });
  }

  goToBack() {
    this.location.back();
  }



}

