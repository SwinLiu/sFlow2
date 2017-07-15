import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'sFlow-left-nav',
  templateUrl: './left-nav.component.html'
})
export class LeftNavComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }
}
