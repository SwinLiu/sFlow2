import {Location} from '@angular/common';
import { Component, NgModule, OnInit } from '@angular/core';

@Component({
    selector: 'sFlow-main-container',
    templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

    nodes = [];

    options = {
        autoExpandParent: true,
        allowDrag: true
    };

    constructor(private location: Location) { }
    
    ngOnInit() {
        this.generateData(this.nodes, 3, 2, 1);
        console.log("-----");
        console.log(this.nodes);
        console.log("-----");
    }

    goToBack() {
        this.location.back();
    }

    generateData(nodes, _x, _y, _z, _preKey?: any, _tns?: any) {
        const preKey = _preKey || '0';
        const tns = _tns || nodes;
    
        const children = [];
        for (let i = 0; i < _x; i++) {
          const key = `${preKey}-${i}`;
          tns.push({ name: key, id: key, disableCheckbox: true });
          if (i < _y) {
            children.push(key);
          }
        }
        if (_z < 0) {
          return tns;
        }
        const level = _z - 1;
        children.forEach((key, index) => {
          tns[index].children = [];
          return this.generateData(nodes, _x, _y, level, key, tns[index].children);
        });
      }
}
