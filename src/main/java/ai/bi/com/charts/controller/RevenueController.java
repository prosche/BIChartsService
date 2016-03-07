package ai.bi.com.charts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ai.bi.com.charts.bean.RevenueBean;
import ai.bi.com.charts.service.RevenueService;

@Controller
@RequestMapping("/revenueController")
public class RevenueController {
	private static final Logger log = LogManager.getLogger();
	@Autowired
	private RevenueService revenueService;

	@RequestMapping(value = "/revenue")
	public String revenue() {
		return "revenue";
	}

	@RequestMapping(value = "/getRevenue", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getRevenue(HttpServletRequest request,
			HttpServletResponse response) {
		String optimeStart = request.getParameter("optimeStart").replace("-",
				"");
		String optimeEnd = request.getParameter("optimeEnd").replace("-", "");
		String[] citys = request.getParameterValues("city");
		String[] countys = request.getParameterValues("county");
		RevenueBean revenue = new RevenueBean(optimeStart, optimeEnd, citys,
				countys);
		String data = revenueService.getRevenueTree(revenue);
		log.debug(data);
		return data;
	}

}
