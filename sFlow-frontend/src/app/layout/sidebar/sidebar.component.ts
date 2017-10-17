import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { SettingsService } from '@core/services/settings.service';
import { AuthService } from "app/services/auth.service";
import { CONSTANTS } from "app/app.const";

@Component({
  selector   : 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent {
    constructor(public authService: AuthService,
      public settings: SettingsService, 
      public msgSrv: NzMessageService) {
    }

    logout() {
      this.authService.logout();
    }
    
}
