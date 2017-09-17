import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';

@Component({
  selector: 'sFlow-main-container',
  templateUrl: './user-management.component.html'
})
export class UserManagementComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }

  goToBack() {
    this.location.back();
  }

}

