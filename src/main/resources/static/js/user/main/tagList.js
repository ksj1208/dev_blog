const tagList = {

    init: () => {
        tagList.bind()
        tagList.search(1)
    },

    bind: () => {

    },

    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 5,
            status: '사용'
        }

        const successHandler= (response) => {
            const data = response.tagList
            tagList.appendData(data.content)
        }

        fetch('/tags/'+ request.status + '?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $(".tag").children("ul").empty();

        const rows = data.map((item, i) => {
            return `<li><a href="#" class="tag-name">${item.tagName}<span class="tag-count">(board cnt)</span></a> </li>`
        }).join('')

        document.getElementById('tag__content').insertAdjacentHTML('afterbegin', (rows == "") ? `<li><a href="#" class="tag-name">없음<span class="tag-count">(board cnt)</span></a> </li>` : rows)
    }
}

tagList.init()