package com.belling.test.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import com.belling.admin.model.Role;
import com.belling.base.util.JxlsUtils;

public class TestJxlsUtils {

    public static void main(String[] args)throws Exception {
		List<Role> roles = 
				//getExcelData(JxlsUtils.getTemplate("roles_output_test.xls"), JxlsUtils.getTemplate("roles_config.xml"));
				JxlsUtils.getExcelData(JxlsUtils.getTemplate("roles_output_test.xls"), JxlsUtils.getTemplate("roles_config.xml"), Role.class);
		
		System.out.println("roles size =="+roles.size());
		for(Role r:roles){
			System.out.println(r.getName());
		}
	}
    
    /** 
     * 使用jxls解析导入的Excel 
     * @param path 导入文件路径 
     * @return List<VideoInfo> 导入对象集合 
     */  
    public static  List<Role> getExcelData(File path,File inputXML){  
        List<Role> objInfoList = new ArrayList<Role>();  
        try {  
            XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );  
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(path));  
            Map<String,Object> beans = new HashMap<String,Object>();  
            Role t = new Role();
            beans.put("objInfo", t);  
            beans.put("objList", objInfoList);  
            XLSReadStatus readStatus = mainReader.read( inputXLS, beans);  
            if(readStatus.isStatusOK()){  
                System.out.println("jxls读取Excel成功！");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return objInfoList;  
    }
    
    
}
