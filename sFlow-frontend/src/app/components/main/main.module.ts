import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { MainComponent }   from './main.component';
import { TopNavComponent }   from './top-nav/top-nav.component';
import { LeftNavComponent }   from './left-nav/left-nav.component';

import { DashboardComponent }   from '../dashboard/dashboard.component';
import { UserProfileComponent }   from '../user-profile/user-profile.component';

import { MainRoutingModule } from './main-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MainRoutingModule
  ],
  declarations: [
    MainComponent,
    TopNavComponent,
    LeftNavComponent,
    DashboardComponent,
    UserProfileComponent
  ],
  providers: [ ]
})
export class MainModule {}
