package com.belling.admin.controller.houtai;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

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
@RequestMapping("/admin/loginlog")
public class LoginLogCtroller extends BaseController {
	
	/**
	 * 登录日志业务对象
	 */
	@Autowired
	private LoginLogService loginLogService;
	
	
	/**
	 * 日志列表首页
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:loginlog:list")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("showField",findPermissionByUserIdShowField("sys:loginlog:list"));
		return "/loginlog/listShowField";
	}
	
	
    @RequestMapping(value = "/upload")  
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);
           String content = FileUtils.readFileToString(targetFile);
           System.out.println("文件内容为："+content);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        List<Role> roles = JxlsUtils.getExcelData(JxlsUtils.getTemplate("roles_output_test.xls"), JxlsUtils.getTemplate("roles_config.xml"), Role.class);
		for(Role r:roles){
			System.out.println("=========="+r.getName());
		}
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
        return "result";  
    } 
	
	/**
	 * 用户分页
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public TablePageResult page(HttpServletRequest req, String uId, Integer startTime, Integer endTime, Integer draw) {
		String curpage = req.getParameter("start");
		if (Strings.isNullOrEmpty(curpage)) {
			curpage = "0";
		}
		int start = Integer.parseInt(curpage);
		if (start <= 0) {
			start = 0;
		}
		String curlg = req.getParameter("length");
		if (Strings.isNullOrEmpty(curlg)) {
			curlg = "1";
		}
		int length = Integer.parseInt(curlg);
		if (length <=  0) {
			length = 8;
		}
		Pagination<LoginLog> page = new Pagination<LoginLog>((start / length) + 1, length);
		page = loginLogService.findPaginationByTime(uId, null == startTime ? null : new Timestamp(startTime * 1000L), null == endTime ? null : new Timestamp(endTime * 1000L), page);
		return TablePageResult.createSuccessResult(page.getList(), page.getRowCount(), draw + 1);
	}
	
	/**
	 * 清理日志
	 * 
	 * @param startTime
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseResult delete(Integer startTime) {
		loginLogService.deleteByTime(null == startTime ? null : new Timestamp(startTime * 1000L));
		return ResponseResult.createSuccessResult().setMessage("成功清理");
	}
}
