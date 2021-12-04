const boardUpdate = {

    init: () => {
        boardUpdate.getDetail()
        smartEditor.init()
        boardUpdate.bind()
    },

    bind: () => {
        document.getElementById('previewBtn').addEventListener('click', boardUpdate.preview)
        document.getElementById('updateBtn').addEventListener('click', boardUpdate.update)
    },

    getDetail: () => {
        const boardId = document.getElementById('boardId').value
        const successHandler = (data) => {
            document.getElementById('subject').value = data.subject
            $('#content').summernote('code', data.content)
        }

        fetch('/boards/detail/'+ boardId, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    update: () => {
        const request = boardUpdate.createUpdateRequest()
        const successHandler = (data) => {
            alert("게시글 수정 완료")
            location.href = "/admin/board/listPage"
        }
        
        fetch("/boards/update", {
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
    },

    preview: () => {
        const previewArea = document.getElementById('previewArea')
        previewArea.innerHTML = document.getElementById('content').value
    },

    createUpdateRequest: () => {
        const content = document.getElementById('content').value
        const subject = document.getElementById('subject').value
        const boardId = document.getElementById('boardId').value
        const request = {content, subject, boardId}

        return request
    }
}

boardUpdate.init()