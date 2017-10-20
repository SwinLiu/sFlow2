package com.lyplay.sflow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.data.domain.system.Menu;
import com.lyplay.sflow.data.repository.MenuRepository;
import com.lyplay.sflow.service.MenuService;
import com.lyplay.sflow.service.dto.MenuTree;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public List<MenuTree> getFullMenuList() {
		Sort sort = new Sort(Direction.ASC, "menuId");
		List<Menu> menuList = menuRepository.findAll(sort);
		return buildMenuTree(menuList);
	}

	private List<MenuTree> buildMenuTree(List<Menu> menuList) {
		List<MenuTree> menuTreeList = new ArrayList<>();
		if(CollectionUtils.isEmpty(menuList)){
			return menuTreeList;
		} else {
			Map<String, MenuTree> menuTreeMap = new HashMap<String, MenuTree>();
			for(Menu menu : menuList){
				MenuTree menuNode = new MenuTree();
				menuNode.setText(menu.getText());
				menuNode.setTranslate(menu.getTranslate());
				menuNode.setIcon(menu.getIcon());
				menuNode.setLink(menu.getLink());
				menuNode.setGroup(menu.getGroup());
				menuTreeMap.put(menu.getMenuId(), menuNode);
			}
			
			for(Menu menu : menuList){
				MenuTree currentMenu = menuTreeMap.get(menu.getMenuId());
				if(StringUtils.isEmpty(menu.getParentId())){
					menuTreeList.add(currentMenu);
				} else {
					MenuTree parentNode = menuTreeMap.get(menu.getParentId());
					if(parentNode == null) {
						menuTreeList.add(currentMenu);
					} else {
						parentNode.addChildMenu(currentMenu);
					}
				}
			}
		}
		return menuTreeList;
	}

}
