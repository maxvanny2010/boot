<#import "parts/common.ftlh" as c>
<@c.page>
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <form action='/login' method="post">
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="username" placeholder="Логин" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Пароль </label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Пароль" required>
            </div>
        </div>
        <div class="form-group row">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Войти</button>
        </div>
    </form>
    <span>login: 'admin' pass: '1' </span>
</@c.page>
