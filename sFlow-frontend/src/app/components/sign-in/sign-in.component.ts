import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { AuthService } from '../../services/auth.service';
import { LoginParam } from "app/beans/loginParam";
import * as crypto from 'crypto-browserify';
import * as URLSafeBase64 from 'urlsafe-base64';


declare const Buffer;

@Component({
  selector: 'sFlow-sign-in',
  templateUrl: './sign-in.component.html'
})
export class SignInComponent implements OnInit {

  unEncryptPwd = "";
  captchaSrc: string;
  rsaPublicKey: string;
  loginParam: LoginParam = new LoginParam();


  constructor(public authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.authService.getCaptchaSrc().then(data => this.setCaptchaImg(data));
    this.authService.getRSAPublicKey().then(data => this.setRsaPublicKey(data));
  }

  setCaptchaImg(data): void {
    this.loginParam.captchaCodeId = data.result.captchaId;
    this.captchaSrc = 'data:image/png;base64,' + data.result.captchaSrc;
  }

  setRsaPublicKey(data): void {
    this.loginParam.rsaKeyId = data.result.rsaPublicKeyId;
    const keyBeginStr = "-----BEGIN PUBLIC KEY-----\n";
    const keyEndStr = "\n-----END PUBLIC KEY-----";
    this.rsaPublicKey = keyBeginStr + data.result.rsaPublicKey + keyEndStr;
  }


  login(): void {

    console.log("rsaPublicKey : \n" + this.rsaPublicKey);
    if (this.rsaPublicKey == null) {
      return;
    }

    const sha1Str: string = crypto.createHash('sha1').update(this.unEncryptPwd).digest('hex');
    console.log("sha1 = " + sha1Str);

    const passwd = this.loginParam.userName + '_@_' + sha1Str + '_@_' + Math.random();
    const encrypted = crypto.publicEncrypt(this.rsaPublicKey, new Buffer(passwd)).toString('base64');

    this.loginParam.password = URLSafeBase64.encode(new Buffer(encrypted, 'base64'))
    // this.loginParam.password = encrypted;
    console.log("RSA encrypted = " + encrypted);
    console.log(this.loginParam);

    this.authService.login(this.loginParam).then(data => {

      console.log(data);

      if (this.authService.isLoggedIn) {
        // Get the redirect URL from our auth service
        // If no redirect has been set, use the default
        const redirect = this.authService.redirectUrl ? this.authService.redirectUrl : '/dashboard';

        // Set our navigation extras object
        // that passes on our global query params and fragment
        const navigationExtras: NavigationExtras = {
          preserveQueryParams: true,
          preserveFragment: true
        };

        // Redirect the user
        this.router.navigate([redirect], navigationExtras);
      }
    });
  }

}
