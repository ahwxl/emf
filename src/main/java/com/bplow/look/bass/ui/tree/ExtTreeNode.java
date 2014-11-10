package com.bplow.look.bass.ui.tree;

public class ExtTreeNode {
	
	private String text;
	private String id;
	private Boolean leaf;
	private String cls;//节点的图标
	private Boolean checked ;
	
	public ExtTreeNode() {
		super();
	}


	public ExtTreeNode(String text, String id, Boolean leaf, String cls, Boolean checked) {
		super();
		this.text = text;
		this.id = id;
		this.leaf = leaf;
		this.cls = cls;
		this.checked = checked;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Boolean getLeaf() {
		return leaf;
	}


	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}


	public String getCls() {
		return cls;
	}


	public void setCls(String cls) {
		this.cls = cls;
	}


	public Boolean getChecked() {
		return checked;
	}


	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	

}
