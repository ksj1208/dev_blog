const boardList = {

    init: () => {
        boardList.bind()
        boardList.search(1)
    },

    bind: () => {

    },

    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 2
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
        $(".post-item").remove()

        const rows = data.map((item, i) => {
            return `<div class="post-item">
                        <a href="#" onclick= "location.href ='/user/board/detailPage/${item.boardId}'">
                            <p class="info">
                                <span class="date">2021년 8월 13일</span>
                                <span class="author">${item.writer}</span>
                            </p>
                         <h2>${item.subject}</h2>
                         <p>${item.content.substr(0, 10)}</p>    
                         <p>...</p>                
                        </a>
                    </div>`
        }).join('')

        document.getElementById('contentArea').insertAdjacentHTML('afterbegin', rows)
    },

    appendPaging: (totalCount, pageNum) => {
        const paging = new Paging(2, 10, totalCount, pageNum)
        paging.showPaging()
        paging.attachClickEvent(boardList.search)
    }

}

boardList.init()