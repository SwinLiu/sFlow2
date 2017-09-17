import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent } from './main.component';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { UserProfileComponent } from '../user-profile/user-profile.component';

import { AuthGuard } from '../../services/auth-guard.service';
import { EmployeeManagementComponent } from "app/components/employee-management/employee-management.component";
import { CompanyManagementComponent } from "app/components/company-management/company-management.component";
import { TranslateModule } from "@ngx-translate/core";

const mainRoutes: Routes = [
  {
    path: '',
    canActivate: [AuthGuard],
    component: MainComponent,
    children: [
      {
        path: '',
        canActivateChild: [AuthGuard],
        children: [
          { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
          { path: 'dashboard', component: DashboardComponent },
          { path: 'user/profile', component: UserProfileComponent },
          { path: 'user', loadChildren: 'app/components/user-management/user-management.module#UserManagementModule' },
          { path: 'company/management', component: CompanyManagementComponent },
          { path: 'employee/management', component: EmployeeManagementComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(mainRoutes)
  ],
  exports: [
    RouterModule,
    TranslateModule
  ],
  providers: [
  ]
})
export class MainRoutingModule { }
