import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import { Router } from "@angular/router";
import { UserAccountService } from "app/services/user/user-account.service";
import { LoggerService } from "app/services/logger.service";
import { EmployeeService } from "app/services/employee/employee.service";
import { CompanyService } from "app/services/company/company.service";
import { CONSTANTS } from "app/app.const";
import { NzNotificationService } from "ng-zorro-antd";

@Component({
  selector: 'sFlow-employee-container',
  templateUrl: './employee-list.component.html'
})
export class EmployeeListComponent implements OnInit {

  public companyList = [];
  public selectedComp;
  public employeeList = [];

  constructor(
    private _notification: NzNotificationService,
    private employeeService: EmployeeService,
    private companyService: CompanyService,
    private loggerService: LoggerService,
    private location: Location,
    private router: Router) { }

  ngOnInit() {

    this.companyService.getCompanyDropdownList().then(data => this.renderListPage(data));

  }

  renderListPage(res) {
    this.companyList = res.result;
    if (this.companyList.length > 0) {
      this.selectedComp = this.companyList[0].value;
      this.renderEmployeeList();
    } else {
      this.employeeList = [];
    }
  }

  selectedCompChange() {
    this.renderEmployeeList();
  }

  renderEmployeeList() {
    this.employeeService.getEmployeeList(this.selectedComp).then(data => {
      this.employeeList = data.result;
    });
  }

  goToBack() {
    this.location.back();
  }

  deleteEmployee(empId: string) {
    this.employeeService.deleteEmployee(empId).then(data => {
      if (data.success) {
        this.renderEmployeeList();
      } else {
        console.error(data.message);
      }
    });
  }

  showAddNew() {
    if (this.selectedComp) {
      this.router.navigate([CONSTANTS.ROUTE_URL.employee.add, this.selectedComp]);
    } else {
      this._notification.create('warning', 'No company data.', '');
    }

  }



}

