const boardWrite = {

    init() {
        this.saveBtn = document.getElementById('saveBtn');
        this.eventBind();

        toastEditor.createFrame()
    },

    eventBind() {
        this.saveBtn.addEventListener('click', boardWrite.save)
    },

    save: () => {
        const request = boardWrite.createSaveRequest()
        const successHandler = () => {
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
            .then((res) => {
                if(res.ok)
                    return successHandler();
                throw new Error();
            })
            .catch(() => alert("오류입니다."))

    },

    createSaveRequest: () => {
        const content = toastEditor.getValue()
        const subject = document.getElementById('subject').value
        const categoryId = document.getElementById('category').value
        const tagList = Object.fromEntries(tag.tagList);
        const request = {content, subject, categoryId, tagList};

        return request
    }
}

boardWrite.init()