<#import "parts/common.ftlh" as c>
<@c.page>
    Добавьте нового пользователя!</br>
    <#if message??>
        ${message}
    </#if>
    <div class="form-group mt-3">
        <form method="post">
            <div class="form-group">
                <label> User Name : <input type="text" class="form-control ${(usernameError??)?string('is-invalid', '')}" name="username"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
                </label>
            </div>
            <div class="form-group">
                <label> Password: <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')} name="password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
                </label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div><input type="submit" class="btn btn-primary" value="Зарегистрировать"/></div>
        </form>
    </div>
</@c.page>