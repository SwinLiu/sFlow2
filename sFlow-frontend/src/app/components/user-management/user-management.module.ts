import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { UserManagementComponent } from "app/components/user-management/user-management.component";
import { UserListComponent } from "app/components/user-management/user-list/user-list.component";
import { UserAddComponent } from "app/components/user-management/user-add/user-add.component";
import { UserRoutingModule } from "app/components/user-management/user-routing.module";


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    UserRoutingModule
  ],
  declarations: [
    UserManagementComponent,
    UserListComponent,
    UserAddComponent
  ],
  providers: [ ]
})
export class UserManagementModule {}
