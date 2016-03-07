package ai.bi.com.charts.service;

import java.util.List;

import ai.bi.com.charts.bean.RevenueBean;
import ai.bi.com.charts.model.Revenue;

public interface RevenueService{

	List<Revenue> getRevenue();

	String getRevenueTree(RevenueBean revenue);

}
