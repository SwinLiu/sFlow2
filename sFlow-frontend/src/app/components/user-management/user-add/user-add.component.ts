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
  selector: 'sFlow-user-add',
  templateUrl: './user-add.component.html'
})
export class UserAddComponent implements OnInit {

  rsaPublicKey: string;

  newUser = {
    userName : "",
    phoneNumber : "",
    email : "",
    password : ""
  }

  unEncryptPwd = "";

  errorMsg = "";

  constructor(private authService: AuthService,
    private userAccountService: UserAccountService,
    private loggerService: LoggerService,
    private location: Location,
    private router: Router) { }

  ngOnInit() {

    const keyBeginStr = "-----BEGIN PUBLIC KEY-----\n";
    const keyEndStr = "\n-----END PUBLIC KEY-----";
    this.authService.getRSAPublicKey().then(data => this.rsaPublicKey =  keyBeginStr + data + keyEndStr);

    // const sha1Str: string = crypto.createHash('sha1').update('test').digest('hex');
    // console.log("sha1 = %s", sha1Str);

    // const RSA_PUBLIC_KEY: string = '-----BEGIN PUBLIC KEY-----\n' +
    // 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOkvtlnW0AZsuC4PCiaagn4tvCKxgHDi\n' +
    // 'ttSM+3eOqmff7uCNb4A5MBpq6jJq2L7giQisp0ko1s1l4zchHAjN+fMCAwEAAQ==\n' +
    // '-----END PUBLIC KEY-----';

    // const RSA_PRIVATE_KEY = '-----BEGIN PRIVATE KEY-----\n' +
    // 'MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA6S+2WdbQBmy4Lg8K\n' +
    // 'JpqCfi28IrGAcOK21Iz7d46qZ9/u4I1vgDkwGmrqMmrYvuCJCKynSSjWzWXjNyEc\n' +
    // 'CM358wIDAQABAkEAiCddt9Mfn8C3Qd1yNdwduYMGyNMPhdo6mpy+764TfZY7m5Ix\n' +
    // 'aAORc8gcxAa4HCgppFZKPMhVHQeQ9PPOyfTNUQIhAP53gQ6dhpoOQJph9EQI3pbw\n' +
    // '+s8Zn2PRuK2qJJGfXHPvAiEA6pdifFp0p0/l2xzhRbWeJTpIU2UpBR++nd8N77od\n' +
    // 'Rj0CICWmxwjDlCP9Ud/F+J+MdGr/Ew1LrELXyCyiDTEi1EovAiAtd5XRAD8nxmaI\n' +
    // 'ZzqKJj82e+tUroCay6JIOtkao3nVCQIgXhPtwxga9IRKEmPYPPInnjslTW6+OoLA\n' +
    // 'LFHMeUwQnvE=\n' +
    // '-----END PRIVATE KEY-----';

    // const encrypted = crypto.publicEncrypt(RSA_PUBLIC_KEY, new Buffer("test")).toString('hex');
    // console.log("RSA encrypted = %s", encrypted);
    // const decrypted = crypto.privateDecrypt(RSA_PRIVATE_KEY, new Buffer(encrypted, 'hex'));
    // console.log("RSA decrypted = %s", decrypted.toString());

  }

  addNew() {
    this.errorMsg = "";
    this.loggerService.log("rsaPublicKey : \n" + this.rsaPublicKey);
    if (this.rsaPublicKey == null) {
      return;
    }

    const sha1Str: string = crypto.createHash('sha1').update(this.unEncryptPwd).digest('hex');
    this.loggerService.log("sha1 = " + sha1Str);

    // const passwd = this.newUser + '_@_' + sha1Str + '_@_test';
    // const encrypted = crypto.publicEncrypt(this.rsaPublicKey, new Buffer(passwd)).toString('hex');

    this.newUser.password = sha1Str;
    // this.loggerService.log("RSA encrypted = " + encrypted);
    this.loggerService.log(this.newUser);

    const result: any = null;
    this.userAccountService.addNewUser(this.newUser).then(
      data => {
        this.loggerService.log(data);
        if (data.success) {
          this.router.navigate([CONSTANTS.ROUTE_URL.user.list]);
        } else {
          this.errorMsg = data.message;
        }
      }
    );
    // this.loggerService.log(this.userAccountService.addNewUser(this.newUser));
  }

  goToBack() {
    this.location.back();
  }

}

