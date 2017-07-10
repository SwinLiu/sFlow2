import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'sFlow2';

    constructor() {
    console.log("Test1 AppComponent");
}

  ngOnInit(): void {
    console.log("Test AppComponent");
    //this.testService.getTest();
  }

}
