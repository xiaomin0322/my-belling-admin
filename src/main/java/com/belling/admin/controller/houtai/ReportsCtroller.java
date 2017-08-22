package com.belling.admin.controller.houtai;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ChartDirector.Chart;
import ChartDirector.PieChart;

import com.belling.admin.model.LoginLog;
import com.belling.admin.model.Role;
import com.belling.admin.service.LoginLogService;
import com.belling.base.controller.BaseController;
import com.belling.base.model.Pagination;
import com.belling.base.model.ResponseResult;
import com.belling.base.model.TablePageResult;
import com.belling.base.util.JxlsUtils;
import com.google.common.base.Strings;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月27日 下午4:48:39  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/admin/reports")
public class ReportsCtroller extends BaseController {
	
	
	
    @RequestMapping(value = "/test")  
    public String upload( HttpServletRequest request, ModelMap model) {  
        System.out.println("开始");  
        
        // The data for the pie chart
        double[] data = {25, 18, 15, 12, 8, 30, 35};

        // The labels for the pie chart
        String[] labels = {"Labor", "Licenses", "Taxes", "Legal", "Insurance", "Facilities",
            "Production"};
        
        // Create a PieChart object of size 280 x 240 pixels
        PieChart c = new PieChart(280, 240);

        // Set the center of the pie at (140, 130) and the radius to 80 pixels
        c.setPieSize(140, 130, 80);

        // Add a title to the pie to show the start angle and direction
            c.addTitle("Start Angle = 90 degrees\nDirection = AntiClockwise");
            c.setStartAngle(90, false);

        // Draw the pie in 3D
        c.set3D();

        // Set the pie data and the pie labels
        c.setData(data, labels);

        // Explode the 1st sector (index = 0)
        c.setExplode(0);
        
        byte[] bytes = c.makeChart(Chart.PNG);
        
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = UUID.randomUUID().toString()+".png";
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        try {
			FileUtils.writeByteArrayToFile(targetFile, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        model.addAttribute("fileUrl", "/upload/"+fileName);  
        return "/reports/test";  
    } 
	
}
