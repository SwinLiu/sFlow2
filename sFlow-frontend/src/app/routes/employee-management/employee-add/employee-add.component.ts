import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import { LoggerService } from "app/services/logger.service";
import { Router, ActivatedRoute } from "@angular/router";
import { CONSTANTS } from "app/app.const";
import { EmployeeService } from "app/services/employee/employee.service";


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
    birthday : null,
    workEmail : ""
  };
  

  // bsConfig: Partial<BsDatepickerConfig>;

  // datePickerConfig: DatePickerConfig = new DatePickerConfig();

  errorMsg = "";

  constructor(
    private routerIonfo: ActivatedRoute,
    private employeeService: EmployeeService,
    private loggerService: LoggerService,
    private location: Location,
    private router: Router) {

      // const minDate: Date = new Date();
      // const maxDate: Date = new Date();
      // minDate.setFullYear((minDate.getFullYear() - 100));
      // // maxDate.setFullYear((maxDate.getFullYear() - 13));

      // this.datePickerConfig.minDate = minDate;
      // this.datePickerConfig.maxDate = maxDate;
      // this.datePickerConfig.locale = this.appTranslateService.getSystemLang();
      // this.bsConfig = Object.assign({}, this.datePickerConfig);
  }

  renderDatePicker(pop: any) {
    // this.datePickerConfig.locale = this.appTranslateService.getSystemLang();
    // this.bsConfig = Object.assign({}, this.datePickerConfig);
    // setTimeout(() => {
    //   pop.hide();
    //   pop.show();
    // });
  }

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

