const authEmail = {

    init: () => {
        authEmail.bind()
    },

    bind: () => {
        document.getElementById("authEmail").addEventListener('onclick', authEmail.onclickAuth)
    },

    onclickAuth: () => {
        const email = document.getElementById("email").value
        if (authEmail.isValidEmail(email)){
            //인증루트
            return true
        }
    },

    isValidEmail(email){
        //이메일 유효성 검사
        var reg_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if(!reg_email.test(email)){
            return false;
        }else {
            return true;
        }
    }


}

authEmail.init()
