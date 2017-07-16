import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { AuthService }      from '../../../services/auth.service';
import { LoggerService }   from '../../../services/logger.service';

@Component({
  selector: 'sFlow-top-nav',
  templateUrl: './top-nav.component.html'
})
export class TopNavComponent implements OnInit {

  title = 'sFlow2';
  loginUserName = "";

  constructor(public authService: AuthService,
    private router: Router,
    private loggerService : LoggerService
    ) {
  }

  ngOnInit(): void {
      this.loginUserName = this.authService.loginUserName;
  }

  logout(): void {
    this.loggerService.log("Start login");
    this.authService.logout().subscribe(() => {
      if (!this.authService.isLoggedIn) {

        // Get the redirect URL from our auth service
        // If no redirect has been set, use the default
        let redirect = 'login';

        // Set our navigation extras object
        // that passes on our global query params and fragment
        let navigationExtras: NavigationExtras = {
          preserveQueryParams: true,
          preserveFragment: true
        };

        // Redirect the user
        this.router.navigate([redirect], navigationExtras);

      }
    });
  }
}
