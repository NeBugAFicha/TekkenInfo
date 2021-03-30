<#macro page>
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}" name="name" placeholder="Character name" />
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(fightingStyleError??)?string('is-invalid', '')}" name="fightingStyle" placeholder="Fighting Style" />
                <#if fightingStyleError??>
                    <div class="invalid-feedback">
                        ${fightingStyleError}
                    </div>
                </#if>
            </div>
            <div class="form-group" >
                <select name="tierLvl" class="form-control">
                    <#list tierLvls as tierLvl>
                        <option  value=${tierLvl}>${tierLvl}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <input type="file" class="form-control" name="file" />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Подтвердить</button>
            </div>
        </form>
    </div>
</#macro>