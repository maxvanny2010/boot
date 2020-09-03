<#import "parts/common.ftlh" as c>
<@c.page>
    <p><a href="/main">к списку</a></p>
    <p> User editor</p>
    <div>
        <form action="/user" method="post">
            <label> <input type="text" name="username" value="${user.username}"> </label>
            <#list roles as role>
                <div>
                    <label>
                        <input type="checkbox" name="${role}"
                                ${user.roles?seq_contains(role)?string("checked","")}>${role}
                    </label>
                </div>
            </#list>
            <input type="hidden" name="userId" value="${user.id}">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Сохранить</button>
        </form>
    </div>

</@c.page>
