<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <#if isCurrentUser>
        <#include "parts/messageAdd.ftlh"/>
    </#if>
    <#include "parts/messageList.ftlh"/>
</@c.page>
