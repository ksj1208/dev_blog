const categoryMain = {

    init: () => {
        categoryMain.bind()
    },

    bind: () => {
        document.getElementById('saveBtn').addEventListener('click', categoryMain.save)
    },

    save: () => {
        categoryMain.createRequest()
    },

    createRequest: () => {
        const categoryName = document.getElementById('categoryName').value
        const request = {
            categoryName: categoryName
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
            .catch((error) => alert(error))
    }
}

categoryMain.init()