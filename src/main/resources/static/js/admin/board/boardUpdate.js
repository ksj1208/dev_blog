const boardUpdate = {

    init: () => {
        boardUpdate.getDetail()
        toastEditor.createFrame()
        boardUpdate.bind()
    },

    bind: () => {
        document.getElementById('updateBtn').addEventListener('click', boardUpdate.update)
    },

    getDetail: () => {
        const boardId = document.getElementById('boardId').value
        const successHandler = (data) => {
            document.getElementById('subject').value = data.subject
            toastEditor.setValue(data.content)
        }

        fetch('/boards/detail/'+ boardId, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    update: () => {
        const request = boardUpdate.createUpdateRequest()
        const successHandler = () => {
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
            .then((res) => {
                if(res.ok)
                    return successHandler();
                throw new Error();
            })
            .catch((error) => alert(error))
    },

    createUpdateRequest: () => {
        const content = toastEditor.getValue()
        const subject = document.getElementById('subject').value
        const boardId = document.getElementById('boardId').value
        const categoryId = document.getElementById('category').value

        const request = {content, subject, boardId, categoryId}

        return request
    }
}

boardUpdate.init()