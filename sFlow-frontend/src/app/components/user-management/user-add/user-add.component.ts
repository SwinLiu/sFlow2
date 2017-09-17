import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';

@Component({
  selector: 'sFlow-user-add',
  templateUrl: './user-add.component.html'
})
export class UserAddComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
  }

  goToBack() {
    this.location.back();
  }

}

