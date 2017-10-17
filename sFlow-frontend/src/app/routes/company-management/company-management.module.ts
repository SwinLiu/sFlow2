import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { CompanyManagementComponent } from "app/routes/company-management/company-management.component";
import { CompanyListComponent } from "app/routes/company-management/company-list/company-list.component";
import { CompanyAddComponent } from "app/routes/company-management/company-add/company-add.component";
import { SharedModule } from "@shared/shared.module";

const routes: Routes = [
  {
      path: 'management',
      component: CompanyManagementComponent,
      children: [
        { path: '', redirectTo: 'list', pathMatch: 'full' },
        { path: 'list', component: CompanyListComponent },
        { path: 'add', component: CompanyAddComponent }
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
      CompanyManagementComponent,
      CompanyListComponent,
      CompanyAddComponent
  ],
  exports: [
      RouterModule
  ]
})
export class CompanyManagementModule {}
