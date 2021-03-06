const joinMember = {

    //전역변수
    //All: {}
    //all :

    init: () => {
        joinMember.bind()
    },

    bind: () => {
        document.getElementById("userId").addEventListener('onkeyup', joinMember.onkeyupUserId)
        document.getElementById("existBtnUserId").addEventListener('click', joinMember.onclickBtnUserId)
        document.getElementById("nickName").addEventListener('onkeyup', joinMember.onkeyupNickName)
        document.getElementById("existBtnNickName").addEventListener('click', joinMember.onclickBtnNickName)
        document.getElementById("currentPassword").addEventListener('onkeyup', joinMember.onkeyupPassword)
        document.getElementById("recheckPassword").addEventListener('focusout', joinMember.focusoutPassword)
        document.getElementById("joinMember").addEventListener('click', joinMember.save)

    },

    createRequest: () => {
        const userId = document.getElementById("userId").value
        const nickName = document.getElementById("nickName").value
        const password = document.getElementById("currentPassword").value
        const recheckPassword = document.getElementById("recheckPassword").value
        const email = document.getElementById("email").value
        return {
            userId: userId,
            nickName: nickName,
            password: password,
            recheckPassword: recheckPassword,
            email: email
        }

    },

    onkeyupUserId: () => {
        const request = joinMember.createRequest()
        if(joinMember.isValidUserId(request.userId)){
            return true
        }

    },

    onclickBtnUserId: () => {
        const request = joinMember.createRequest()
        if(joinMember.isExistUserId(request.userId)){
            return true
        }
    },

    onkeyupNickName: () => {
        const request = joinMember.createRequest()
        if(joinMember.isValidNickName(request.nickName)){
            return true
        }
    },

    onclickBtnNickName: () => {
      const request = joinMember.createRequest()
        if(joinMember.isExistNickName(request.nickName)){
            return true
        }
    },
    onkeyupPassword: () => {
        const request = joinMember.createRequest()
        if(joinMember.isValidPassword(request.currentPassword)){
            return true
        }
        if(request.recheckPassword != null ){
            if(joinMember.isSamePassword(request.currentPassword, request.recheckPassword)){
                return true
            }
        }

    },

    focusoutPassword: () => {
        const request = joinMember.createRequest()
        if(joinMember.isSamePassword(request.currentPassword, request.recheckPassword)){
            return true
        }
    },


    save: () => {
        const request = joinMember.createRequest()
        //이메일 인증완료여부 체크

        //위에 항목 모두 true인지 확인

        const successHandler = (data) => {
            alert("회원가입완료")
            //회원가입 완료되면 이동하는 페이지
            location.href = "/"

        }

        fetch("/users/join", {
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

    },

    isValidUserId(userId){
        //아이디 유효성 검사
        let reg_userId = /^[a-zA-Z0-9]{4,12}$/;
        if (!reg_userId.test(userId)){
            return false;
        }else {
            return true;
        }
    },
    isExistUserId(userId){
        //아이디 중복검사
        alert("id check");
    },

    isValidPassword(password){
        //패스워드 유효성검사
        let reg_password = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;
        if(!reg_password.test(password)){
            return false;
        }
            return true;


    },

    isSamePassword(currentPassword, recheckPassword){
        //패스워드 일치 검사
        if(currentPassword === recheckPassword){
            return true
        }
            return false

    },

    isValidNickName(nickName){
        //닉네임 유효성 검사
        const reg_nickName = /^[가-힣a-zA-Z0-9]{4,12}$/;
        if(!reg_nickName.test(nickName)){
            return false;
        }
            return true;

    },

    isExistNickName(nickName){
        //닉네임 중복 검사
        alert("nick name check ");
    }
}

joinMember.init()