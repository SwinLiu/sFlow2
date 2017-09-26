import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { AuthService } from '../../services/auth.service';
import { LoginParam } from "app/beans/loginParam";
import * as crypto from 'crypto-browserify';
import { UserSession } from "app/beans/userSession";
// import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';

// declare const Buffer;

@Component({
  selector: 'sFlow-sign-in',
  templateUrl: './sign-in.component.html'
})
export class SignInComponent implements OnInit {

  // form: FormGroup;
  unEncryptPwd = "";
  captchaSrc: string;
  loginMsg = "";
  // rsaPublicKey: string;
  loginParam: LoginParam = new LoginParam();
  submitted = false;
  // public userName: AbstractControl;
  // public password: AbstractControl;
  // public captchaCode: AbstractControl;

  constructor(public authService: AuthService,
    // fb: FormBuilder,
    private router: Router) {

    // this.form = fb.group({
    //   'userName': ['', Validators.compose([Validators.required])],
    //   'password': ['', Validators.compose([Validators.required])],
    //   'captchaCode': ['', Validators.compose([Validators.required])]
    // });

    // this.userName = this.form.controls['userName'];
    // this.password = this.form.controls['password'];
    // this.captchaCode = this.form.controls['captchaCode'];
  }

  ngOnInit(): void {
    this.refreshCaptcha();
    // this.authService.getRSAPublicKey().then(data => this.setRsaPublicKey(data));
  }

  setCaptchaImg(data): void {
    this.loginParam.captchaCodeId = data.result.captchaId;
    this.captchaSrc = 'data:image/png;base64,' + data.result.captchaSrc;
  }

  refreshCaptcha(): void {
    this.authService.getCaptchaSrc().then(data => this.setCaptchaImg(data));
  }

  // setRsaPublicKey(data): void {
  //   this.loginParam.rsaKeyId = data.result.rsaPublicKeyId;
  //   const keyBeginStr = "-----BEGIN PUBLIC KEY-----\n";
  //   const keyEndStr = "\n-----END PUBLIC KEY-----";
  //   this.rsaPublicKey = keyBeginStr + data.result.rsaPublicKey + keyEndStr;
  // }



  onSubmit(values: Object): void {
    // this.submitted = true;
    // if (this.form.valid) {
    //   this.login();
    // }
  }

  login(): void {

    // console.log("rsaPublicKey : \n" + this.rsaPublicKey);
    // if (this.rsaPublicKey == null) {
    //   return;
    // }

    const sha1Str: string = crypto.createHash('sha1').update(this.unEncryptPwd).digest('hex');

    // const passwd = this.loginParam.userName + '_@_' + sha1Str + '_@_' + Math.random();
    // const encrypted = crypto.publicEncrypt(this.rsaPublicKey, new Buffer(passwd)).toString('base64');

    // this.loginParam.password = URLSafeBase64.encode(new Buffer(encrypted, 'base64'))
    this.loginParam.password = sha1Str;

    this.authService.login(this.loginParam).then(data => {

      if (data.success) {
        const userSession: UserSession = data.result;
        this.authService.setLoginInfo(userSession);
        this.loginMsg = "";
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

      } else {
        this.refreshCaptcha();
        this.loginMsg = data.message;

      }
    });
  }

}
