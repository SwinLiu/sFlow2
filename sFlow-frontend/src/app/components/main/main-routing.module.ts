import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent } from './main.component';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { UserProfileComponent } from '../user-profile/user-profile.component';

import { AuthGuard } from '../../services/auth-guard.service';
import { UserManagermentComponent } from "app/components/user-managerment/user-managerment.component";
import { EmployeeManagermentComponent } from "app/components/employee-managerment/employee-managerment.component";
import { CompanyManagermentComponent } from "app/components/company-managerment/company-managerment.component";

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
          { path: 'user/managerment', component: UserManagermentComponent },
          { path: 'company/managerment', component: CompanyManagermentComponent },
          { path: 'employee/managerment', component: EmployeeManagermentComponent }
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
    RouterModule
  ]
})
export class MainRoutingModule { }
