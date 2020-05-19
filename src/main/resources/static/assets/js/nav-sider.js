$(function () {
    if (userId != "") {
        user_projects(userId);
        messageCount();
    }
})

//用户项目列表
function user_projects(userId) {
    $.ajax({
        url: "/user_projects",
        type: "get",
        data: {
            userId: userId
        },
        datatype: "json",
        success: function (data) {
            var parentUL = document.getElementById("projectsul");
            for (var i = 0; i < data.length; i++) {
                //创建二级li
                var li = document.createElement("li");
                parentUL.appendChild(li);
                var a = document.createElement("a");
                a.href = '/projects_view?projectId=' + data[i].id;
                a.innerHTML = data[i].name;
                li.appendChild(a);
            }
        }
    })
}

//加载项目个人消息和待办数量
function messageCount() {
    $.ajax({
        url: "/messageCount",
        type: "get",
        datatype: "json",
        data: {
            userId: userId
        },
        success: function (json) {
            $("#messageCount").text(json)
        }
    })
}

function initNavSiderJs(user) {
    if (user.photo != "") {
        $("#sideBarUserImg1").attr("src", '/files/photo/' + user.photo);
    } else {
        $("#sideBarUserImg1").attr("src", '/files/photo/profile.jpg');
    }
    $("#sideBarUsername").html(user.username);
    $("#sideBarA").attr("href", '/projects_index/' + user.id);

}

//跳转修改信息页面userprofile
function userprofileView() {
    checkLoginAndAndDoFunction(userprofileViewDo)
}

function userprofileViewDo() {
    location.href = "/userprofile"
}

//跳转我的项目my_projects
function myProjectView() {
    checkLoginAndAndDoFunction(myProjectViewDo, userId)
}

function myProjectViewDo(arguments) {
    location.href = "/my_projects/" + arguments[1]
}

//跳转我的项目my_projects_store
function myProjectStoreView() {
    checkLoginAndAndDoFunction(myProjectStoreViewDo, userId)
}
function myProjectStoreViewDo(arguments) {
    location.href = "/my_projects_store/" + arguments[1]
}

//跳转个人消息
function messageView() {
    checkLoginAndAndDoFunction(messageViewDo)
}
function messageViewDo() {
    location.href = "/message_view"
}