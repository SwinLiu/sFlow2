import { Router, NavigationExtras } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SettingsService } from '@core/services/settings.service';
import { LoginParam } from 'app/beans/loginParam';
import { AuthService } from 'app/services/auth.service';
import * as crypto from 'crypto-browserify';
import { UserSession } from 'app/beans/userSession';
import { StartupService } from "@core/services/startup.service";

@Component({
  selector: 'app-pages-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  valForm: FormGroup;

  unEncryptPwd = "";
  captchaSrc: string;
  loginMsg = "";
  loginParam: LoginParam = new LoginParam();
  submitted = false;
  
  constructor(private startupService: StartupService,
    public authService: AuthService,
    public settings: SettingsService, fb: FormBuilder, private router: Router) {
    this.valForm = fb.group({
      userAccount: [null, Validators.compose([Validators.required])],
      password: [null, Validators.required],
      captcha: [null, Validators.required],
      remember_me: [null]
    });
  }

  ngOnInit(): void {
    this.refreshCaptcha();
  }

  setCaptchaImg(data): void {
    this.loginParam.captchaCodeId = data.result.captchaId;
    this.captchaSrc = 'data:image/png;base64,' + data.result.captchaSrc;
  }

  refreshCaptcha(): void {
    this.authService.getCaptchaSrc().then(data => this.setCaptchaImg(data));
  }



  submit() {
    // tslint:disable-next-line:forin
    for (const i in this.valForm.controls) {
      this.valForm.controls[i].markAsDirty();
    }
    if (this.valForm.valid) {
      // this.router.navigate(['dashboard']);
      
      const sha1Str: string = crypto.createHash('sha1').update(this.unEncryptPwd).digest('hex');
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
  
            this.startupService.load();

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
}
