<#macro page>
    <#include "security.ftlh">
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Character
    </a>
    <div class="collapse <#if char??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}" <#if char??>value="${char.name}"</#if> name="name" placeholder="Character name" />
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control ${(fightingStyleError??)?string('is-invalid', '')}"  <#if char??>value="${char.fightingStyle}"</#if> name="fightingStyle" placeholder="Fighting Style" />
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
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="charMakerName" value="${name}" />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Подтвердить</button>
                </div>
            </form>
        </div>
    </div>
</#macro>