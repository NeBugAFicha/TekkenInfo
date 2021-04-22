<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Guide
    </a>
    <div class="collapse <#if newGuide??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}" <#if newGuide??>value="${newGuide.title}"</#if> name="title" placeholder="Название гайда" />
                    <#if titleError??>
                        <div class="invalid-feedback">
                            ${titleError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control ${(bodyError??)?string('is-invalid', '')}"  <#if newGuide??>value="${newGuide.body}"</#if> name="body" placeholder="Чему бы вы хотели научить..." />
                    <#if bodyError??>
                        <div class="invalid-feedback">
                            ${bodyError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Подтвердить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
        <#list guides as guide>
            <div class="card my-3">
                <div class="m-2">
                    <span>${guide.title}</span></br>
                    <span>${guide.body}</span></br>
                    <span>${guide.author.username}</span></br>
                    <#if name=guide.author.username>
                        <a href="deleteGuide/${guide.id}">Delete</a>
                        <a href="updateGuide/${guide.id}">Update</a></br>
                    </#if>
                </div>
            </div>
        <#else>
        </#list>
    </div>
</@c.page>