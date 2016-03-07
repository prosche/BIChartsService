package ai.bi.com.charts.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.bi.com.charts.bean.ManyTreeNode;
import ai.bi.com.charts.bean.TreeNode;

/**
 * 多叉树生成、遍历工具
 * 
 * @author
 * @version 1.0.0.0
 */
public class ManyNodeTree {
	/** 树根 */
	private ManyTreeNode root;

	/**
	 * 构造函数
	 */
	public ManyNodeTree() {
		root = new ManyTreeNode(new TreeNode("root"));
	}

	/**
	 * 生成一颗多叉树，根节点为root
	 * 
	 * @param treeNodes
	 *            生成多叉树的节点集合
	 * @return ManyNodeTree
	 */
	public ManyNodeTree createTree(List<TreeNode> treeNodes) {
		if (treeNodes == null || treeNodes.size() < 0)
			return null;

		ManyNodeTree manyNodeTree = new ManyNodeTree();

		// 将所有节点添加到多叉树中
		for (TreeNode treeNode : treeNodes) {
			if (treeNode.getParentId().equals("root")) {
				// 向根添加一个节点
				manyNodeTree.getRoot().getChildList()
						.add(new ManyTreeNode(treeNode));
			} else {
				addChild(manyNodeTree.getRoot(), treeNode);
			}
		}

		return manyNodeTree;
	}

	/**
	 * 向指定多叉树节点添加子节点
	 * 
	 * @param manyTreeNode
	 *            多叉树节点
	 * @param child
	 *            节点
	 */
	public void addChild(ManyTreeNode manyTreeNode, TreeNode child) {
		for (ManyTreeNode item : manyTreeNode.getChildList()) {
			if (item.getData().getNodeId().equals(child.getParentId())) {
				// 找到对应的父亲
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
	 * 遍历多叉树
	 * 
	 * @param manyTreeNode
	 *            多叉树节点
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
						+ "</div><div style='color:black;' title='当期账单金额(万元)' readonly >当期:"
						+ index.getData().getText().get("VALUE1")
						+ "</div>"
						+ "<div style='color:black;' title='上月账单金额(万元)' readonly >上期:"
						+ index.getData().getText().get("VALUE2")
						+ "</div>"
						+ "<div style='color:black;' title='去年同期账单金额(万元)' readonly >同期:"
						+ index.getData().getText().get("VALUE3") + "</div>"
						+ "<div style='color:" + map.get("VALUE4color")
						+ ";' title='环比(%)：当期账单金额/上月账单金额-1' readonly >环比:"
						+ index.getData().getText().get("VALUE4")
						+ map.get("VALUE4type") + "</div>" + "<div style='color:"
						+ map1.get("VALUE5color")
						+ ";' title='同比(%)：当期账单金额/去年同期账单金额-1' readonly >同比:"
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
		String up = "&nbsp&nbsp↑";
		String downColor = "red";
		String down = "&nbsp&nbsp↓";
		map.put(type + "color", downColor);
		map.put(type + "type", down);
		if (value > 0) {
			map.put(type + "color", upColor);
			map.put(type + "type", up);
		}
		return map;
	}

}
