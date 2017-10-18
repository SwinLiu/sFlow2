import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import { UserAccountService } from "app/services/user/user-account.service";
import { LoggerService } from "app/services/logger.service";

@Component({
  selector: 'sFlow-user-container',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {

  public userAccounts;

  constructor(
    private userAccountService: UserAccountService,
    private loggerService: LoggerService,
    private location: Location) { }

  ngOnInit() {
    this.renderListPage();
  }

  renderListPage() {
    this.userAccountService.getUserList().then(data => this.userAccounts = data.result);
  }

  deleteUser(uid: string) {
    this.userAccountService.deleteUser(uid).then(data => {
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
