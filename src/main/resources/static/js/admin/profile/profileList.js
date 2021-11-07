const profileList = {
    init: () => {
        profileList.bind()
        profileList.search(1)
    },

    bind: () => {
        document.getElementById('profileWriteBtn').addEventListener('click', profileList.onClickProfileWrite)
    },

    onClickProfileWrite: () => {
        location.href = "/profile/profileWriter"
    },


    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 20
        }

        const successHandler= (response) => {
            const data = response.profileList
            profileList.appendData(data.content)
            profileList.appendPaging(data.totalElements, pageNum)
        }

        fetch('/profiles/list?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },


    appendData: (data) => {
        $('#profileListTable tr:not(:first)').remove();

        const rows = data.map((item, i) => {
            return `<tr style="cursor: pointer" onclick= "location.href ='/profile/detailPage/${item.profileCode}'">
                <td>${i + 1}</td>
                <td>${item.subject}</td>
                <td>${item.writer}</td>
                <td>${item.createDate}</td>
                <td>${item.status}</td>
            </tr>`
        }).join('')

        document.getElementById('profileListTable').insertAdjacentHTML('beforeend', rows)
    },

    appendPaging: (totalCount, pageNum) => {
        const paging = new Paging(20, 10, totalCount, pageNum)
        paging.showPaging()
        paging.attachClickEvent(profileList.search)
    }

}

profileList.init()