const userList = {
    init: () => {
        userList.bind()
        userList.search(1)
    },

    bind: () => {
        document.getElementById('adminUserAddBtn').addEventListener('click', userList.onClickUserAdd)
    },

    onClickUserAdd: () => {
        alert("추가 눌림");
    },

    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 20
        }

        const successHandler= (response) => {
            console.log(response);
            const data = response.adminUserList
            userList.appendData(data.content)
            userList.appendPaging(data.totalElements, pageNum)
        }

        fetch('/admin/adminUser/list?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $('#userListTable tr:not(:first)').remove();

        const rows = data.map((item, i) => {
            return `<tr>
                <td>${i + 1}</td>                
                <td>${item.userId}</td>
                <td>${item.nickName}</td>
                <td>${item.createDate}</td>
                <td>${item.passwordUpdateDate}</td>
                <td>${item.email}</td>                
                <td>${item.status}</td>
                <td>${item.authority}</td>
            </tr>`
        }).join('')

        document.getElementById('adminUserListTable').insertAdjacentHTML('beforeend', rows)
        document.querySelectorAll('#statusSelectBox').forEach(target => {
            target.addEventListener('change', userList.onchangeStatus)
        })
    },

    onchangeStatus: (e) => {
        if(!confirm("이 프로필을 활성화 하시겠습니까? 다른 프로필은 비활성화 됩니다."))
            return

        const profileCode = e.target.dataset.profilecode
        const status = e.target.value
        const request = {profileCode, status}
        console.log("프로필 코드 " + profileCode);
        const successHandler = (data) => {
            alert(data)
            userList.search(1)
        }

        fetch("/admin/profile/status", {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then(data => successHandler(data))

    },

    appendPaging: (totalCount, pageNum) => {
        const paging = new Paging(20, 10, totalCount, pageNum)
        paging.showPaging()
        paging.attachClickEvent(userList.search)
    }
}

userList.init()