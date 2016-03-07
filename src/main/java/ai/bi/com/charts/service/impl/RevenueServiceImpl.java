package ai.bi.com.charts.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.bi.com.charts.bean.RevenueBean;
import ai.bi.com.charts.bean.TreeNode;
import ai.bi.com.charts.dao.RevenueDao;
import ai.bi.com.charts.model.Revenue;
import ai.bi.com.charts.service.RevenueService;
import ai.bi.com.charts.util.ManyNodeTree;

@Service("revenueService")
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private RevenueDao revenueDao;
	
	@Override
	public List<Revenue> getRevenue() {
		return revenueDao.getObjectALL();
	}

	@Override
	public String getRevenueTree(RevenueBean revenue){
		List<Map<String, Object>> list = revenueDao.getSql(revenue);
		String data = null;
		if (list != null && list.size() != 0) {
			List<TreeNode> treeNodes = new ArrayList<TreeNode>();
			Iterator<Map<String, Object>> iterator = list.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> entry = iterator.next();
				treeNodes.add(new TreeNode(entry.get("ID").toString(), entry
						.get("FID").toString(), entry));
			}
			ManyNodeTree tree = new ManyNodeTree();
			data = tree.iteratorTree(tree.createTree(treeNodes).getRoot());
		}
		return data;
	}
}
