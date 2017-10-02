import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import * as crypto from 'crypto-browserify';
import { AuthService } from "app/services/auth.service";
import { LoggerService } from "app/services/logger.service";
import { UserAccountService } from "app/services/user/user-account.service";
import { Router } from "@angular/router";
import { CONSTANTS } from "app/app.const";

declare const Buffer;

@Component({
  selector: 'sFlow-employee-add',
  templateUrl: './employee-add.component.html'
})
export class EmployeeAddComponent implements OnInit {

  // rsaPublicKey: string;

  newUser = {
    userName : "",
    phoneNumber : "",
    email : "",
    password : ""
  }

  unEncryptPwd = "";

  errorMsg = "";

  constructor(
    // private authService: AuthService,
    private userAccountService: UserAccountService,
    private loggerService: LoggerService,
    private location: Location,
    private router: Router) { }

  ngOnInit() {


  }

  addNew() {
    this.errorMsg = "";
    const sha1Str: string = crypto.createHash('sha1').update(this.unEncryptPwd).digest('hex');

    this.newUser.password = sha1Str;

    const result: any = null;
    this.userAccountService.addNewUser(this.newUser).then(
      data => {
        if (data.success) {
          this.router.navigate([CONSTANTS.ROUTE_URL.user.list]);
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

