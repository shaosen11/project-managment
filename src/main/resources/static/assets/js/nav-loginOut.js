//退出触发器
function login_out() {
    swal({
        title: '你确定?',
        text: "你正在要退出系统!",
        type: 'warning',
        buttons: {
            confirm: {
                text: '是的，退出!',
                className: 'btn btn-success',
                className: 'btn btn-secondary'
            },
            cancel: {
                visible: true,
                text: '不，取消',
                className: 'btn btn-primary'
            }
        }
    }).then((Delete) => {
        if(Delete) {
            window.location.href = "/login_out"
        }
    }
)
}