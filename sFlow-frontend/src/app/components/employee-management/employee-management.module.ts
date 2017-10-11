import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EmployeeRoutingModule } from "app/components/employee-management/employee-routing.module";
import { EmployeeManagementComponent } from "app/components/employee-management/employee-management.component";
import { EmployeeListComponent } from "app/components/employee-management/employee-list/employee-list.component";
import { EmployeeAddComponent } from "app/components/employee-management/employee-add/employee-add.component";
import { BsDatepickerModule } from "ngx-bootstrap";


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    BsDatepickerModule.forRoot(),
    EmployeeRoutingModule
  ],
  declarations: [
    EmployeeManagementComponent,
    EmployeeListComponent,
    EmployeeAddComponent
  ],
  providers: [ ]
})
export class EmployeeManagementModule {}
