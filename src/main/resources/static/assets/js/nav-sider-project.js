var userId;
var projectId;

function initJsUserIdAndProjectId(userId, projectId) {
    this.userId = userId;
    this.projectId = projectId;
}

//项目包结构
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

//项目包列表
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


//检查包名是否存在
var packageFlag = false;
function checkPackage() {
    var projectId = $("#newPackageProjectId").val();
    var packageName = $("#packageName").val();
    var packageNamecss = $("#packageName");
    $.ajax({
        type: "get",
        url: "projectsPackage",
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

$(function () {
    projects_package(projectId, userId);
    package_list(projectId);
    $("#packageName").keyup(function () {
        setTimeout('checkPackage()', 1000);
    })
})



