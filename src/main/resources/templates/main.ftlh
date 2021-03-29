<#import "parts/common.ftlh" as c>
<#import "parts/characterEdit.ftlh" as chr>
<@c.page>
        <@chr.page/>
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
</@c.page>