import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';

@Component({
  selector: 'sFlow-user-container',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }

  goToBack() {
    this.location.back();
  }

}

