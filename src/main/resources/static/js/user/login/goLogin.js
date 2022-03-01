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

    createRequest: () => {
        const userId = document.getElementById("userId").value
        const password = document.getElementById("currentPassword").value
        return {
            userId: userId,
            password: password
        }
    },

    onclickGoJoinPage: () => {
        location.href = ""

    },

    onclickSearchIdPage: () => {
        alert("id search check")
    },

    onclickSearchPasswordPage: () => {
        alert("password search check")
    },

    onclickLogin: () => {
        const request = goLogin.createRequest()

        const successHandler = (data) => {
            alert("로그인 성공")
            location.href = "/"
        }

        fetch("/users/login", {
            method: "POST",
            headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                    'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
            .catch((error) => alert(error))
    }




}

goLogin.init()