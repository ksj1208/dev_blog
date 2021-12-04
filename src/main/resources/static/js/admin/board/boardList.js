const boardList = {

    init: () => {
        boardList.bind()
        boardList.search(1)
    },

    bind: () => {
        document.getElementById("writeBtn").addEventListener('click', boardList.onclickWriteBtn)
        document.getElementById('termsAll').addEventListener('click', boardList.onclickTermsAll)
        document.getElementById('deleteBtn').addEventListener('click', boardList.onclickDeleteBtn)
        document.getElementById('updateBtn').addEventListener('click', boardList.onclickUpdateBtn)
    },

    onclickDeleteBtn: () => {
        let boardIdArr = []
        document.querySelectorAll('#terms').forEach(target => {
            if(target.checked)
                boardIdArr.push(target.value)
        })

        const request = boardIdArr
        const successHandler = (data) => {
            boardList.search(1)
            alert(data)
        }

        if(! confirm("게시글을 삭제하시겠습니까?"))
            return

        fetch("/boards/delete", {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then(data => successHandler(data))
    },

    onclickWriteBtn: () => {
        location.href = "/admin/board/writePage"
    },

    onclickUpdateBtn: () => {
        let boardIdArr = []
        document.querySelectorAll('#terms').forEach(target => {
            if(target.checked)
                boardIdArr.push(target.value)
        })

        if(boardIdArr.length == 0)
            return alert("수정할 게시글을 선택해주세요.")
        if(boardIdArr.length > 1)
            return alert("하나의 게시글만 수정이 가능합니다.")

        location.href = "/admin/board/updatePage/" + boardIdArr[0]
    },

    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 15,
            status: 'All'
        }

        const successHandler= (response) => {
            const data = response.boardList
            boardList.appendData(data.content)
            boardList.appendPaging(data.totalElements, pageNum)
        }

        fetch('/boards/list/'+ request.status + '?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $('#boardListTable tr:not(:first)').remove();

        const rows = data.map((item, i) => {
            return `<tr>
                <td class="checkBox"><input type="checkbox" id="terms" value="${item.boardId}"></td>
                <td>${i + 1}</td>
                <td>${item.subject}</td>
                <td>${item.writer}</td>
                <td>${item.createDate}</td>
                <td>
                    <select id="statusSelectBox" data-boardId = ${item.boardId}>
                        ${item.status == 'A'? `<option value="A" selected>공개</option>` : `<option value="A">공개</option>`}
                        ${item.status == 'I'? `<option value="I" selected>비공개</option>` : `<option value="I">비공개</option>`}
                    </select>
                </td>
            </tr>`
        }).join('')

        document.getElementById('boardListTable').insertAdjacentHTML('beforeend', rows)
        document.querySelectorAll('#statusSelectBox').forEach(target => {
            target.addEventListener('change', boardList.onchangeStatus)
        })
    },

    appendPaging: (totalCount, pageNum) => {
        const paging = new Paging(15, 10, totalCount, pageNum)
        paging.showPaging()
        paging.attachClickEvent(boardList.search)
    },

    onclickTermsAll: () => {
        const termsAll = document.getElementById('termsAll')
        document.querySelectorAll('#terms').forEach(target => {
            target.checked = false
        })

        if(termsAll.checked)
            document.querySelectorAll('#terms').forEach(target => {
                target.checked = true
            })
    },

    onchangeStatus: (e) => {
        if(! confirm("상태를 변경하시겠습니까?"))
            return

        const boardId = e.target.dataset.boardid
        const status = e.target.value
        const request = {boardId, status}
        const successHandler = (data) => {
            alert(data)
            boardList.search(1)
        }

        fetch("/boards/status", {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then(data => successHandler(data))
    }
}

boardList.init()