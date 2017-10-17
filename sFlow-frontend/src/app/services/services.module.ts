import { NgModule, Optional, SkipSelf } from '@angular/core';
import { throwIfAlreadyLoaded } from "@core/module-import-guard";
import { CompanyService } from "app/services/company/company.service";
import { EmployeeService } from "app/services/employee/employee.service";
import { UserAccountService } from "app/services/user/user-account.service";
import { AuthService } from "app/services/auth.service";
import { LoggerService } from "app/services/logger.service";
import { ConsoleService } from "app/services/console.service";


@NgModule({
  imports: [
  ],
  providers: [
    CompanyService,
    EmployeeService,
    UserAccountService,
    AuthService,
    ConsoleService,
    LoggerService
  ],
  declarations: [
  ],
  exports: [
  ]
})
export class ServicesModule {
  constructor( @Optional() @SkipSelf() parentModule: ServicesModule) {
    throwIfAlreadyLoaded(parentModule, 'ServicesModule');
  }
}
