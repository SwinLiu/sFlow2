import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'sFlow-sign-in',
  templateUrl: './sign-in.component.html'
})
export class SignInComponent implements OnInit {

  loginAccount: string;
  password: string;
  captchaCode: string;
  captchaSrc: string;
  rsaPublicKey: string;


  constructor(public authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.captchaSrc = this.authService.getCaptchaSrc();
    this.authService.getRSAPublicKey().then(data => this.rsaPublicKey =  data);
  }

  login(): void {

    this.authService.login().subscribe(() => {
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
