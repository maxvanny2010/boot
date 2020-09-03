<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user">UserList</a></span>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <label> <input type="text" name="text" placeholder="Введите сообщение"> </label>
            <label> <input type="text" name="tag" placeholder="Тэг"> </label>
            <input type="file" name="file">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="get" action="/main">
        <label><input type="text" name="filter" value=${filter!""}></label>
        <button type="submit">Выбрать</button>
    </form>
    <table>
        <tr>
            <th>id</th>
            <th>message</th>
            <th>tag</th>
            <th>author</th>
        </tr>
        <#list messages as message>
            <tr style="text-align: center">
                <TD><b>${message.id}</b></TD>
                <TD><span>${message.text}</span></TD>
                <TD><i>${message.tag}</i></TD>
                <TD><strong>${message.authorName}</strong></TD>
                <TD>
                    <div>
                        <#if message.filename??>
                            <img src="/img/${message.filename}" alt="pic">
                        </#if>
                    </div>
                </TD>
            </tr>
        <#else>
            No message
        </#list>
    </table>
</@c.page>
