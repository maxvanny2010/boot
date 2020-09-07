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
    <div class="form-row">
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseMessage"
           role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить сообщение
        </a>
    </div>
    <div class="collapse <#if map??>show</#if>" id="collapseMessage">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label>
                        <input class="form-control <#if (map['textError'])??>is-invalid</#if>"
                               style="width: 400px"
                               value="<#if map??>${message.text}</#if>" type="text" name="text"
                               placeholder="Введите сообщение">
                        <#if (map['textError'])??>
                            <div class="invalid-feedback">
                                ${map['textError']}
                            </div>
                        </#if>
                    </label>
                </div>
                <div class="form-group">
                    <label>
                        <input class="form-control  <#if (map['tagError'])??>is-invalid</#if>"
                               style="width:
                         400px" type="text" name="tag"
                               placeholder="Тэг">
                        <#if (map['tagError'])??>
                            <div class="invalid-feedback">
                                ${map['tagError']}
                            </div>
                        </#if>
                    </label>
                </div>
                <div class="form-group" style="width: 400px">
                    <div class="custom-file">
                        <input class="custom-file-input" type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Загрузите Аватарку</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="form-row">
        <div class="card-columns">
            <#list messages as message>
                <div class="card my-3" data-id="${message.id}">
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="card-img-top" alt="pic"/>
                    </#if>
                    <div class="m-2">
                        <span>${message.text}</span><br/>
                        <i>#${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <a class="col align-self-center"
                               href="/user-messages/${message.author.id}">${message.authorName}</a>
                        </div>
                    </div>
                </div>
            <#else>
                No message
            </#list>
        </div>
    </div>
</@c.page>
