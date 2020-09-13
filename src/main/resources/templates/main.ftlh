<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline" method="get" action="/main">
                <label><input class="form-control" type="text"
                              placeholder="Поиск по тэгу"
                              name="filter" value=${filter!""}>
                </label>
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
        </div>
    </div>
    <#include "parts/messageAdd.ftlh"/>
    <#include "parts/messageList.ftlh"/>
</@c.page>
