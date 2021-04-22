<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Fighting Room</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>

            <li class="nav-item">
                <a class="nav-link" href="/main">Characters</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user">Users</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/profile">My profile</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/guides">Guides</a>
            </li>

        </ul>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-primary" type="submit">Выйти</button>
            </form>
    </div>
</nav>