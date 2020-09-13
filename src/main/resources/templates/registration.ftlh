<#import "parts/common.ftlh" as c>
<@c.page>
    <form action='/registration' method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"></label>
            <div class="col-sm-6">
                <div class="g-recaptcha" data-sitekey="6Led6coZAAAAAGdpT0Mn8C6vsgumbkfArwA4KIX7"></div>
                <#if (map['captchaError'])??>
                    <div class="alert alert-${map['messageType']}" role="alert">
                        <strong>${map['captchaError']}</strong>
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-6">
                <input type="text" class="form-control <#if (map['usernameError'])??>is-invalid</#if>"
                       name="username" placeholder="Логин" value="<#if user??>${user.username}</#if>">
                <#if (map['usernameError'])??>
                    <div class="invalid-feedback">
                        ${map['usernameError']}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Пароль </label>
            <div class="col-sm-6">
                <input type="password" class="form-control <#if (map['passwordError'])??>is-invalid</#if>"
                       name="password" placeholder="Пароль">
                <#if (map['passwordError'])??>
                    <div class="invalid-feedback">
                        ${map['passwordError']}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="password2" class="col-sm-2 col-form-label">Пароль </label>
            <div class="col-sm-6">
                <input type="password" class="form-control  <#if (map['password2Error'])??>is-invalid</#if>"
                       name="password2" placeholder="Повторите пароль">
                <#if (map['password2Error'])??>
                    <div class="invalid-feedback">
                        ${map['password2Error']}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Почта</label>
            <div class="col-sm-6">
                <input type="email" class="form-control <#if (map['emailError'])??>is-invalid</#if>"
                       name="email" placeholder="example@some.com"
                       value="<#if user??>${user.email}</#if>">
                <#if (map['mailError'])??>
                    <div class="invalid-feedback">
                        ${map['mailError']}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Регистрация</button>
        </div>
    </form>
</@c.page>
