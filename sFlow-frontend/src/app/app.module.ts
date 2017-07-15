import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, Http }    from '@angular/http';
import { TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppComponent }         from './app.component';
import { AppConfigModule } from './app-config.module';

import { LoginComponent }   from './components/login/login.component';
import { PageNotFoundComponent }   from './components/not-found/not-found.component';

import { TestService }          from './services/test.service';
import { ConsoleService }          from './services/console.service';
import { LoggerService }          from './services/logger.service';

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
    LoginComponent,
    PageNotFoundComponent
  ],
  providers: [
    TestService,
    ConsoleService,
    LoggerService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
