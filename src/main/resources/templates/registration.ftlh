<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    Add new user
    <div style="color: #ff0000; font-size: smaller">
        ${message!""}
    </div>
    <@l.login "/registration" "Регистрация"/>
</@c.page>
