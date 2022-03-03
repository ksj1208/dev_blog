const boardWrite = {

    init: () => {
        toastEditor.createFrame()
        boardWrite.bind()
    },

    bind: () => {
        document.getElementById('saveBtn').addEventListener('click', boardWrite.save)
    },

    save: () => {
        const request = boardWrite.createSaveRequest()
        const successHandler = (data) => {
            alert("게시글 등록 완료")
            location.href = "/admin/board/listPage"
        }
        
        fetch("/boards/save", {
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

    createSaveRequest: () => {
        const content = toastEditor.getValue()
        const subject = document.getElementById('subject').value
        const categoryId = document.getElementById('category').value
        const request = {content, subject, categoryId}

        return request
    }
}

boardWrite.init()