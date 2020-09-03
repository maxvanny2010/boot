<#import "parts/common.ftlh" as c>
<@c.page>
    List of users
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
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
        <#else>list empty
        </#list>
        </tbody>
    </table>

</@c.page>
