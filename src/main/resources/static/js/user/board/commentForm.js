const commentForm = {
    init() {
        this.bind()
        this.getCommentList()
    },

    bind() {
        document.getElementById('commentBtn').addEventListener('click', this.onclickCommentBtn)
    },

    getCommentList() {
        const boardId = document.getElementById('boardId').value
        const successHandler = (data) => {
            document.getElementById('commentCount').innerText = data.commentList.length + '개의 댓글'
            commentForm.tableAppend(data)
        }
        fetch('/comment/list/' + boardId, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    tableAppend(data) {
        document.getElementById('comment_box').innerHTML = ''
        console.log(data)
        const rows = data.commentList.map((item, i) => {
            return `<div class="comment">
                        <div class="flex-between">
                            <div class="cbox-info">
                                <div class="cbox_profile">${item.nickName}</div>
                                <div class="cbox_date">${item.createDate}</div>
                            </div>
                            ${item.writeRole == 'Y'? `
                                 <div class="cbox-action">
                                    <button type="button" onclick="commentForm.showUpdateForm(this, ${item.commentId})">수정</button>
                                    <button type="button" class="btn-delComment" onclick="commentForm.deleteComment(${item.commentId})">삭제</button>
                                </div>
                            `:``}
                        </div>
                        <div class="cbox_txt">
                            <pre style="font-size: 15px;">${item.comment}</pre>
                        </div>
                        <div class="cbox_tool">
                            <ul class="flex-start">
                                <li><button type="button" class="btn-reply">답글</button></li>
                                <li>
                                    <a href="javascript:;" class="btn-like">
                                        <span class="ico-like">좋아요</span>
                                        <span class="like-count">공감</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>`
        }).join('')

        document.getElementById('comment_box').insertAdjacentHTML('beforeend', rows)
    },

    deleteComment(commentId) {
        if(! confirm("댓글 삭제하시겠습니까?")) return
        const successHandler = () => {
            alert("댓글이 삭제되었습니다.")
            commentForm.getCommentList()
        }

        fetch("/comment/delete/" + commentId, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
        })
            .then((response) => {
                if(response.ok)
                    return successHandler(response)
                else
                    throw response
            })
            .catch((error) => error.text().then((res) => alert(res)))
    },

    showUpdateForm(e, id) {
        const commentArea = e.parentNode.parentNode.nextElementSibling
        const commentTag = commentArea.firstElementChild
        const cBoxAction = e.parentNode.parentNode.lastChild.previousElementSibling
        const cBoxTag = cBoxAction.innerHTML

        cBoxAction.innerHTML = `<div class="cbox-action">
                                    <button type="button" id="updateBtn_${id}">저장</button>
                                    <button type="button" id="updateCancel_${id}" class="btn-delComment">취소</button>
                                </div>`
        commentArea.innerHTML = `<textarea id="newComment_${id}" class="comment-input" rows="8" wrap="hard">${commentTag.textContent}</textarea>`

        document.getElementById('updateCancel_' + id).addEventListener('click', () => {
            commentArea.innerHTML = ''
            commentArea.appendChild(commentTag)
            cBoxAction.innerHTML = cBoxTag
        })

        document.getElementById('updateBtn_' + id).addEventListener('click', (e) => {
            commentForm.updateComment(id)
        })
    },

    updateComment(commentId) {
        const comment = document.getElementById('newComment_' + commentId).value

        if(! confirm("댓글을 수정하시겠습니까?")) return

        const successHandler = () => {
            alert("댓글 수정을 완료했습니다.")
            commentForm.getCommentList()
        }

        fetch("/comment/update", {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify({commentId, comment})
        })
            .then((response) => {
                if(response.ok)
                    return successHandler(response)
                else
                    throw response
            })
            .catch((error) => error.text().then((res) => alert(res)))
    },

    onclickCommentBtn() {
        const comment = document.getElementById('comment').value
        const boardId = document.getElementById('boardId').value
        commentForm.commentSave(comment, boardId)
    },

    commentSave(comment, boardId) {
        if(! confirm("댓글 등록을 하시겠습니까?")) return
        if(! commentForm.isValidComment(comment)) return

        const successHandler = () => {
            alert("댓글이 등록되었습니다.")
            document.getElementById('comment').value = ""
            commentForm.getCommentList()
        }

        fetch("/comment/save", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify({comment, boardId})
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
            .catch((error) => alert(error))
    },

    isValidComment(comment) {
        if(comment.length === 0) {
            alert("댓글 내용을 입력해주세요.")
            return false
        }

        return true
        //TODO 비속어, 특정단어 필터링 필요.
    }
}

commentForm.init()