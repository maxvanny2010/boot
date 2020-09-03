<#import "parts/common.ftlh" as c>
<#import "parts/link.ftlh" as l>
<@c.page>
    <@l.link '/main' 'Главная'/>
    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Роль</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as u>
            <tr>
                <td>${u.username}</td>
                <td><#list u.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${u.id}">edit</a></td>
            </tr>
        <#else>Список пустой
        </#list>
        </tbody>
    </table>

</@c.page>
