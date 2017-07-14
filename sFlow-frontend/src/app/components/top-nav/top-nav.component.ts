import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'sFlow-top-nav',
  templateUrl: './top-nav.component.html'
})
export class TopNavComponent implements OnInit {

  title = 'sFlow2';

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }
}
