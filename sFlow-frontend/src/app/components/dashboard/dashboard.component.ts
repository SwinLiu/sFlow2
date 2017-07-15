import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TestService } from '../../services/test.service';

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {

  title = 'sFlow2';

  constructor(
    private router: Router,
    private testService: TestService) {
  }

  ngOnInit(): void {
    //this.testService.getTest();
  }
}
