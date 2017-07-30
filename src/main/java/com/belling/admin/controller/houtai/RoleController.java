package com.belling.admin.controller.houtai;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belling.admin.model.Role;
import com.belling.admin.service.RoleService;
import com.belling.base.controller.BaseController;
import com.belling.base.model.Pagination;
import com.belling.base.model.ResponseResult;
import com.belling.base.model.TablePageResult;
import com.belling.base.util.JxlsUtils;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月23日 上午8:48:33  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	/**
	 * 角色业务对象
	 */
	@Autowired
	private RoleService roleService;
	
	
	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:list")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "/role/list";
	}
	
	@RequestMapping(value = "/rolesExport")
	public void export(HttpServletRequest request,HttpServletResponse response) {
	    List<Role> rolesList = roleService.findAll(true);
	    Map<String , Object> model=new HashMap<String , Object>();
        model.put("roles", rolesList);
        model.put("nowdate", new Date());
        
	    String destFileName = "roles_template_output.xls";
	    String templateFileName = "roles_template.xls";
	    InputStream in=null;  
        OutputStream out=null;  
        //设置响应  
        response.setHeader("Content-Disposition", "attachment;filename=" + destFileName);  
        //response.setContentType("application/vnd.ms-excel");  
        //response.setContentType("application/x-download");
        //response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/octet-stream");
        try {  
            in=new BufferedInputStream(new FileInputStream(JxlsUtils.getTemplate(templateFileName)));  
            out=response.getOutputStream();  
            //FileOutputStream fileOutputStream = new FileOutputStream(destFileName);
            //JxlsUtils.exportExcel(in,fileOutputStream,model);
            //out.write(IOUtils.toByteArray(new FileInputStream(destFileName)));
            JxlsUtils.exportExcel(in,out,model);
            //将内容写入输出流并把缓存的内容全部发出去  
            out.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (in!=null){try {in.close();} catch (IOException e) {}}  
            if (out!=null){try {out.close();} catch (IOException e) {}}  
        }  
	}
	
	/**
	 * 保存或编辑角色
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		Role role;
		if (id == null) {
			role = new Role();
		} else {
			role = roleService.get(id);
		}
		model.addAttribute("vo", role);
		return "/role/edit";
	}
	
	/**
	 * 分页请求
	 * 
	 * @param kw
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public @ResponseBody TablePageResult page(String kw, Integer start, Integer length, Integer draw) {
		if (start <= 0) {
			start = 0;
		}
		if (length <=  0) {
			length = 8;
		}
		Pagination<Role> page  = new Pagination<Role>((start / length) + 1, length);
		page = roleService.findPaginationByName(kw, page);
		return TablePageResult.createSuccessResult(page.getList(), page.getRowCount(), draw + 1);
	}
	
	/**
	 * 是否启用
	 * 
	 * @param ids
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody ResponseResult enable(String ids, Boolean isEnable) {
		roleService.enable(isEnable, getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}
	
	/**
	 * 保存角色
	 * 
	 * @param id
	 * @param name
	 * @param sort
	 * @param description
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseResult save(Integer id, String name, String code, Integer sort, String description, Boolean isEnable) {
		Role role;
		if (id == null) {
			role = new Role();
		} else {
			role = roleService.get(id);
		}
		role.setName(name);
		role.setSort(sort);
		role.setCode(code);
		role.setDescription(description);
		role.setIsEnable(isEnable);
		roleService.save(role);
		return ResponseResult.createSuccessResult();
	}
	
	/**
	 * 角色删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseResult delete(String ids) {
		roleService.deleteById(getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}

}
