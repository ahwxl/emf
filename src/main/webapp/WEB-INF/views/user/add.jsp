<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
<title>添加用户页面</title>
<style type="text/css">
.headerStyle{color:blue}
.listBtn{color:red;text-underline-position : below;text-decoration: underline;cursor:pointer; }
.bodystyle TD{border:1px red ;background-color:#B0C4DE}
</style>
</head>
<body>
<h2 style="color:red">这个页面演示三种表单的提交方式:</h2>
<h2 class="headerStyle">思考问题：<br />
如何防止表单的重复提交问题<br />
为什么要用ajax提交<br />
什么是相对路径与绝对路径<br />
jquery如何给按钮绑定事件<br />
</h2>
<table class="bodystyle">
<tr><td><a class="listBtn">查看列表数据</a></td>
<td><a class="refreshbtn">刷新页面</a></td>
</tr>
</table>

<form action="add" method="post" id="myform" name="myform">
用户名：<input type="text" name="userName"  /> <br />
用户id：<input type="text" name="userid"  /><br />
用户密码：<input type="text" name="userpsw"  /><br />
<input type="button" value="form提交表单" onclick="doSubmit()"/><br />
<input type="button" value="jquery ajax 提交" onclick="doSubmitByAjax()"/><br />
</form>

<script type="text/javascript">
/*
 * 普通的form表单提交数据
 */
function doSubmit(){
	myform.action ="add";
	myform.method ="post";
	myform.submit();
}

/**
 * jquery ajax 提交表单 
 */

function doSubmitByAjax(){
	var argdata = $("#myform").serialize();//获取表单提交的form数据
	//alert(argdata);
	$.ajax({
		   type: "POST",
		   url: "addByAjax",
		   async : true,
		   data: argdata,//a=1&b=2
		   success: function(msg){
			 alert('哈哈！');
		     alert( msg );
		   },
		   error : function(XMLHttpRequest, textStatus, errorThrown){
			   alert('添加失败');
		   }
	       
		});
}


$(document).ready(function(){
	$('a.listBtn').click(function(){
		       window.location.href="showlist";//showlist
	         }
	);
	
	$("a.refreshbtn").click(function(){
	       window.location.reload(false);
    });
});

</script>

</body>
</html>