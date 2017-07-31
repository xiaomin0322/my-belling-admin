package com.belling.test.admin;

import java.util.List;

import com.belling.admin.model.Role;
import com.belling.base.util.JxlsUtils;

public class TestJxlsUtils {

    public static void main(String[] args)throws Exception {
		List<Role> roles = JxlsUtils.getExcelData(JxlsUtils.getTemplate("roles_output_test.xls"), JxlsUtils.getTemplate("roles_config.xml"), Role.class);
		for(Role r:roles){
			System.out.println(r.getName());
		}
	}
}
