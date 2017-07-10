import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppComponent }         from './app.component';
import { AppConfigModule } from './app-config.module';

import { LoginComponent }   from './components/login/login.component';
import { DashboardComponent }   from './components/dashboard/dashboard.component';
import { MainErrorComponent }   from './components/main-error/main-error.component';


import { TestService }          from './services/test.service';

import { AppRoutingModule }     from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppConfigModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    MainErrorComponent
  ],
  providers: [ TestService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
