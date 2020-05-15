function initNavJs(user) {
    if (user.photo != "") {
        $("#topBarUserImg1").attr("src", '/files/photo/' + user.photo);
        $("#topBarUserImg2").attr("src", '/files/photo/' + user.photo);
    }else {
        $("#topBarUserImg1").attr("src", '/files/photo/profile.jpg');
        $("#topBarUserImg2").attr("src", '/files/photo/profile.jpg');
    }
    $("#topBarUsername").html(user.username);
    $("#topBarEmail").html(user.email);
    $("#topBarA1").attr("href", '/my_projects/' + user.id);
    $("#topBarA2").attr("href", '/my_projects_store/' + user.id);
}