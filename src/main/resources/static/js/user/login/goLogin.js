const goLogin = {
    init: () => {
        goLogin.bind()
    },

    bind: () => {
        document.getElementById("goJoin").addEventListener('click', goLogin.onclickGoJoinPage)
        document.getElementById("searchId").addEventListener('click', goLogin.onclickSearchIdPage)
        document.getElementById("searchPassword").addEventListener('click', goLogin.onclickSearchPasswordPage)
        document.getElementById("loginBtn").addEventListener('click', goLogin.onclickLogin)
    },

    onclickGoJoinPage: () => {
        location.href = "/users/signup"

    },

    onclickSearchIdPage: () => {
        alert("id search check")
    },

    onclickSearchPasswordPage: () => {
        alert("password search check")
    },

    onclickLogin: () => {

    }


}

goLogin.init()