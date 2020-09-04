<#macro login path name isRegisterForm>
    <form action=${path} method="post">
        <div class="form-group">
            <label for="username">Логин
                <input type="text" class="form-control" name="username" placeholder="Логин" required></label>
        </div>
        <div class="form-group">
            <label for="password">Пароль
                <input type="password" class="form-control" name="password" placeholder="Пароль" required>
            </label>
        </div>
        <#if isRegisterForm>
            <div class="form-group">
                <label for="email">Почта
                    <input type="email" class="form-control" name="email" placeholder="example@some.com" required>
                </label>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">${name}</button>
    </form>
</#macro>
<#macro  logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Выйти</button>
    </form>
</#macro>
