<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    Редактор моего профиля
    <div class="form-group mt-3">
        <form method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="userName" value="${user.username}" placeholder="Имя Пользователя"></br>
                <#if nameError??>${nameError}</#if>
            </div>
            Role:
            <#list user.getAuthorities() as authority>
             <span>${authority}<#sep>, </span>
            </#list></br>
            <input type="hidden" value="${user}" name="user">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </form>
    </div>
</@c.page>