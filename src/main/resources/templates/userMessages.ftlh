<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h3>${userChannel.username}</h3>
    <#if !isCurrentUser>
        <#if isSubscriber>
            <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">Отписаться</a>
        <#else>
            <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">Подписаться</a>
        </#if>
    </#if>
    <div class="container my-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="car-title">Subscription</div>
                        <h3 class="car-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="car-title">Subscribers</div>
                        <h3 class="car-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser>
        <#include "parts/messageAdd.ftlh"/>
    </#if>
    <#include "parts/messageList.ftlh"/>
</@c.page>
