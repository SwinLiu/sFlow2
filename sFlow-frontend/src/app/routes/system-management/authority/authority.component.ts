import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';

@Component({
    selector: 'sFlow-main-container',
    templateUrl: './authority.component.html'
})
export class AuthorityComponent implements OnInit {

    constructor(private location: Location) { }
    
    ngOnInit() {
    }

    goToBack() {
    this.location.back();
    }
}
