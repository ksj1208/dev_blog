const categoryList = {

    init: () => {
        categoryList.bind()
        categoryList.search(1)
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
            const data = response.categoryList
            categoryList.appendData(data.content)
        }

        fetch('/categories/list/'+ request.status + '?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $(".catecory").empty()

        const rows = data.map((item, i) => {
            return `<a href="#" class="cat-item">${item.categoryName}</a>`
        }).join('')

        document.getElementById('category__content').insertAdjacentHTML('afterbegin', (rows == "") ? `<a href="#" class="cat-item">없음</a>` : rows)
    }
}

categoryList.init()