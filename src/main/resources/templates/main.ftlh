<#import "parts/common.ftlh" as c>
<#import "parts/characterEdit.ftlh" as chr>
<@c.page>

     <@chr.page/>
    <div xmlns="http://www.w3.org/1999/html">Tekken Characters:</div> </br>
    <form method="get" action="/sort">
        <select name="sortCritery">
            <#list sortCriteries as sortCritery>
                <option value=${sortCritery} <#if selectedSortCritery??&&selectedSortCritery=sortCritery>selected</#if>>${sortCritery}</option>
            </#list>
        </select>
        <button type="submit">Сортировать</button>
    </form>
    <form method="get" action="/filter">
        <select name="searchByCritery">
            <#list searchBy as searchByCritery>
                <option value=${searchByCritery} <#if selectedSearchByCritery??&&selectedSearchByCritery=searchByCritery>selected</#if>>${searchByCritery}</option>
            </#list>
        </select>
        <input type="text" name="filter" placeholder="Поиск бойцов" <#if filter??>value="${filter}"</#if>/>
        <button type="submit">Найти</button>
    </form>
    <a href="/dropAllFilters">Сбросить все фильтры</a>
    <div class="card-columns">
        <#list chars as char>
            <div class="card my-3">
                <#if char.image??>
                    <img src="/img/${char.image}" class="card-img-top" width="100" height="200">
                </#if>
                <div class="m-2">
                    <span>${char.name}</span>
                    <span>${char.fightingStyle}</span>
                    <i>${char.tierLvl.toString()}</i>
                    <a href="delete/${char.getName()}">Delete</a>
                    <a href="update/${char.getName()}">Update</a></br>
                    <span>${char.getCharMakerName()}</span>
                </div>
            </div>
        <#else>
        </#list>
    </div>
</@c.page>