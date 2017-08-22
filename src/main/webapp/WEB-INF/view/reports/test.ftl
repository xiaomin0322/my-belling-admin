<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>百灵权限管理系统</title>
	<#include "/widget/common-css.html">
</head>
<body class="body">

  <img src="${base}${fileUrl}" />
  
	<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'layer', 'element' ], function() {
			var layer = layui.layer, element = layui.element();
			element.on('collapse(collapse)', function(data) {
					
			});
		});
	</script>
</body>
</html>