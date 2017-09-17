import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { AuthService } from '../../../services/auth.service';
import { LoggerService } from '../../../services/logger.service';
import { AppTranslateService } from "app/services/app-translate.service";

@Component({
  selector: 'sFlow-top-nav',
  templateUrl: './top-nav.component.html'
})
export class TopNavComponent implements OnInit {

  title = 'sFlow2';
  loginUserName = "";
  systemLangHtml = "";


  constructor(public authService: AuthService,
    private router: Router,
    private loggerService: LoggerService,
    public appTranslateService: AppTranslateService
    ) {
  }

  ngOnInit(): void {
      this.loginUserName = this.authService.loginUserName;
      this.systemLangHtml = this.appTranslateService.getLang();
  }

  setLang(lang?: string): void {
    this.appTranslateService.setLang(lang);
    this.systemLangHtml = this.appTranslateService.getLang();
  }

  logout(): void {
    this.loggerService.log("Start login");
    this.authService.logout().subscribe(() => {
      if (!this.authService.isLoggedIn) {

        // Get the redirect URL from our auth service
        // If no redirect has been set, use the default
        const redirect = 'signin';

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
