$(function () {
    if (userId != "") {
        user_projects(userId);
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

function initNavSiderJs(user) {
    if (user.photo != "") {
        $("#sideBarUserImg1").attr("src", '/files/photo/' + user.photo);
    }else {
        $("#sideBarUserImg1").attr("src", '/files/photo/profile.jpg');
    }
    $("#sideBarUsername").html(user.username);
    $("#sideBarA").attr("href", '/projects_index/' + user.id);

    $("#sideBarA1").attr("href", '/my_projects/' + user.id);
    $("#sideBarA2").attr("href", '/my_projects_store/' + user.id);
}



