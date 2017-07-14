import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, Http }    from '@angular/http';
import { TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppComponent }         from './app.component';
import { AppConfigModule } from './app-config.module';

import { TopNavComponent }   from './components/top-nav/top-nav.component';
import { LeftNavComponent }   from './components/left-nav/left-nav.component';
import { LoginComponent }   from './components/login/login.component';
import { DashboardComponent }   from './components/dashboard/dashboard.component';
import { PageNotFoundComponent }   from './components/not-found/not-found.component';
import { UserProfileComponent }   from './components/user-profile/user-profile.component';


import { TestService }          from './services/test.service';

import { AppRoutingModule }     from './app-routing.module';

export function createTranslateHttpLoader(http: Http) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  imports: [
    BrowserModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateHttpLoader),
        deps: [Http]
      }
    }),
    FormsModule,
    HttpModule,
    AppConfigModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    TopNavComponent,
    LeftNavComponent,
    DashboardComponent,
    LoginComponent,
    PageNotFoundComponent,
    UserProfileComponent
  ],
  providers: [ TestService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
