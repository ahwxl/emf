<#macro pagination totalCount pageSize page>  
    <#--声明一个函数transform 转换uri,在新的uri上pager_offset参数  -->  
    <#assign transform = "com.bplow.look.bass.freemarker.TransformURI"?new()>
    <#--总页数-->
    <#assign pageCount= page.totalPage >
    <#assign uri="abc">
    <#--当前页号-->
    <#assign pageIndex=page.pageNo>
    <#assign new_uri="showlist?pageNo=">  
    <#if (pageIndex>pageCount)>  
        <#assign pageIndex=pageCount>  
    </#if>  
    <#if (pageIndex>1)>  
        <li class="active"><a href="${new_uri+1}" title="首页">&lt;&lt;</a></li>
    </#if>  
    <#--如果前面页数过多,显示"..."-->  
    <#if (pageIndex>5)>  
        <#assign prevPages=pageIndex-9>  
        <#if prevPages lt 1>  
            <#assign prevPages=1>  
        </#if>  
        <#assign start=pageIndex-4>  
        <a href="${new_uri+prevPages}" title="向前5页">...</a>  
    <#else>  
        <#assign start=1>  
     </#if>  
    <#-- 显示当前页附近的页-->  
    <#assign end=pageIndex+4>  
    <#if (end>pageCount)>
        <#assign end=pageCount>  
    </#if>  
    <#list start..end as index>  
        <#if pageIndex==index>  
            <li class="disabled"><a href="#">${index}</a></li>
        <#else>  
            <li class="active"><a href="${new_uri+index}">${index}</a></li>
        </#if>  
    </#list>  
    <#--如果后面页数过多,显示"...":-->  
    <#if (end lt pageCount)>  
        <#assign endend=end+5>  
        <#if (end>pageCount)>  
            <#assign end=pageCount>  
        </#if>  
        <li class="active"><a href="${new_uri+end}" title="向后5页">...</a></li>
    </#if>  
    <#-- 显示"下一页":-->  
    <#if (pageIndex lt pageCount)>  
        <li class="active"><a href="${new_uri+pageCount}" title="末页">&gt;&gt;</a></li>
    </#if>  
</#macro>