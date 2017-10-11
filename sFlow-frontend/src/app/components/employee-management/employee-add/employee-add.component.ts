import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import * as crypto from 'crypto-browserify';
import { AuthService } from "app/services/auth.service";
import { LoggerService } from "app/services/logger.service";
import { Router, ActivatedRoute } from "@angular/router";
import { CONSTANTS } from "app/app.const";
import { EmployeeService } from "app/services/employee/employee.service";

declare const Buffer;

@Component({
  selector: 'sFlow-employee-add',
  templateUrl: './employee-add.component.html'
})
export class EmployeeAddComponent implements OnInit {

  newEmployee = {
    compId : "",
    employeeId : "",
    surName : "",
    givenName : "",
    gender : "0",
    birthday : "",
    workEmail : ""
  }

  errorMsg = "";

  constructor(
    private routerIonfo: ActivatedRoute,
    private employeeService: EmployeeService,
    private loggerService: LoggerService,
    private location: Location,
    private router: Router) { }

  ngOnInit() {

    this.newEmployee.compId = this.routerIonfo.snapshot.params["compId"];

  }

  addNew() {

    this.employeeService.addEmployee(this.newEmployee).then(
      data => {
        if (data.success) {
          this.router.navigate([CONSTANTS.ROUTE_URL.employee.list]);
        } else {
          this.errorMsg = data.message;
        }
      }
    );

  }

  goToBack() {
    this.location.back();
  }

}

