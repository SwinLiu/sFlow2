import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import { AuthService } from "app/services/auth.service";
import { LoggerService } from "app/services/logger.service";
import { Router } from "@angular/router";
import { CONSTANTS } from "app/app.const";
import { CompanyService } from "app/services/company/company.service";

declare const Buffer;

@Component({
  selector: 'sFlow-company-add',
  templateUrl: './company-add.component.html'
})
export class CompanyAddComponent implements OnInit {

  newCompany = {
    companyName : "",
    address : ""
  }

  errorMsg = "";

  constructor(
    private companyService: CompanyService,
    private loggerService: LoggerService,
    private location: Location,
    private router: Router) { }

  ngOnInit() {


  }

  addNew() {
    this.errorMsg = "";

    this.companyService.addCompany(this.newCompany).then(
      data => {
        if (data.success) {
          this.router.navigate([CONSTANTS.ROUTE_URL.company.list]);
        } else {
          this.errorMsg = data.message;
        }
      }
    );

  }

  goToBack() {
    this.location.back();
  }

}

