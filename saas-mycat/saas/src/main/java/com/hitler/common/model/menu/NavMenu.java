package com.hitler.common.model.menu;

import java.io.Serializable;
import java.util.Collection;

/**
 * 导航栏菜单vo
 * @author Kylin
 * 2015-10-10 上午9:44:27
 */
public class NavMenu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 显示内容
	 */
	public String text;
	/**
	 * 图标
	 */
	public String icon;
	/**
	 * 子节点
	 */
	public Collection<? extends Serializable> children;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Collection<? extends Serializable> getChildren() {
		return children;
	}
	
	public void setChildren(Collection<? extends Serializable> children) {
		this.children = children;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NavMenu other = (NavMenu) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
}
