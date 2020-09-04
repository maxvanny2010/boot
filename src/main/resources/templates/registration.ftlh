<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as o>
<#import "parts/link.ftlh" as l>
<@c.page>
    <div style="color: #ff0000; font-size: smaller">
        ${message!""}
    </div>
    <@o.login "/registration" "Регистрация" true/>
</@c.page>
