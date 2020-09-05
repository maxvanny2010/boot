<#import "parts/common.ftlh" as c>
<@c.page>
    <h5>${username}</h5>
    <div style="color: green; font-size: smaller">
        ${message!}
    </div>
    <form method="post">
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Пароль" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Почта</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" name="email" placeholder="example@some.com" value="${email!''}"
                       required>
            </div>
        </div>
        <div class="form-group row">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
    </form>
</@c.page>
