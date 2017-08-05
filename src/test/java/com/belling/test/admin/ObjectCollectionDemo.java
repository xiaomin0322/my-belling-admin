package com.belling.test.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belling.base.util.JxlsUtils;

/**
 * Object collection output demo
 * @author Leonid Vysochyn
 */
public class ObjectCollectionDemo {
	static Logger logger = LoggerFactory.getLogger(ObjectCollectionDemo.class);
	
    public static void main(String[] args) throws ParseException, IOException {
    	logger.info("Running Object Collection demo");
    	
        List<Employee> employees = generateSampleEmployeeData();
        OutputStream os = new FileOutputStream("target/object_collection_output.xls");
        Map<String , Object> model=new HashMap<String , Object>();
        model.put("employees", employees);
        model.put("nowdate", new Date());
        JxlsUtils.exportExcel("object_collection_template.xls", os, model);
        os.close();
    }

    public static List<Employee> generateSampleEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add( new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15) );
        employees.add( new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25) );
        employees.add( new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00) );
        employees.add( new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15) );
        employees.add( new Employee("John", dateFormat.parse("1969-May-30"), 2800, 0.20) );
        return employees;
    }
}
