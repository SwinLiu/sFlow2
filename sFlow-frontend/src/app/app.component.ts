import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor() {
    console.log("Test1 AppComponent");
  }

  ngOnInit(): void {
    console.log("Test AppComponent");
    //this.testService.getTest();
  }

}
