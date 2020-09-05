<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div style="color: #ff0000; font-size: smaller">
        ${message!}
    </div>
    <@l.login '/login' 'Войти' false/>
</@c.page>
