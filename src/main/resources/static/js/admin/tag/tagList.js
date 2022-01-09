const tagList = {
    init: () => {
        tagList.bind()
        tagList.search(1)
    },

    bind: () => {
        document.getElementById('tagWriteBtn').addEventListener('click', tagList.onClicktagWrite)
        document.getElementById('tagDeleteBtn').addEventListener('click', tagList.onClicktagDelete)
        document.getElementById('checkboxAll').addEventListener('click', tagList.onClickCheckboxAll)
    },

    onClicktagDelete: () => {
        let tagIdArr = []
        document.querySelectorAll('.checkbox').forEach(target => {
            if(target.checked)
                tagIdArr.push(target.value)
        })

        const request = tagIdArr
        const successHandler = (data) => {
            tagList.search(1)
            // alert(data)
        }

        fetch("/tags/delete", {
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

    onClicktagWrite: () => {
        location.href = "/tags/tagWrite"
    },

    onClickCheckboxAll: () => {
        const checkAll = document.getElementById('checkboxAll')
        document.querySelectorAll('.checkbox').forEach(target => {
            target.checked = false;
        })

        if(checkAll.checked)
            document.querySelectorAll('.checkbox').forEach(target => {
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
            const data = response.tagList
            tagList.appendData(data.content)
            tagList.appendPaging(data.totalElements, pageNum)
        }

        fetch('/tags/list?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $('#tagListTable tr:not(:first)').remove();

        const rows = data.map((item, i) => {
            return `<tr>
                <td><input type="checkbox" class="checkbox" name="checkbox" value="${item.tagId}"></td>
                <td>${i + 1}</td>
                <td style="cursor: pointer" onclick= "location.href ='/tags/detailPage/${item.tagId}'">${item.tagName}</td>
<!--                <td>${item.tagName}</td>-->
                <td>${item.createDate}</td>
                <td>${item.updateDate}</td>
                <td>${item.tagStatus}</td>
            </tr>`
        }).join('')

        document.getElementById('tagListTable').insertAdjacentHTML('beforeend', rows)
    },

    appendPaging: (totalCount, pageNum) => {
        const paging = new Paging(20, 10, totalCount, pageNum)
        paging.showPaging()
        paging.attachClickEvent(tagList.search)
    }

}

tagList.init()