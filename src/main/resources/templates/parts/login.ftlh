<#macro login path name isRegisterForm>
    <form action=${path} method="post">
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="username" placeholder="Логин" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Пароль" required>
            </div>
            </label>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Почта</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" name="email" placeholder="example@some.com" required>
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">${name}</button>
        </div>
    </form>
</#macro>
<#macro  logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Выйти</button>
    </form>
</#macro>
