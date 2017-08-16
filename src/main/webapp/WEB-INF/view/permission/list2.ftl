<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>权限列表</title>
	<#include "/widget/common-css.html">
	<link rel="stylesheet" href="${base}/static/plugins/zTree/css/metroStyle/metroStyle.css?v=1" />
	<link rel="stylesheet" href="${base}/static/plugins/zTree/css/metroStyle/metroStyle.custom.css" />
	<style type="text/css">
		.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
		.ztree li ul.level0 {padding:0; background:none;}
		
		.container{
            width: 100%;
            white-space: nowrap;
            display:inline-block;
        }
        .box{
            width: 300px;
            display: inline-block;
            vertical-align: top;
        }
        
	</style>
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title none">
		<legend>
			权限树节点
		</legend>
	</fieldset>
	
	<div class="container">
	<div class="box">
	<#-- Table  -->
	<ul id="_tree" class="ztree" style="height:auto; overflow:auto;"></ul>
     </div>
     
     <div class="box">
    <#-- Table  -->
	<table id="dataTable" class="layui-table">
		<thead>
			<tr>
				<th width="50">登录账号</th>
				<th width="20" >登录时间</th>
				<th  width="50">登录IP</th>
				<th  width="50">登录方式</th>
				<th  width="50">备注</th>
			</tr>
		</thead>
		<tbody>
		  <tr>
				<th width="50">登录账号</th>
				<th width="20" >登录时间</th>
				<th  width="50">登录IP</th>
				<th  width="50">登录方式</th>
				<th  width="50">备注</th>
			</tr>
			<tr>
				<th width="50">登录账号</th>
				<th width="20" >登录时间</th>
				<th  width="50">登录IP</th>
				<th  width="50">登录方式</th>
				<th  width="50">备注</th>
			</tr>
			<tr>
				<th width="50">登录账号</th>
				<th width="20" >登录时间</th>
				<th  width="50">登录IP</th>
				<th  width="50">登录方式</th>
				<th  width="50">备注</th>
			</tr>
		</tbody>
	</table>
	  </div>
</div>
	<#-- 单选按钮   -->
	<script id="opBtn" type="text/html">
		<@shiro.hasPermission name="sys:permission:edit">  
		&nbsp;<a id='add_{{ d.id }}' class='blue' href='javascript:void(0)' title='添加'>		
				<i class='layui-icon'>&#xe654;</i>	
			 </a>
		</@shiro.hasPermission> 
			
		{{# if (d.id) { }}
			<@shiro.hasPermission name="sys:permission:edit">  
			&nbsp;<a id='edit_{{ d.id }}' class='green'  href='javascript:void(0)' title='修改'>
					<i class='layui-icon'>&#xe642;</i>	
				</a>
			</@shiro.hasPermission> 
			
			<@shiro.hasPermission name="sys:permission:delete">  
			&nbsp;<a id='delete_{{ d.id }}' class='red' href='javascript:void(0)' title='删除'>
					<i class='layui-icon'>&#xe640;</i>
				 </a>
			</@shiro.hasPermission>
		{{# } }}
	</script>
	<script type="text/javascript">
	<!--
		var WEB_ROOT = '${base}';
	//-->
	</script>
	<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="${base}/static/plugins/datatables/1.10.13/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugins//zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="${base}/static/js/app/permission/list.js"></script>
</body>
</html>