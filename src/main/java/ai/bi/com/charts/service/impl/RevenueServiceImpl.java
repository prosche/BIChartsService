package ai.bi.com.charts.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.bi.com.charts.dao.RevenueDao;
import ai.bi.com.charts.dao.RevenueTypeDao;
import ai.bi.com.charts.model.Revenue;
import ai.bi.com.charts.service.RevenueService;

@Service("revenueService")
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private RevenueDao revenueDao;
	@Autowired
	private RevenueTypeDao revenueTypeDao;

	@Override
	public List<Revenue> getRevenue() {
		revenueDao.getObjectALL();
		return null;
	}
	@Override
	public List<Revenue> getRevenueTypeAll() {
		revenueTypeDao.getAll();
		return null;
	}
}
