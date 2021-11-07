const profileImageWriter = {
    init: () => {
        profileImageWriter.bind()
    },

    bind: () => {
        document.getElementById('profileSaveBtn').addEventListener('click', profileImageWriter.save)
    },

    createSaveRequest: () => {
        const content = document.getElementById('content').value
        const subject = document.getElementById('subject').value
        const image = document.getElementById('profileImage').value
        const request = {content, subject, image}

        return request
    },

    save: () => {
        const request = profileImageWriter.createSaveRequest()
        const successHandler = (data) => {
            alert("프로필 등록 완료")
            location.href = "/profile/listPage"
        }

        fetch("/profile/imageSave", {
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

profileImageWriter.init()