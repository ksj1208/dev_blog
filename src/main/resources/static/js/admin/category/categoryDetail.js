const categoryDetail = {

    init: () => {
        categoryDetail.bind()
    },

    bind: () => {
        document.getElementById('updateBtn').addEventListener('click', categoryDetail.update)
    },

    update: () => {
        categoryDetail.updateRequest();
    },

    updateRequest: () => {
        const categoryId = document.getElementById('categoryId').value
        const categoryName = document.getElementById('categoryName').value
        const categoryStatus = document.getElementById('categoryStatus').value
        const request = {
            categoryId: categoryId,
            categoryName: categoryName,
            categoryStatus: categoryStatus
        }

        console.log("request = " , request);

        const successHandler= () => {
            // alert("카테고리 수정 완료")
            location.href = "/category/categoryListPage"
        }

        fetch("/categories/update", {
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

categoryDetail.init()