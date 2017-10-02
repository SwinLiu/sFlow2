import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CompanyRoutingModule } from "app/components/company-management/company-routing.module";
import { CompanyManagementComponent } from "app/components/company-management/company-management.component";
import { CompanyListComponent } from "app/components/company-management/company-list/company-list.component";
import { CompanyAddComponent } from "app/components/company-management/company-add/company-add.component";



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CompanyRoutingModule
  ],
  declarations: [
    CompanyManagementComponent,
    CompanyListComponent,
    CompanyAddComponent
  ],
  providers: [ ]
})
export class CompanyManagementModule {}
