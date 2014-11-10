<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表展示页面</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-tab.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-dropdown.js"></script>
</head>
<body>
<div style="height:2px;"></div>
    <ul id="myTab" class="nav nav-tabs" >
    <li class="active"><a href="#home" data-toggle="tab">展示用户列表</a></li>
    <li><a href="#profile" data-toggle="tab">其他</a></li>
    <li class="dropdown">
    <a class="dropdown-toggle"
       data-toggle="dropdown"
       href="#">
        操作
        <b class="caret"></b>
      </a>
    <ul class="dropdown-menu">
      <li><a href="#addpage">添加</a></li>
      <li><a href="#">其他</a></li>
    </ul>
  </li>
    </ul>
<form action="" name="myform" id="myform" method="post">
</form>
<table class="table table-bordered table-striped">
<thead><th>用户名</th><th>用户id</th><th>用户密码</th><th>操作</th></<thead>
<tbody>
<#list userlist as user>
<tr>
<td>${user.userName }</td><td>${user.userid }</td><td>${user.userpsw }</td><td><a onclick="deluser('${user.id}')" >删除</a>   修改</td>
</tr>
</#list>
</tbody>
</table>
<div class="pagination pagination-centered">
  <ul>
    <li class="disabled"><a href="#">&laquo;</a></li>
    <li class="active"><a href="#">1</a></li>
    <li class="active"><a href="#">2</a></li>
    <li class="active"><a href="#">3</a></li>
    <li class="active"><a href="#">4</a></li>
    <li class="active"><a href="#">5</a></li>
    <li class="active"><a href="#">Next</a></li>
  </ul>
</div>


<div class="pagination pagination-centered">
  <ul>
            <#import "../pageftl/pagination.ftl" as com>  
            <#--前一个参数是总记录数，后一个参数是页面记录数-->  
            <@com.pagination page.totalRecord page.pageSize page />
</ul>
</div>

<script type="text/javascript">
function goaddpage(){
	myform.method ="post";
	myform.action="addUserPage";
	myform.submit();
}

function deluser(id){
	myform.action="del?id="+id;
	myform.submit();
}


$(function () {
//$('#myTab a:last').tab('show');
$('#myTab a[href="#addpage"]').click(goaddpage);
})

</script>
</body>
</html>