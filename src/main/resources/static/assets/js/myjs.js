function login_out() {
    swal({
        title: '你确定?',
        text: "你正在要退出系统!",
        type: 'warning',
        buttons: {
            confirm: {
                text: '是的，退出!',
                className: 'btn btn-success',
                className: 'btn btn-secondary'
            },
            cancel: {
                visible: true,
                text: '不，取消',
                className: 'btn btn-primary'
            }
        }
    }).then((Delete) => {
        if(Delete) {
            window.location.href = "/login_out"
        }
    }
)
}

function user_prjects(userId) {
    $.ajax({
        url: "/user_prjects?userId=" + userId,
        type: "get",
        datatype: "json",
        success: function (data) {
            var parentUL = document.getElementById("projectsul");
            for (var i = 0; i < data.length; i++) {
                //创建二级li
                var li = document.createElement("li");
                parentUL.appendChild(li);
                var a = document.createElement("a");
                a.href = '/projects_view?projectId=' + data[i].id + '&userId=' + userId;
                a.innerHTML = data[i].name;
                li.appendChild(a);
            }
        }
    })
}

function projects_package(projectId, userId) {
    $.ajax({
        url: "/projectsPackages?projectId=" + projectId,
        type: "get",
        datatype: "json",
        success: function (data) {
            var parentUL = document.getElementById("packageul");
            for (var i = 0; i < data.length; i++) {
                //创建一级目录
                var li = document.createElement("li");
                li.className = 'nav-item';
                parentUL.appendChild(li);
                //创建超链接
                var a = document.createElement("a");
                a.setAttribute('data-toggle', 'collapse');
                a.href = "#package" + i;
                li.appendChild(a);
                //创建i
                var i1 = document.createElement("i");
                i1.className = 'fas fa-file-code';
                a.appendChild(i1);
                //创建P
                var p = document.createElement("p");
                p.innerHTML = data[i].packageName;
                a.appendChild(p);
                //创建span
                var span = document.createElement("span");
                span.className = 'caret';
                a.appendChild(span);
                //创建div
                var div = document.createElement("div");
                div.className = 'collapse';
                div.id = "package" + i;
                li.appendChild(div);
                //创建ul
                var ul = document.createElement("ul");
                ul.className = 'nav nav-collapse';
                div.appendChild(ul);
                for (var j = 0; j < data[i].projectsPackageList.length; j++) {
                    if (data[i].projectsPackageList[j].documentName != null) {
                        //创建二级li
                        var li1 = document.createElement("li");
                        ul.append(li1);
                        var a1 = document.createElement("a");
                        a1.href = '/document?userId=' + userId + '&projectId=' + projectId + '&documentName=' + data[i].projectsPackageList[j].documentName;
                        li1.appendChild(a1);
                        //创建span
                        var span1 = document.createElement("span");
                        span1.className = "sub-item";
                        span1.innerHTML = data[i].projectsPackageList[j].documentName;
                        a1.appendChild(span1);
                    }
                }
            }
        }
    })
}

function package_list(projectId) {
    $.ajax({
        url: '/projectsPackages?projectId=' + projectId,
        type: "get",
        dataType: "json",
        success: function (data) {
            var packagennameul = document.getElementById("uploadPackageName");
            for (var i = 0; i < data.length; i++) {
                var opts = document.createElement("option");
                opts.value = data[i].packageName;
                opts.innerHTML = data[i].packageName;
                packagennameul.appendChild(opts);
            }
        }
    });
}

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

function addMessageToDo(list, divNumber, divTitle, div) {
    $(divNumber).html("");
    $(divTitle).html("");
    if (list.length != 0) {
        var messageNumber = '';
        messageNumber += '<span class="notification" id="">' + list.length + '</span>'
        $(divNumber).html(messageNumber);
        var messageTitle = '有'+list.length+'件事务需要处理';
        $(divTitle).html(messageTitle);
    }else {
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
            '                   <button class="btn btn-secondary col-md-4 ml-auto mr-auto">同意</button>' +
            '                   <button class="btn btn-primary col-md-4 ml-auto mr-auto">拒绝</button>' +
            '               </div>' +
            '           </a>'
    }
    $(div).html(messagelist);
}

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
        messagelist += '<a href="#">\n' +
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

