const goLogin ={
    inig: () =>{
        goLogin.bind()
    },

    bind: () =>{
        document.getElementById("goJoin").addEventListener('click', goLogin.onclickGoJoin)
        document.getElementById("recheckPassword").addEventListener('focusout', )
    },

    onclickGoJoin: () =>{
        location.href = "/users/signpup"
        console.log("click!");

        if(sign.isValidUseriId(userId))
            return true

    },

    isValidUseriId(userId){
        //유효성검사
    },
    isExistUserId(userId){
        //중복검사
    }

}

goLogin.init()