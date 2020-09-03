<#macro login path name>
    <form action=${path} method="post">
        <table>
            <tr>
                <td>User Name</td>
                <td><label> <input type="text" name="username" required></label>
                <td>
            </tr>
            <tr>
                <td>Password</td>
                <td><label><input type="password" name="password" required></label></td>
            </tr>
            <tr>
                <td><input type="hidden" name="_csrf" value="${_csrf.token}" required></td>
                <td><input type="submit" value=${name}></td>
            </tr>
        </table>
    </form>

</#macro>
<#macro  logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Выйти</button>
    </form>
</#macro>
