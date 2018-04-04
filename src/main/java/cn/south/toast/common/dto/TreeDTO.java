package cn.south.toast.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
public class TreeDTO {
	/** id */
	private String id;
	/** 父id */
	private String parentId;
	/** 是否有下一级 */
	private boolean hasSub;
	/** 孩子 */
	private List<TreeDTO> children = new ArrayList<TreeDTO>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<TreeDTO> getChildren() {
		return children;
	}

	public boolean isHasSub() {
		return hasSub;
	}

	public void setHasSub(boolean hasSub) {
		this.hasSub = hasSub;
	}

	public void setChildren(List<TreeDTO> children) {
		this.children = children;
	}

}
