function functionPageStart(url,projectId,functionStatus,functionPage,divName,pagecolor) {//分页函数
    $.ajax({ //去后台查询第一页数据
        type : "get",
        url : url,
        dataType : "json",
        data : {
            page : '1',
            projectId : projectId,
            functionStatus :functionStatus,
        }, //参数：当前页为1
        success : function(data) {
            functionAppendHtml(data.list,divName,pagecolor)//处理第一页的数据
            var options = {//根据后台返回的分页相关信息，设置插件参数
                bootstrapMajorVersion : 3, //如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
                currentPage : data.page, //当前页数
                totalPages : data.totalPage, //总页数
                numberOfPages : data.pageSize,//每页记录数
                itemTexts : function(type, page, current) {//设置分页按钮显示字体样式
                    switch (type) {
                        case "first":
                            return "首";
                        case "prev":
                            return "<";
                        case "next":
                            return ">";
                        case "last":
                            return "末";
                        case "page":
                            return page;
                    }
                },
                onPageClicked : function(event, originalEvent, type, page) {//分页按钮点击事件
                    $.ajax({//根据page去后台加载数据
                        url : url,
                        type : "get",
                        dataType : "json",
                        data : {
                            "page" : page,
                            projectId : projectId,
                            functionStatus :functionStatus,
                        },
                        success : function(data) {
                            functionAppendHtml(data.list,divName,pagecolor);//处理数据
                        }
                    });
                }
            };
            functionPage.bootstrapPaginator(options);//设置分页
        }
    });
}

function functionPageStart2(url,projectId,userId,functionStatus,functionPage,divName,pagecolor) {//分页函数
    $.ajax({ //去后台查询第一页数据
        type : "get",
        url : url,
        dataType : "json",
        data : {
            page : '1',
            projectId : projectId,
            userId: userId,
            functionStatus :functionStatus,
        }, //参数：当前页为1
        success : function(data) {
            functionAppendHtml(data.list,divName,pagecolor)//处理第一页的数据
            var options = {//根据后台返回的分页相关信息，设置插件参数
                bootstrapMajorVersion : 3, //如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
                currentPage : data.page, //当前页数
                totalPages : data.totalPage, //总页数
                numberOfPages : data.pageSize,//每页记录数
                itemTexts : function(type, page, current) {//设置分页按钮显示字体样式
                    switch (type) {
                        case "first":
                            return "首";
                        case "prev":
                            return "<";
                        case "next":
                            return ">";
                        case "last":
                            return "末";
                        case "page":
                            return page;
                    }
                },
                onPageClicked : function(event, originalEvent, type, page) {//分页按钮点击事件
                    $.ajax({//根据page去后台加载数据
                        url : url,
                        type : "get",
                        dataType : "json",
                        data : {
                            "page" : page,
                            projectId : projectId,
                            userId: userId,
                            functionStatus :functionStatus,
                        },
                        success : function(data) {
                            functionAppendHtml(data.list,divName,pagecolor);//处理数据
                        }
                    });
                }
            };
            functionPage.bootstrapPaginator(options);//设置分页
        }
    });
}

function functionAppendHtml(list,divName,pagecolor) {//此函数用于处理后台返回的数据，根据自己需求来实现页面拼接
    var tableShow = '';
    for (var i = 0; i < list.length; i++) {
        tableShow +=
            '<div class="row" style="background-color: white;margin-top: 10px" onclick="projectFunctionDetail(' + list[i].id + ')">\n'+
            '  <div style="background-color:'+ pagecolor +';width: 5px;""></div>\n' +
            '<div class="col-md-11" style="margin-top: 8px;margin-bottom: 8px">\n' +
            '         <h3>' + list[i].functionName + '</h3>\n' +
            '         <span style="color: darkgray">by</span>&nbsp;\n' +
            '         <span style="color: darkgray">'+ list[i].publishUserName + '</span>\n'+
            '         <br>\n' +
            '         <span style="color: darkgray">todo</span>&nbsp;\n' +
            '         <span style="color: darkgray">'+ list[i].realizeUserName + '</span>\n'+
            '     </div>'+
            ' </div>'+
            '</div>'
    }
    divName.html(tableShow)
}
