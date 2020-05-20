//全局加载导航栏消和待办
$(function () {
    if (userId != "") {
        //全局加载导航栏消和待办
        $(function () {
            my_message(userId);
            my_message_need_to_do(userId);
        })
    }
})

//请求数据库
function nav_message(userId, needToDo, divNumber, divTitleName, messageDiv) {
    $.ajax({
        type: "post",
        url: "/myMessage",
        dataType: "json",
        data: {
            userId: userId,
            needToDo: needToDo,
        },
        success: function (list) {
            if (needToDo == 0) {
                addMessage(list, divNumber, messageDiv);
            } else if (needToDo == 1) {
                addMessageToDo(list, divNumber, divTitleName, messageDiv)
            }
        }
    })
}

//数据库返回数据，添加待办消息
function addMessageToDo(list, divNumber, divTitle, div) {
    $(divNumber).html("");
    $(divTitle).html("");
    if (list.length != 0) {
        var messageNumber = '';
        messageNumber += '<span class="notification" id="">' + list.length + '</span>'
        $(divNumber).html(messageNumber);
        var messageTitle = '有' + list.length + '件事务需要处理';
        $(divTitle).html(messageTitle);
    } else {
        var messageTitle = '你没有事务需要出处理';
        $(divTitle).html(messageTitle);
    }
    $(div).html("");
    var messagelist = '';
    for (var i = 0; i < list.length && i < 4; i++) {
        messagelist += '<a class="col-md-12" href="#">\n' +
            '               <div class="row">' +
            '                   <div class="col-md-3">\n' +
            '                       <div class="notif-icon ' + list[i].messageType.background + ' col-auto">' +
            '                           <i class="fa ' + list[i].messageType.icon + '"></i>' +
            '                       </div>\n' +
            '                   </div>\n' +
            '                   <div class="col-md-9">\n' +
            '                       <div class="notif-content">\n' +
            '                           <span class="block">' + list[i].message + '</span>\n' +
            '                           <span class="time">' + list[i].time + '</span>\n' +
            '                       </div>\n' +
            '                   </div>\n' +
            '                   <button class="btn btn-secondary col-md-4 ml-auto mr-auto" onclick="agreeNeedToDo(' + list[i].id + ')">同意</button>' +
            '                   <button class="btn btn-primary col-md-4 ml-auto mr-auto" onclick="doNotAgreeNeedToDo(' + list[i].id + ')">拒绝</button>' +
            '               </div>' +
            '           </a>'
    }
    $(div).html(messagelist);
}

//数据库返回数据，添加消息
function addMessage(list, divNumber, div) {
    $(divNumber).html("");
    if (list.length != 0) {
        var messageNumber = '';
        messageNumber += '<span class="notification" id="">' + list.length + '</span>'
        $(divNumber).html(messageNumber);
    }
    $(div).html("");
    var messagelist = '';
    for (var i = 0; i < list.length && i < 4; i++) {
        messagelist += '<a onclick="updateMessageIsRead(' + list[i].id + ')">\n' +
            '              <div class="col-md-3">\n' +
            '                   <div class="notif-icon ' + list[i].messageType.background + ' col-auto">' +
            '                       <i class="fa ' + list[i].messageType.icon + '"></i>' +
            '                   </div>\n' +
            '              </div>\n' +
            '              <div class="col-md-9">\n' +
            '                   <div class="notif-content">\n' +
            '                       <span class="block">' + list[i].message + '</span>\n' +
            '                       <span class="time">' + list[i].time + '</span>\n' +
            '                   </div>\n' +
            '              </div>\n' +
            '           </a>'
    }
    $(div).html(messagelist);
}

//同意待办
function agreeNeedToDo(messageId) {
    $.ajax({
        type: "put",
        url: "/agreeNeedToDo",
        dataType: "json",
        data: {
            messageId: messageId,
        },
        success: function (result) {
            console.log(result);
            if (result) {
                my_message_need_to_do();
                setTimeout(function () {
                    location.href = result.data;
                }, 1500);
            }
        }
    })
}

//拒绝待办
function doNotAgreeNeedToDo(messageId) {
    console.log(messageId);
    $.ajax({
        type: "put",
        url: "/doNotAgreeNeedToDo",
        dataType: "json",
        data: {
            messageId: messageId,
        },
        success: function (result) {
            if (result) {
                console.log(result);
                my_message_need_to_do();
            }
        }
    })
}

//调用请求数据库
function my_message_need_to_do(userId) {
    var divNumber = $('#myMessageNeedToDoNumber');
    var divTitleName = $('#myMessageNeedToDoTitle');
    var divName = $('#myMessageNeedToDo');
    nav_message(userId, 1, divNumber, divTitleName, divName);
}

//调用请求数据库
function my_message(userId) {
    var divName = $('#myMessage');
    var divNumber = $('#myMessageNumber');
    var divTitleName = $('');
    nav_message(userId, 0, divNumber, divTitleName, divName);
}

//标记所有已读
function updateAllMessageIsRead() {
    $.ajax({
        type: "get",
        url: "/updateAllMessageIsReadByUserId",
        dataType: "json",
        data: {
            userId: userId,
        },
        success: function (result) {
            if (result) {
                my_message();
            }
        }
    })
}

//标记单条消息已读
function updateMessageIsRead(messageId) {
    $.ajax({
        type: "get",
        url: "/updateMessageIsReadByMessageId",
        dataType: "json",
        data: {
            messageId: messageId,
        },
        success: function (result) {
            if (result.ok) {
                my_message();
            } else {
                swal("系统出现错误！", result.message, {
                    icon: "error",
                    buttons: {
                        confirm: {
                            className: 'btn btn-danger'
                        }
                    },
                });
            }
        }
    })
}


