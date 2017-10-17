import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from "@shared/shared.module";
import { UserManagementComponent } from "app/routes/user-management/user-management.component";
import { UserListComponent } from "app/routes/user-management/user-list/user-list.component";
import { UserAddComponent } from "app/routes/user-management/user-add/user-add.component";
import { UserProfileComponent } from "app/routes/user-management/user-profile/user-profile.component";

const routes: Routes = [
  {
      path: 'management',
      component: UserManagementComponent,
      children: [
        { path: '', redirectTo: 'list', pathMatch: 'full' },
        { path: 'list', component: UserListComponent },
        { path: 'add', component: UserAddComponent }
      ]
  },
  { path: 'profile', component: UserProfileComponent }
];

@NgModule({
  imports: [
      SharedModule,
      RouterModule.forChild(routes)
  ],
  providers: [ ],
  declarations: [
      UserManagementComponent,
      UserListComponent,
      UserAddComponent,
      UserProfileComponent
  ],
  exports: [
      RouterModule
  ]
})
export class UserManagementModule {}
