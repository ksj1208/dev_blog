const profileDetail = {
    init: () => {
        profileDetail.bind()
    },

    bind: () => {
        document.getElementById('profileListBtn').addEventListener('click', profileDetail.back)
        document.getElementById('profileModifyBtn').addEventListener('click', profileDetail.modify)
    },

    modifyRequest: () => {
        const profileCode = document.getElementById('profileCode').value
        const content = document.getElementById('content').value
        const subject = document.getElementById('subject').value
        const request = {profileCode, content, subject}

        return request
    },

    back: () => {
        location.href = "/profile/listPage"
        //onclick="history.back()"
    },


    modify: () => {
        if(!confirm("프로필을 수정하시겠습니까?"))
            return


        const request = profileDetail.modifyRequest()
        const successHandler = (data) => {
            alert("프로필 수정 완료")
            location.href = "/profile/listPage"
        }


        fetch("/profile/modify", {

            method: "PATCH",
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

profileDetail.init()