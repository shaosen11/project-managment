$(function () {
    console.log(projectId);
    projects_package(projectId);
    package_list(projectId);
    $("#packageName").keyup(function () {
        setTimeout('checkPackage()', 1000);
    });
    if (userId != "") {
        projectMessageCount();
    }
});


//项目包结构
function projects_package(projectId) {
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
                i1.className = 'fas fa-folder';
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
                        a1.href = '/document/' + projectId + '/' + data[i].projectsPackageList[j].documentName;
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

//项目包列表
function package_list(projectId) {
    $.ajax({
        url: '/projectsPackages?projectId=' + projectId,
        type: "get",
        dataType: "json",
        success: function (data) {
            var packagennameul = document.getElementById("uploadPackageName");
            // var packagennameul2 = document.getElementById("packageName1");
            for (var i = 0; i < data.length; i++) {
                var opts = document.createElement("option");
                opts.value = data[i].packageName;
                opts.innerHTML = data[i].packageName;
                packagennameul.appendChild(opts);
                // packagennameul2.appendChild(opts);
            }
            var packagennameul2 = document.getElementById("packageName1");
            if (packagennameul2 != null) {
                for (var i = 0; i < data.length; i++) {
                    var opts = document.createElement("option");
                    opts.value = data[i].packageName;
                    opts.innerHTML = data[i].packageName;
                    packagennameul2.appendChild(opts);
                }
            }
        }
    });
}

//检查包名是否存在
var packageFlag = false;

function checkPackage() {
    var projectId = $("#newPackageProjectId").val();
    var packageName = $("#packageName").val();
    var packageNamecss = $("#packageName");
    $.ajax({
        type: "get",
        url: "/projectsPackage",
        data: {
            "projectId": projectId,
            "packageName": packageName
        },
        success: function (json) {
            if (!json) {
                packageNamecss.css('borderColor', 'red');
                packageFlag = false;
            } else {
                packageNamecss.css('borderColor', '');
                packageFlag = true;
            }
        }
    })
}

//检查包名已存在
function submitPackageCheck() {
    if (!packageFlag) {
        swal("包名已存在，换个名字试试", {
            buttons: false,
            timer: 1500,
        });
        return false;
    }
}

//加载项目个人消息和待办数量
function projectMessageCount() {
    $.ajax({
        url: "/projectMessageCount",
        type: "get",
        datatype: "json",
        data: {
            projectId: projectId,
            userId: userId
        },
        success: function (data) {
            $("#projectMessageCount").text(data)
        }
    })
}

function initNavSiderProjectJs(user) {
    $("#newPackageUserId").val(user.id);
}

$(function () {
    if (userId != "") {
        $("#projectSideBarA1").attr("href", '/projectmessage?projectId=' + projectId + '&userId=' + userId);
    }
    $("#projectSideBarA2").attr("href", '/projects_view/' + projectId);
    $("#projectSideBarA3").attr("href", '/projects_plan_view/' + projectId);
    $("#projectSideBarA4").attr("href", '/project_function_view/' + projectId)
    $("#projectSideBarA5").attr("href", '/project_user_view/' + projectId)
    // $("#projectSideBarA6").attr("href", '/project_user_cooperation_view?projectId=' + projectId)
});

//判断用户是否有权限标记
var projectAdminFlag;

//判断用户是否登录,是否项目人员,是否有权限
function judgeProjectAdmin(arguments) {
    $.ajax({
        type: "Get",
        url: "/projectUserDuty",
        data: {
            projectId: projectId,
            projectFunctionId: arguments,
        },
        dataType: "json",
        success: function (data) {
            if (data != 0) {
                projectAdminFlag = true;
            } else {
                projectAdminFlag = false;
            }
        }
    });
}

//判断用户是否有权限标记
var projectUserFlag;

//判断用户是否登录,是否项目人员
function judgeProjectUser() {
    $.ajax({
        type: "Get",
        url: "/projectUser",
        data: {
            projectId: projectId,
        },
        dataType: "json",
        success: function (data) {
            if (data != null) {
                projectUserFlag = true;
            } else {
                projectUserFlag = false;
            }
        },
    });
}


//检查用户是否登录，是否有管理员权限，都通过之后执行函数
function checkLoginAndPowerAndDoFunction(doFunction) {
    //检查是否登录
    if (userId != "") {
        //检查是否项目人员
        judgeProjectUser();
        if (projectUserFlag) {
            //检查是否项目管理员或者功能点发布者或者功能点实现者
            judgeProjectAdmin(arguments[1]);
            if (projectAdminFlag) {
                doFunction(arguments);
            } else {
                noProjectAdminAlert();
            }
        } else {
            noProjectUserAlert();
        }
    } else {
        noLoginALert()
    }
}

//检查用户是否登录，是否有项目人员，都通过之后执行函数
function checkLoginAndProjectUserAndDoFunction(doFunction) {
    //检查是否登录
    if (userId != "") {
        judgeProjectUser();
        if (projectUserFlag) {
            doFunction(arguments)
        } else {
            noProjectUserAlert();
        }
    } else {
        noLoginALert()
    }
}


//上传文件弹出框
function uploadFileModel() {
    checkLoginAndProjectUserAndDoFunction(uploadFileDo)
}
function uploadFileDo() {
    $("#uploadFileModel").modal('show');
}

//新建包弹出框
function addPackageModel() {
    checkLoginAndProjectUserAndDoFunction(addPackageDo)
}
function addPackageDo() {
    $("#addPackageModel").modal('show');
}

//项目消息跳转
function projectMessageView() {
    checkLoginAndProjectUserAndDoFunction(projectMessageViewDo, projectId, userId);
}

function projectMessageViewDo(arguments) {
    location.href = '/projectmessage/' + arguments[1] + '/' + arguments[2]
}

//跳转合作伙伴
function projectUserCooperationView() {
    checkLoginAndProjectUserAndDoFunction(projectUserCooperationViewDo)
}
function projectUserCooperationViewDo() {
    location.href = '/project_user_cooperation_view/' + projectId
}