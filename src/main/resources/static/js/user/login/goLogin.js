const goLogin = {

    init: () => {
        goLogin.bind()
    },

    bind: () => {
        document.getElementById("goJoin").addEventListener('click', goLogin.onclickGoJoin)

    },

    onclickGoJoin: () => {
        location.href = "/users/signup"
        console.log("click!");
    }
}

goLogin.init()