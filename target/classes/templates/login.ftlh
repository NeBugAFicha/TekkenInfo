<#import "parts/common.ftlh" as c>
<@c.page>
<form action="/login" method="post">
    <div ><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Войти"/></div>
</form>
<a href="/registration">Add new User</a>
</@c.page>