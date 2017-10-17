import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from "@shared/shared.module";
import { EmployeeManagementComponent } from "app/routes/employee-management/employee-management.component";
import { EmployeeListComponent } from "app/routes/employee-management/employee-list/employee-list.component";
import { EmployeeAddComponent } from "app/routes/employee-management/employee-add/employee-add.component";

const routes: Routes = [
  {
      path: 'management',
      component: EmployeeManagementComponent,
      children: [
        { path: '', redirectTo: 'list', pathMatch: 'full' },
        { path: 'list', component: EmployeeListComponent },
        { path: 'add/:compId', component: EmployeeAddComponent }
      ]
  }
];

@NgModule({
  imports: [
      SharedModule,
      RouterModule.forChild(routes)
  ],
  providers: [ ],
  declarations: [
      EmployeeManagementComponent,
      EmployeeListComponent,
      EmployeeAddComponent
  ],
  exports: [
      RouterModule
  ]
})
export class EmployeeManagementModule {}
