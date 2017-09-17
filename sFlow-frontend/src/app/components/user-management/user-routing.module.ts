import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from "app/services/auth-guard.service";
import { UserManagementComponent } from "app/components/user-management/user-management.component";
import { UserListComponent } from "app/components/user-management/user-list/user-list.component";
import { UserAddComponent } from "app/components/user-management/user-add/user-add.component";

const userRoutes: Routes = [
  {
    path: '',
    canActivate: [AuthGuard],
    component: UserManagementComponent,
    children: [
      {
        path: '',
        canActivateChild: [AuthGuard],
        children: [
          { path: '', redirectTo: 'list', pathMatch: 'full' },
          { path: 'list', component: UserListComponent },
          { path: 'add', component: UserAddComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(userRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule { }