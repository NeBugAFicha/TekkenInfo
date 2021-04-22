<#import "parts/common.ftlh" as c>
<@c.page>
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
</@c.page>