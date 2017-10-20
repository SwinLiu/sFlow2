package com.lyplay.sflow.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.service.dto.MenuTree;

public class MenuServiceTest extends BaseTest{

	@Autowired
	MenuService menuService;
	
	@Test
	public void testMenuList() {
		
		List<MenuTree> list = menuService.getFullMenuList();
		
		for(MenuTree menuTree : list){
			printTree(menuTree);
		}
		
	}
	
	private void printTree(MenuTree menuTree){
		System.out.println(menuTree.getText());
		System.out.println("Children menus: ");
		for(MenuTree child : menuTree.getChildren()){
			printTree(child);
		}
	}
}
