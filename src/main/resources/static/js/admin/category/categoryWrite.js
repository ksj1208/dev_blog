const categoryWrite = {

    init: () => {
        categoryWrite.bind()
    },

    bind: () => {
        document.getElementById('saveBtn').addEventListener('click', categoryWrite.save)
    },

    save: () => {
        categoryWrite.createRequest();
    },

    createRequest: () => {
        const categoryName = document.getElementById('categoryName').value
        const request = {
            categoryName: categoryName,
            categoryStatus: "사용"
        }

        const successHandler= () => {
            // alert("카테고리 등록 완료")
            location.href = "/category/categoryListPage"
        }

        fetch("/categories/save", {
            method: "POST",
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

categoryWrite.init()