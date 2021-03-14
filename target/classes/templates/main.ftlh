<!DOCTYPE HTML>
<html>
<head>
    <title>Fighting room</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
    <body>
        <form method="post">
            <input type="text" name="name" placeholder="Character Name" />
            <input type="text" name="style" placeholder="Fighting Style" />
            <select name="lvl">
                <#list tierLvls as tierLvl>
                    <option value=${tierLvl}>${tierLvl}</option>
                </#list>
            </select>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add a character</button>
        </form>
        <div>Tekken Characters:</div> </br>
        <div>
            <#list chars as char>
                <span>${char.getName()}</span>
                <strong>${char.getFightingStyle()}</strong>
                <i>${char.getTierLvl().toString()}</i>
                <a href="delete/${char.getName()}">Delete</a>
                <a href="update/${char.getName()}">Update</a></br>
            </#list>
        </div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Sign Out"/>
        </form>
    </body>
</html>