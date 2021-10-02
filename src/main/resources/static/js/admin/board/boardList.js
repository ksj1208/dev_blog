const boardList = {

    init: () => {
        boardList.bind()
    },

    bind: () => {
        document.getElementById("writeBtn").addEventListener('click', boardList.onclickWriteBtn);
    },

    onclickWriteBtn: () => {
        location.href = "/admin/board/writePage"
    }

}

boardList.init()