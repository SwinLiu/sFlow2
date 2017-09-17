import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { Http } from '@angular/http';

import { MainComponent } from './main.component';
import { TopNavComponent } from './top-nav/top-nav.component';
import { LeftNavComponent } from './left-nav/left-nav.component';

import { DashboardComponent } from '../dashboard/dashboard.component';
import { UserProfileComponent } from '../user-profile/user-profile.component';

import { MainRoutingModule } from './main-routing.module';
import { CompanyManagementComponent } from "app/components/company-management/company-management.component";
import { EmployeeManagementComponent } from "app/components/employee-management/employee-management.component";
import { UserManagementModule } from "app/components/user-management/user-management.module";

export function createTranslateHttpLoader(http: Http) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  imports: [
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateHttpLoader),
        deps: [Http]
      }
    }),
    CommonModule,
    FormsModule,
    UserManagementModule,
    MainRoutingModule
  ],
  declarations: [
    MainComponent,
    TopNavComponent,
    LeftNavComponent,
    DashboardComponent,
    UserProfileComponent,
    CompanyManagementComponent,
    EmployeeManagementComponent
  ],
  providers: [ ]
})
export class MainModule {}
