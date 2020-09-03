<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div>Login page</div>
    <@l.login '/login' 'Войти'></@l.login>
    <div><a href="/registration">Регистрация</a></div>
</@c.page>
