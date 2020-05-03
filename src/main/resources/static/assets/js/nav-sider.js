var userId;

function initJsUserId(userId) {
    this.userId = userId;
}

//用户项目列表
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

//加载项目列表
$(function () {
    user_prjects(userId);
})