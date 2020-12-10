<!DOCTYPE HTML>
<html>
<head>
    <title>Fighting room</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
    <body>
        <form method="post">
            <input type="text" name="name" value="${char.getName()}" />
            <input type="text" name="style" value="${char.getFightingStyle()}" />
            <input type="text" name="lvl" value="${char.getTierLvl().toString()}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Change</button>
        </form>
    </body>
</html>