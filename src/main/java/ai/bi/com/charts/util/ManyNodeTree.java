package ai.bi.com.charts.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.bi.com.charts.bean.ManyTreeNode;
import ai.bi.com.charts.bean.TreeNode;

/**
 * ��������ɡ���������
 * 
 * @author
 * @version 1.0.0.0
 */
public class ManyNodeTree {
	/** ���� */
	private ManyTreeNode root;

	/**
	 * ���캯��
	 */
	public ManyNodeTree() {
		root = new ManyTreeNode(new TreeNode("root"));
	}

	/**
	 * ����һ�Ŷ���������ڵ�Ϊroot
	 * 
	 * @param treeNodes
	 *            ���ɶ�����Ľڵ㼯��
	 * @return ManyNodeTree
	 */
	public ManyNodeTree createTree(List<TreeNode> treeNodes) {
		if (treeNodes == null || treeNodes.size() < 0)
			return null;

		ManyNodeTree manyNodeTree = new ManyNodeTree();

		// �����нڵ���ӵ��������
		for (TreeNode treeNode : treeNodes) {
			if (treeNode.getParentId().equals("root")) {
				// ������һ���ڵ�
				manyNodeTree.getRoot().getChildList()
						.add(new ManyTreeNode(treeNode));
			} else {
				addChild(manyNodeTree.getRoot(), treeNode);
			}
		}

		return manyNodeTree;
	}

	/**
	 * ��ָ��������ڵ�����ӽڵ�
	 * 
	 * @param manyTreeNode
	 *            ������ڵ�
	 * @param child
	 *            �ڵ�
	 */
	public void addChild(ManyTreeNode manyTreeNode, TreeNode child) {
		for (ManyTreeNode item : manyTreeNode.getChildList()) {
			if (item.getData().getNodeId().equals(child.getParentId())) {
				// �ҵ���Ӧ�ĸ���
				item.getChildList().add(new ManyTreeNode(child));
				break;
			} else {
				if (item.getChildList() != null
						&& item.getChildList().size() > 0) {
					addChild(item, child);
				}
			}
		}
	}

	/**
	 * ���������
	 * 
	 * @param manyTreeNode
	 *            ������ڵ�
	 * @return
	 */
	public String iteratorTree(ManyTreeNode manyTreeNode) {
		StringBuilder buffer = new StringBuilder();
		if (manyTreeNode != null) {
			for (ManyTreeNode index : manyTreeNode.getChildList()) {
				Map<String, String> map = getDivValue(Double.valueOf(index
						.getData().getText().get("VALUE4").toString()),"VALUE4");
				Map<String, String> map1 = getDivValue(Double.valueOf(index
						.getData().getText().get("VALUE5").toString()),"VALUE5");

				buffer.append("{id:\""
						+ index.getData().getNodeId()
						+ "\",name: \"<div style='font-weight:bold;margin-top: 5px;margin-bottom: 5px;color:#000080'>"
						+ index.getData().getText().get("NAME")
						+ "</div><div style='color:black;' title='�����˵����(��Ԫ)' readonly >����:"
						+ index.getData().getText().get("VALUE1")
						+ "</div>"
						+ "<div style='color:black;' title='�����˵����(��Ԫ)' readonly >����:"
						+ index.getData().getText().get("VALUE2")
						+ "</div>"
						+ "<div style='color:black;' title='ȥ��ͬ���˵����(��Ԫ)' readonly >ͬ��:"
						+ index.getData().getText().get("VALUE3") + "</div>"
						+ "<div style='color:" + map.get("VALUE4color")
						+ ";' title='����(%)�������˵����/�����˵����-1' readonly >����:"
						+ index.getData().getText().get("VALUE4")
						+ map.get("VALUE4type") + "</div>" + "<div style='color:"
						+ map1.get("VALUE5color")
						+ ";' title='ͬ��(%)�������˵����/ȥ��ͬ���˵����-1' readonly >ͬ��:"
						+ index.getData().getText().get("VALUE5")
						+ map1.get("VALUE5type") + "</div>"
						+ "\",data:{},children:[");
				if (index.getChildList() != null
						&& index.getChildList().size() > 0) {
					buffer.append(iteratorTree(index));
				}
				buffer.append("]},");
			}
		}
		return buffer
				.toString()
				.replace("},]", "}]")
				.substring(0,
						buffer.toString().replace("},]", "}]").length() - 1);

	}

	public ManyTreeNode getRoot() {
		return root;
	}

	public void setRoot(ManyTreeNode root) {
		this.root = root;
	}

	public Map<String, String> getDivValue(Double value, String type) {
		Map<String, String> map = new HashMap<String, String>();
		String upColor = "green";
		String up = "&nbsp&nbsp��";
		String downColor = "red";
		String down = "&nbsp&nbsp��";
		map.put(type + "color", downColor);
		map.put(type + "type", down);
		if (value > 0) {
			map.put(type + "color", upColor);
			map.put(type + "type", up);
		}
		return map;
	}

}
