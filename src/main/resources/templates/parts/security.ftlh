<#assign
know= Session.SPRING_SECURITY_CONTEXT??
>
<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    isUnknow = false
    >
<#else>
    <#assign
    name = "unknow"
    isAdmin = false
    isUnknow = true
    >
</#if>
