const tagDetail = {

    init: () => {
        tagDetail.bind()
    },

    bind: () => {
        document.getElementById('updateBtn').addEventListener('click', tagDetail.update)
    },

    update: () => {
        tagDetail.updateRequest();
    },

    updateRequest: () => {
        const tagId = document.getElementById('tagId').value
        const tagName = document.getElementById('tagName').value
        const tagStatus = document.getElementById('tagStatus').value
        const request = {
            tagId: tagId,
            tagName: tagName,
            tagStatus: tagStatus
        }

        console.log("request = " , request);

        const successHandler= () => {
            // alert("카테고리 수정 완료")
            location.href = "/tags/tagListPage"
        }

        fetch("/tags/update", {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then((data) => console.log(data))
            .then((data) => successHandler(data))
            .catch((error) => alert(error))
    }
}

tagDetail.init()