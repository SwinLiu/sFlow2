import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import { UserAccountService } from "app/services/user/user-account.service";
import { LoggerService } from "app/services/logger.service";

@Component({
  selector: 'sFlow-company-container',
  templateUrl: './company-list.component.html'
})
export class CompanyListComponent implements OnInit {

  private userAccounts;

  constructor(private userAccountService: UserAccountService,
    private loggerService: LoggerService,
    private location: Location) { }

  ngOnInit() {

    this.userAccountService.getUserList().then(data => this.renderListPage(data));

  }

  renderListPage(res) {
    this.userAccounts = res.result;
  }

  goToBack() {
    this.location.back();
  }



}

