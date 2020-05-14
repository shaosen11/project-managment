$(function () {
    if (userId != "") {
        user_prjects(userId);
    }
})

//用户项目列表
function user_prjects(userId) {
    $.ajax({
        url: "/user_prjects",
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

