package ai.bi.com.charts.bean;

import java.util.Map;

/**
 * ���ڵ�
 * 
 * @author 
 * @version 1.0.0.0
 */
public class TreeNode 
{
	/** �ڵ�Id*/
	private String nodeId;
	/** ���ڵ�Id*/
	private String parentId;
	/** �ı�����*/
	private Map<String, Object> text;
	
	/**
	 * ���캯��
	 * 
	 * @param nodeId �ڵ�Id
	 */
	public TreeNode(String nodeId)
	{
		this.nodeId = nodeId;
	}
	
	/**
	 * ���캯��
	 * 
	 * @param nodeId �ڵ�Id
	 * @param parentId ���ڵ�Id
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
