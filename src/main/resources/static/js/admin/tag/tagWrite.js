const tagWrite = {

    init: () => {
        tagWrite.bind()
    },

    bind: () => {
        document.getElementById('saveBtn').addEventListener('click', tagWrite.save)
    },

    save: () => {
        tagWrite.createRequest();
    },

    createRequest: () => {
        const tagName = document.getElementById('tagName').value
        const request = {
            tagName: tagName,
            tagStatus: "사용"
        }

        const successHandler= () => {
            // alert("태그 등록 완료")
            location.href = "/tags/tagListPage"
        }

        fetch("/tags", {
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

tagWrite.init()