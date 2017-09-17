import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, Http }    from '@angular/http';
import { TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppComponent }         from './app.component';
import { AppConfigModule } from './app-config.module';

import { SignInComponent }   from './components/sign-in/sign-in.component';
import { SignUpComponent }   from './components/sign-up/sign-up.component';
import { PageNotFoundComponent }   from './components/not-found/not-found.component';

import { MainModule }   from './components/main/main.module';

import { AppTranslateService } from './services/app-translate.service';
import { AuthService } from './services/auth.service';
import { ConsoleService } from './services/console.service';
import { LoggerService } from './services/logger.service';
import { AuthGuard } from './services/auth-guard.service';

import { AppRoutingModule } from './app-routing.module';
import { AppTranslateModule } from './app-translate.module';
import { UserManagementModule } from "app/components/user-management/user-management.module";

@NgModule({
  imports: [
    BrowserModule,
    AppTranslateModule,
    FormsModule,
    HttpModule,
    MainModule,
    UserManagementModule,
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
    ConsoleService,
    LoggerService,
    AuthGuard
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
