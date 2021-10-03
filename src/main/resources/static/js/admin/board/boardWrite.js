const boardWrite = {

    init: () => {
        smartEditor.init()
        boardWrite.bind()
    },

    bind: () => {
        document.getElementById('saveBtn').addEventListener('click', boardWrite.save)
        document.getElementById('previewBtn').addEventListener('click', boardWrite.preview)
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

    preview: () => {
        const previewArea = document.getElementById('previewArea')
        previewArea.innerHTML = document.getElementById('content').value
    },

    createSaveRequest: () => {
        const content = document.getElementById('content').value
        const subject = document.getElementById('subject').value
        const request = {content, subject}

        return request
    }
}

boardWrite.init()