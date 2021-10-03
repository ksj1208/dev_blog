const boardList = {

    init: () => {
        boardList.bind()
        boardList.search(1)
    },

    bind: () => {
        document.getElementById("writeBtn").addEventListener('click', boardList.onclickWriteBtn);
    },

    onclickWriteBtn: () => {
        location.href = "/admin/board/writePage"
    },

    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 1
        }

        const successHandler= (response) => {
            const data = response.boardList
            boardList.appendData(data.content)
            boardList.appendPaging(data.totalElements, pageNum)
        }

        fetch('/boards/list?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $('#boardListTable tr:not(:first)').remove();

        const rows = data.map((item, i) => {
            return `<tr>
                <td>${i + 1}</td>
                <td>${item.subject}</td>
                <td>${item.writer}</td>
                <td>${item.createDate}</td>
                <td>${item.status}</td>
            </tr>`
        }).join('')

        document.getElementById('boardListTable').insertAdjacentHTML('beforeend', rows)
    },

    appendPaging: (totalCount, pageNum) => {
        const paging = new Paging(1, 10, totalCount, pageNum)
        paging.showPaging()
        paging.attachClickEvent(boardList.search)
    }

}

boardList.init()