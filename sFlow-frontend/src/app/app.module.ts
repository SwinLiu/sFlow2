import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http } from '@angular/http';
import { TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppComponent } from './app.component';
import { AppConfigModule } from './app-config.module';

import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { PageNotFoundComponent } from './components/not-found/not-found.component';

import { MainModule } from './components/main/main.module';

import { AppTranslateService } from './services/app-translate.service';
import { AuthService } from './services/auth.service';
import { ConsoleService } from './services/console.service';
import { LoggerService } from './services/logger.service';
import { AuthGuard } from './services/auth-guard.service';

import { AppRoutingModule } from './app-routing.module';
import { AppTranslateModule } from './app-translate.module';
import { UserAccountService } from "app/services/user/user-account.service";
import { JwtInterceptor } from "app/jwt.interceptor";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { CompanyService } from "app/services/company/company.service";
import { EmployeeService } from "app/services/employee/employee.service";

@NgModule({
  imports: [
    BrowserModule,
    AppTranslateModule,
    FormsModule,
    HttpClientModule,
    MainModule,
    AppConfigModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    PageNotFoundComponent
  ],
  providers: [
    AppTranslateService,
    AuthService,
    UserAccountService,
    CompanyService,
    EmployeeService,
    ConsoleService,
    LoggerService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    }
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
