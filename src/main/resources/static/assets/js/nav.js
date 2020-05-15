function initNavJs(user) {
    $("#topBarUserImg1").attr("src", '/files/photo/' + user.photo);
    $("#topBarUserImg2").attr("src", '/files/photo/' + user.photo);
    $("#topBarUsername").html(user.username);
    $("#topBarEmail").html(user.email);
    $("#topBarA1").attr("href", '/my_projects/' + user.id);
    $("#topBarA2").attr("href", '/my_projects_store/' + user.id);
}