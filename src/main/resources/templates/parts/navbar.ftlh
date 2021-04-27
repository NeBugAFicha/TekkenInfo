<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Fighting Room</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Хоум</a>

            <li class="nav-item">
                <a class="nav-link" href="/main">Персонажи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user">Пользователя</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/profile">Мой профиль</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/guides">Гайды</a>
            </li>

        </ul>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-primary" type="submit">Выйти</button>
            </form>
    </div>
</nav>