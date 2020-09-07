<#macro login path button map isRegisterForm>
    <form action=${path} method="post">
        <div class="form-group row">
            <div class="col-sm-6">
                <label for="username" class="col-sm-2 col-form-label">Логин
                    <input type="text" class="form-control
                     <#if (map['usernameError'])??>is-valid</#if>"
                           name="username" placeholder="Логин" value="<#if user??>${user.username}</#if>" required>
                    <#if map['usernameError']??>
                        <div class="invalid-feedback">
                            ${map['usernameError']}
                        </div>
                    </#if>
                </label>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6">
                <label for="password" class="col-sm-2 col-form-label">Пароль
                    <input type="password" class="form-control <#if map['passwordError']??>is-valid</#if>"
                           name="password" placeholder="Пароль" required>
                    <#if map['passwordError']??>
                        <div class="invalid-feedback">
                            ${map['passwordError']}
                        </div>
                    </#if>
                </label>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <div class="col-sm-6">
                    <label for="password2" class="col-sm-2 col-form-label">Пароль
                        <input type="password" class="form-control ${(map['password2Error']??)?string('is-valid','')}"
                               name="password2" placeholder="Повторите пароль" required>
                        <#if map['password2Error']??>
                            <div class="invalid-feedback">
                                ${map['password2Error']}
                            </div>
                        </#if>
                    </label>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-6">
                    <label for="email" class="col-sm-2 col-form-label">Почта
                        <input type="email" class="form-control <#if map['emailError']??>is-valid</#if>"
                               name="email" placeholder="example@some.com"
                               value="<#if user??>${user.mail}</#if>" required>
                        <#if (map['mailError'])??>
                            <div class="invalid-feedback">
                                ${map['mailError']}
                            </div>
                        </#if>
                    </label>
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">${button}</button>
        </div>
    </form>
</#macro>
<#macro  logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Выйти</button>
    </form>
</#macro>
