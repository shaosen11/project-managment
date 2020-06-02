$(function () {
    if (userId != "") {
        user_projects(userId);
        messageCount();
        judgeProjectManagementAdmin();
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
                a.href = '/projects_view/' + data[i].id;
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

}

//跳转修改信息页面userprofile
function userprofileView() {
    checkLoginAndDoFunction(userprofileViewDo)
}
function userprofileViewDo() {
    location.href = "/userprofile"
}

//跳转我的项目my_projects
function myProjectView() {
    checkLoginAndDoFunction(myProjectViewDo, userId)
}
function myProjectViewDo(arguments) {
    location.href = "/my_projects/" + arguments[1]
}

//跳转我的项目my_projects_store
function myProjectStoreView() {
    checkLoginAndDoFunction(myProjectStoreViewDo, userId)
}
function myProjectStoreViewDo(arguments) {
    location.href = "/my_projects_store/" + arguments[1]
}

//跳转个人消息
function messageView() {
    checkLoginAndDoFunction(messageViewDo)
}
function messageViewDo() {
    location.href = "/message_view"
}

//不是系统开发者提醒
function noProjectManagementAdminAlert(){
    swal({
        icon: "warning",
        text: "你不是系统开发者，没有权限！",
        type: "warning",
        buttons: false,
        timer: 1500,
    })
}

var projectManagementAdminFlag = false;
//判断用户是否系统开发者提醒
function judgeProjectManagementAdmin() {
    $.ajax({
        type: "Get",
        url: "/projectManagementAdmin",
        success: function (data) {
            if (data != "") {
                projectManagementAdminFlag = true;
            } else {
                projectManagementAdminFlag = false;
            }
        },
    });
}

//检查是否本系统开发管理员
function checkProjectManagmentAdminAndDoFunction(doFunction) {
    //检查是否登录
    if (userId != "") {
        if (projectManagementAdminFlag) {
            doFunction(arguments);
        } else {
            noProjectManagementAdminAlert();
        }
    } else {
        noLoginALert();
    }
}

//跳转管理员user_list
function userListView() {
    checkProjectManagmentAdminAndDoFunction(userListViewDo)
}
function userListViewDo() {
    location.href = "/user_list"
}

//跳转管理员projects_list
function projectsListView() {
    checkProjectManagmentAdminAndDoFunction(projectsListViewDo)
}
function projectsListViewDo() {
    location.href = "/projects_list"
}

//跳转管理员project_user_list
function projectUserListView() {
    checkProjectManagmentAdminAndDoFunction(projectUserListViewDo)
}
function projectUserListViewDo() {
    location.href = "/project_user_list"
}

//跳转管理员project_function_list
function projectFunctionListView() {
    checkProjectManagmentAdminAndDoFunction(projectFunctionListViewDo)
}
function projectFunctionListViewDo() {
    location.href = "/project_function_list"
}



//跳转管理员deleted_user_list
function deletedUserListView() {
    checkProjectManagmentAdminAndDoFunction(deletedUserListViewDo)
}
function deletedUserListViewDo() {
    location.href = "/deleted_user_list"
}

//跳转管理员del_project_list
function delProjectListView() {
    checkProjectManagmentAdminAndDoFunction(delProjectListViewDo)
}
function delProjectListViewDo() {
    location.href = "/del_project_list"
}

//跳转管理员del_project_user_list
function delProjectUserListView() {
    checkProjectManagmentAdminAndDoFunction(delProjectUserListViewDo)
}
function delProjectUserListViewDo() {
    location.href = "/del_project_user_list"
}

//跳转管理员del_project_function_list
function delProjectFunctionListView() {
    checkProjectManagmentAdminAndDoFunction(delProjectFunctionListViewDo)
}
function delProjectFunctionListViewDo() {
    location.href = "/del_project_function_list"
}