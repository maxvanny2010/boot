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
    <div class="form-row">
        <div class="collapse" id="collapseMessage">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>
                            <input class="form-control" style="width: 400px" type="text" name="text"
                                   placeholder="Введите сообщение">
                        </label>
                    </div>
                    <div class="form-group">
                        <label>
                            <input class="form-control mr-2" style="width: 400px" type="text" name="tag"
                                   placeholder="Тэг">
                        </label>
                    </div>
                    <div class="form-group" style="width: 400px">
                        <div class="custom-file">
                            <input class="custom-file-input" type="file" name="file">
                            <label class="custom-file-label">Загрузите Аватарку</label>
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="form-row">
        <div class="card-columns">
            <#list messages as message>
                <div class="card my-3">
                    <div style="font-size: smaller; color: cadetblue; text-align: center">
                        <strong>Tэг: </strong>${message.tag}
                    </div>
                    <div class="m-2">
                        <span>${message.text}</span>
                    </div>
                    <div class="card-footer text-muted">
                        <#if message.filename??>
                            <img style="width:50px; height:50px" class="card-img-top" src="/img/${message.filename}"
                                 alt="pic">
                        </#if>
                        ${message.authorName}
                    </div>
                </div>
            <#else>
                No message
            </#list>
        </div>
    </div>
</@c.page>
