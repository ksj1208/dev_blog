const profileWriter = {
    init: () => {
        profileWriter.bind()
    },

    bind: () => {
        document.getElementById('profileSaveBtn').addEventListener('click', profileWriter.save)

    },

    createSaveRequest: () => {
        const content = document.getElementById('content').value
        const subject = document.getElementById('subject').value
        const request = {content, subject}

        return request
    },

    save: () => {
        const request = profileWriter.createSaveRequest()
        const successHandler = (data) => {
            alert("프로필 등록 완료")
            location.href = "/admin/profile/listPage"
        }

        fetch("/admin/profile/save", {
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

profileWriter.init()