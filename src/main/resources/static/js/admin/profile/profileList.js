const profileList = {
    init: () => {
        profileList.bind()
        profileList.search(1)
    },

    bind: () => {
        document.getElementById('profileWriteBtn').addEventListener('click', profileList.onClickProfileWrite)
        document.getElementById('profileCheckAll').addEventListener('click', profileList.onClickProfileCheckAll)
    },

    onClickProfileWrite: () => {
        location.href = "/profile/profileWriter"
    },

    onClickProfileCheckAll: () => {
        const checkAll = document.getElementById('profileCheckAll')
        document.querySelectorAll('#profileCheck').forEach(target => {
            target.checked = false;
        })

        if(checkAll.checked)
            document.querySelectorAll('#profileCheck').forEach(target => {
                target.checked = true;
            })
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
            return `<tr>
                <td><input type="checkbox" id="profileCheck" name="profileCheck" value="${item.profileCode}"></td>
                <td>${i + 1}</td>
                <td style="cursor: pointer" onclick= "location.href ='/profile/detailPage/${item.profileCode}'">${item.subject}</td>
                <td>${item.writer}</td>
                <td>${item.createDate}</td>
                <td>
                    <select id="statusSelectBox" data-profileCode = ${item.profileCode}>
                        ${item.status == 'A'? `<option value="A" selected>공개</option>` : `<option value="A">공개</option>`}
                        ${item.status == 'I'? `<option value="I" selected>비공개</option>` : `<option value="I">비공개</option>`}
                    </select>
                </td>
            </tr>`
        }).join('')

        document.getElementById('profileListTable').insertAdjacentHTML('beforeend', rows)
        document.querySelectorAll('#statusSelectBox').forEach(target => {
            target.addEventListener('change', profileList.onchangeStatus)
        })
    },

    onchangeStatus: (e) => {
        if(!confirm("상태를 변경하시겠습니까?"))
            return

        const profileCode = e.target.dataset.profilecode
        const status = e.target.value
        const request = {profileCode, status}
        console.log("프로필 코드 " + profileCode);
        const successHandler = (data) => {
            alert(data)
            profileList.search(1)
        }

        fetch("/profile/status", {
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
        paging.attachClickEvent(profileList.search)
    }
}

profileList.init()