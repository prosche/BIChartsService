package ai.bi.com.charts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ai.bi.com.charts.bean.RevenueBean;
import ai.bi.com.charts.model.Revenue;
import ai.bi.com.charts.service.RevenueService;

@Controller
@RequestMapping("/revenueController")
//@Scope("prototype")
public class RevenueController {
	private static final Logger log = LogManager.getLogger();
	@Autowired
	private RevenueService revenueService;

	@RequestMapping(value="/getRevenue")
	public String getRevenue(RevenueBean revenueBean) {
		List<Revenue> list = revenueService.getRevenue();
		log.debug(list);
		List<Revenue> list1 = revenueService.getRevenueTypeAll();
		log.debug(list1);
		return "www/fail";
	}
}
