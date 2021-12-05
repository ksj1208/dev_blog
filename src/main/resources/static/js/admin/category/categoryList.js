const categoryList = {
    init: () => {
        categoryList.bind()
        categoryList.search(1)
    },

    bind: () => {
        document.getElementById('categoryWriteBtn').addEventListener('click', categoryList.onClickCategoryWrite)
        document.getElementById('checkboxAll').addEventListener('click', profileList.onClickCheckboxAll)
    },

    onClickCategoryWrite: () => {
        location.href = "/category/categoryWrite"
    },

    onClickCheckboxAll: () => {
        const checkAll = document.getElementById('checkboxAll')
        document.querySelectorAll('#checkbox').forEach(target => {
            target.checked = false;
        })

        if(checkAll.checked)
            document.querySelectorAll('#checkbox').forEach(target => {
                target.checked = true;
            })
    },

    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 20
        }

        const successHandler= (response) => {
            console.log("response : ", response);
            const data = response.categoryList
            categoryList.appendData(data.content)
            categoryList.appendPaging(data.totalElements, pageNum)
        }

        fetch('/categories/list?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $('#categoryListTable tr:not(:first)').remove();

        const rows = data.map((item, i) => {
            return `<tr>
                <td><input type="checkbox" id="checkbox" name="checkbox" value="${item.categoryId}"></td>
                <td>${i + 1}</td>
                <td>${item.categoryName}</td>
                <td>${item.createDate}</td>
                <td>${item.categoryStatus}</td>
            </tr>`
        }).join('')

        document.getElementById('categoryListTable').insertAdjacentHTML('beforeend', rows)
    },

    appendPaging: (totalCount, pageNum) => {
        const paging = new Paging(20, 10, totalCount, pageNum)
        paging.showPaging()
        paging.attachClickEvent(categoryList.search)
    }

}

categoryList.init()