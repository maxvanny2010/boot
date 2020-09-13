<div class="form-row">
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseMessage"
       role="button" aria-expanded="false" aria-controls="collapseExample">
        Редактор сообщений
    </a>
</div>
<div class="collapse <#if message??>show</#if>" id="collapseMessage">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>
                    <input class="form-control <#if (map['textError'])??>is-invalid</#if>"
                           style="width: 400px"
                           value="<#if message??>${message.text}</#if>" type="text" name="text"
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
                         400px" type="text" name="tag" value="<#if message??>${message.tag}</#if>"
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
                    <label class="custom-file-label" for="customFile">Загрузите фото</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="<#if (message.id)??>${message.id}</#if>">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </form>
    </div>
</div>
