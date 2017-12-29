package com.lyplay.sflow.service.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
	
	private String text;
	private String translate;
	private boolean group;
	private String link;
	private String icon;
	private Integer badge;
	private String authorityId;
	private Integer orderNumber;
	private List<MenuTree> children = new ArrayList<MenuTree>();
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTranslate() {
		return translate;
	}
	public void setTranslate(String translate) {
		this.translate = translate;
	}
	public boolean isGroup() {
		return group;
	}
	public void setGroup(boolean group) {
		this.group = group;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getBadge() {
		return badge;
	}
	public void setBadge(Integer badge) {
		this.badge = badge;
	}
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	public void addChildMenu(MenuTree childMenu) {
		this.children.add(childMenu);
	}
    
}
