import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from "app/services/auth-guard.service";
import { TranslateModule } from "@ngx-translate/core";
import { EmployeeManagementComponent } from "app/components/employee-management/employee-management.component";
import { EmployeeListComponent } from "app/components/employee-management/employee-list/employee-list.component";
import { EmployeeAddComponent } from "app/components/employee-management/employee-add/employee-add.component";

const employeeRoutes: Routes = [
  {
    path: 'management',
    canActivate: [AuthGuard],
    component: EmployeeManagementComponent,
    children: [
      {
        path: '',
        canActivateChild: [AuthGuard],
        children: [
          { path: '', redirectTo: 'list', pathMatch: 'full' },
          { path: 'list', component: EmployeeListComponent },
          { path: 'add', component: EmployeeAddComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(employeeRoutes)
  ],
  exports: [
    RouterModule,
    TranslateModule
  ],
  providers: [
  ]
})
export class EmployeeRoutingModule { }
