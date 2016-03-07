package ai.bi.com.charts.bean;

import java.util.Map;

/**
 * 树节点
 * 
 * @author 
 * @version 1.0.0.0
 */
public class TreeNode 
{
	/** 节点Id*/
	private String nodeId;
	/** 父节点Id*/
	private String parentId;
	/** 文本内容*/
	private Map<String, Object> text;
	
	/**
	 * 构造函数
	 * 
	 * @param nodeId 节点Id
	 */
	public TreeNode(String nodeId)
	{
		this.nodeId = nodeId;
	}
	
	/**
	 * 构造函数
	 * 
	 * @param nodeId 节点Id
	 * @param parentId 父节点Id
	 */
	public TreeNode(String nodeId, String parentId, Map<String, Object> text)
	{
		this.nodeId = nodeId;
		this.parentId = parentId;
		this.text = text;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Map<String, Object> getText() {
		return text;
	}

	public void setText(Map<String, Object> text) {
		this.text = text;
	}
	
}
