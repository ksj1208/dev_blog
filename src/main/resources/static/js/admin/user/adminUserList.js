const adminUserList = {
    init: () => {
        //adminUserList.bind()
        adminUserList.search(1)
    },

    /*bind: () => {
        document.getElementById('adminUserAddBtn').addEventListener('click', adminUserList.onClickUserAdd)
    },

    onClickUserAdd: () => {
        alert("추가 눌림");
    },*/

    search: (pageNum) => {
        const request = {
            page: pageNum - 1,
            size: 20
        }

        const successHandler= (response) => {
            console.log(response);
            const data = response.adminUserList
            adminUserList.appendData(data.content)
            adminUserList.appendPaging(data.totalElements, pageNum)
        }


        fetch('/admin/adminUser/list?' + $.param(request), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    appendData: (data) => {
        $('#adminUserListTable tr:not(:first)').remove();

        const rows = data.map((item, i) => {
            return `<tr>
                <td>${i + 1}</td>                
                <td>${item.userId}</td>
                <td>${item.nickName}</td>
                <td>${item.createDate}</td>
                <td>${item.passwordUpdateDate == null ? "-" : item.passwordUpdateDate}</td>
                <td>${item.email}</td>                
                <td>${item.status}</td>
                <td>
                    <select id="statusSelectBox" data-userCode = ${item.userCode}>
                        ${item.authority == 'ROLE_ADMIN'? `<option value="ROLE_ADMIN" selected>관리자</option>` : `<option value="ROLE_ADMIN">관리자</option>`}
                        ${item.authority == 'ROLE_USER'? `<option value="ROLE_USER" selected>유저</option>` : `<option value="ROLE_USER">유저</option>`}
                    </select>
                </td>
            </tr>`
        }).join('')

        document.getElementById('adminUserListTable').insertAdjacentHTML('beforeend', rows)
        document.querySelectorAll('#statusSelectBox').forEach(target => {
            target.addEventListener('change', adminUserList.onchangeStatus)
        })
    },
    onchangeStatus: (e) => {
        if(!confirm("계정의 권한을 변경 하시겠습니까?"))
            return

        const userCode = e.target.dataset.usercode
        const authority = e.target.value
        const request = {userCode, authority}

        console.log("유저 코드 " + userCode);
        const successHandler = (data) => {
            alert(data)
            adminUserList.search(1)
        }

        fetch("/admin/adminUser/authority", {
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
        paging.attachClickEvent(adminUserList.search)
    }
}

adminUserList.init()