<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="en" xmlns:ext="http://www.sencha.com/docs">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工作平台</title>
<meta name="description" content="API Documentation for Ext JS - a cross-browser JavaScript framework for building rich internet applications." />
<link rel="stylesheet" type="text/css" href="resources/js/ext-3.4/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="resources/js/ext-3.4/resources/css/xtheme-gray.css" />
<link rel="stylesheet" type="text/css" href="resources/css/docs.css" />
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="shortcut icon" href="resources/favicon.ico" />
<link rel="icon" href="resources/favicon.ico" />
<style type="text/css"></style>
<!-- GC -->
<script type="text/javascript" src="resources/js/ext-3.4/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="resources/js/ext-3.4/ext-all.js"></script>
<script type="text/javascript" src="resources/js/ext-3.4/ux/ux-all.js"></script>
<script type="text/javascript" src="resources/js/module/portal.js"></script>

<script type="text/javascript" src="resources/js/module/sample-grid.js"></script>
<script type="text/javascript" src="resources/js/module/docs.js"></script>
<script type="text/javascript" src="resources/js/module/tree.js"></script>

<script language="JavaScript" src="resources/Charts/FusionCharts.js"></script>

<script type="text/javascript" src="resources/js/module/miframe.js"></script>

</head>
<body scroll="no" id="docs">
<div id="loading-mask"></div>
<div id="loading">
    <div class="loading-indicator">
        <img src="resources/images/extanim32.gif" width="32" height="32" style="margin-right:8px;" align="absmiddle" />
        Loading&hellip;
    </div>
</div>

<div id="header">
    <!---<img style="margin-left: 5px" src="resources/css/title-ext.png" alt="Ext JS API Documentation" height="49" width="380" />-->
    <div style="float:right; margin-top: 15px;margin-right: 10px;color: #CCC">
        <a href="http://dev.sencha.com/deploy/dev/examples/" style="padding:5px">examples</a> | 
        <a href="http://www.sencha.com/" style="padding:5px">sencha.com</a>
    </div>
</div>

<div id="classes"></div>

<div id="main"></div>

<select id="search-options" style="display:none">
    <option>Starts with</option>
    <option>Ends with</option>
    <option>Any Match</option>
</select>

<div id="chartdiv"></div>
<div id="column3ddiv"></div>
<div id="column4ddiv"></div>
</body>
</html>
