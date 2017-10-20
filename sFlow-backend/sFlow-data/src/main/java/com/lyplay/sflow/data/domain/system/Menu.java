package com.lyplay.sflow.data.domain.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name="sf_sys_menu")
public class Menu implements Serializable{
	
	private static final long serialVersionUID = -7974738479680072884L;

	@Id
	@Column(name = "menu_id")
	private String menuId;

	@Column(name = "text")
	private String text;

	@Column(name = "translate")
	private String translate;
	
	@Column(name = "group")
	private Boolean group;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "icon")
	private String icon;
	
	@Column(name = "parent_id")
	private String parentId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

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

	public Boolean getGroup() {
		return group;
	}

	public void setGroup(Boolean group) {
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

