const likeContent = {

    init() {
        this.likeBtn = document.getElementById('likeBtn');
        this.boardId = document.getElementById('boardId');

        this.eventBind();
    },

    eventBind() {
        this.likeBtn.addEventListener('click', () => this.onclickLikeBtn());
    },

    async onclickLikeBtn() {
        const request = {};
        const fetchReq = await fetch("/boards/", {

            method: "PATCH",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify(request)
        })
    }
}

likeContent.init();

