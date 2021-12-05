const login = {
    init: () => {
        login.bind()
    },

    bind: () => {
        document.getElementById("goLogin").addEventListener('click', login.onclickGoLogin);
        document.getElementById("goSignup").addEventListener('click', login.onclickGoSignup)
    },

    onclickGoLogin: () => {

    },
    onclickGoSignup: () => {
      location.href = "/user/login/signup"
    },
}