<#import "parts/common.ftlh" as c>
<#import "parts/characterEdit.ftlh" as chr>
<@c.page>
        <@chr.page/>
        <div>Tekken Characters:</div> </br>
    <form method="post" action="sort">
        <select name="critery">
            <#list sortCriteries as critery>
                <option  value=${critery}>${critery}</option>
            </#list>
        </select>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Сортировать персонажей</button>
    </form>
    <div class="card-columns">
        <#list chars as char>
            <div class="card my-3">
                <#if char.image??>
                    <img src="/img/${char.image}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${char.name}</span>
                    <span>${char.fightingStyle}</span>
                    <i>${char.tierLvl.toString()}</i>
                    <a href="delete/${char.getName()}">Delete</a>
                    <a href="update/${char.getName()}">Update</a></br>
                </div>
            </div>
        <#else>
        </#list>
    </div>
</@c.page>