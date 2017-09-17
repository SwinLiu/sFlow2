import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';

@Component({
  selector: 'sFlow-main-container',
  templateUrl: './company-management.component.html'
})
export class CompanyManagementComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }

  goToBack() {
    this.location.back();
  }

}

