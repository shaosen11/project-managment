function initNavJs(user) {
    if (user.photo != "") {
        $("#topBarUserImg1").attr("src", '/files/photo/' + user.photo);
        $("#topBarUserImg2").attr("src", '/files/photo/' + user.photo);
    } else {
        $("#topBarUserImg1").attr("src", '/files/photo/profile.jpg');
        $("#topBarUserImg2").attr("src", '/files/photo/profile.jpg');
    }
    $("#topBarUsername").html(user.username);
    $("#topBarEmail").html(user.email);
    $("#topBarA1").attr("href", '/my_projects/' + user.id);
    $("#topBarA2").attr("href", '/my_projects_store/' + user.id);
}

//不是管理员提醒
function noProjectAdminAlert() {
    swal({
        icon: "warning",
        text: "你不是管理员，没有权限！",
        type: "warning",
        buttons: false,
        timer: 1500,
    })
}

//不是项目人员提醒
function noProjectUserAlert() {
    swal({
        icon: "warning",
        text: "你不是项目人员，没有权限！",
        type: "warning",
        buttons: false,
        timer: 1500,
    })
}

//没有登录提醒
function noLoginALert() {
    swal({
        icon: "warning",
        text: "你未登录，请点击右上角登录！",
        type: "warning",
        buttons: false,
        timer: 2000,
    })
}

//检查用户是否登录
function checkLoginAndDoFunction(doFunction) {
    //检查是否登录
    if (userId != "") {
        doFunction(arguments);
    } else {
        noLoginALert()
    }
}

//跳转新建项目页面
function newProjectView() {
    checkLoginAndDoFunction(newProjectViewDo)
}
function newProjectViewDo() {
    location.href = "/newprojectview"
}