import {Location} from '@angular/common';
import { Component, NgModule, OnInit } from '@angular/core';
import { SysMenuService } from "app/services/system/sys-menu.service";
import { MenuTreeNode } from "app/beans/menuTreeNode";

@Component({
    selector: 'sFlow-main-container',
    templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

    editFlag = false;

    loading = false;

    public treeNodes = [];

    public menuTreeData: any;

    constructor(private location: Location,
        private sysMenuService: SysMenuService) { }
    
    ngOnInit() {
        this.loading = true;
        this.sysMenuService.getMenuList()
            .then(data => {
                this.menuTreeData = data.result;
                this.generateMenuTree();
                this.loading = false;
            });
    }

    generateMenuTree() {
        for (let i = 0; i < this.menuTreeData.length; i ++) {
            this.getMenuTreeNode(this.menuTreeData[i], 1, i, this.menuTreeData.length);
        }
    }

    getMenuTreeNode(node, deep, index, brotherNum) {
        const treeNode: MenuTreeNode = new MenuTreeNode();
        treeNode.menuId = node.menuId;
        treeNode.text = node.text;
        treeNode.translate = node.translate;
        treeNode.group = node.group;
        treeNode.link = node.link;
        treeNode.icon = node.icon;
        treeNode.parentId = node.parentId;
        treeNode.orderNumber = node.orderNumber;
        treeNode.authorityId = node.authorityId;
        treeNode.editable = true;
        treeNode.deleteable = true;
        treeNode.deep = deep - 1;
        
        if (index === 0) {
            treeNode.up = false;
        } else {
            treeNode.up = true;
        }

        if (index === brotherNum - 1 ) {
            treeNode.down = false;
        } else {
            treeNode.down = true;
        }

        if (deep >= 3) {
            treeNode.addChildFlag = false;
        } else {
            treeNode.addChildFlag = true;
        }
        treeNode.children = [];
        this.treeNodes.push(treeNode);
        if (node.children.length > 0) {
            for (let i = 0; i < node.children.length; i ++) {
                this.getMenuTreeNode(node.children[i], deep + 1, i, node.children.length);
            }
        }
    }

    goToBack() {
        this.location.back();
    }

    deleteMenu(menuId: string) {
    
    }

    getArray(size) {
        return new Array(size);
    }

    editMenu() {
        this.editFlag = true;
    }

    cancelEditMenu() {
        this.generateMenuTree();
        this.editFlag = false;
    }
}
