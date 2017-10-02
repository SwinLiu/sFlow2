import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from "app/services/auth-guard.service";
import { TranslateModule } from "@ngx-translate/core";
import { CompanyManagementComponent } from "app/components/company-management/company-management.component";
import { CompanyAddComponent } from "app/components/company-management/company-add/company-add.component";
import { CompanyListComponent } from "app/components/company-management/company-list/company-list.component";

const companyRoutes: Routes = [
  {
    path: 'management',
    canActivate: [AuthGuard],
    component: CompanyManagementComponent,
    children: [
      {
        path: '',
        canActivateChild: [AuthGuard],
        children: [
          { path: '', redirectTo: 'list', pathMatch: 'full' },
          { path: 'list', component: CompanyListComponent },
          { path: 'add', component: CompanyAddComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(companyRoutes)
  ],
  exports: [
    RouterModule,
    TranslateModule
  ],
  providers: [
  ]
})
export class CompanyRoutingModule { }
