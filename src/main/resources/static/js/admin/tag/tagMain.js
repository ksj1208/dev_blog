const tagMain = {

    init: () => {
        tagMain.bind()
    },

    bind: () => {
        document.getElementById('saveBtn').addEventListener('click', tagMain.save)
    },

    save: () => {
        tagMain.createRequest()
    },

    createRequest: () => {
        const tagName = document.getElementById('tagName').value
        const request = {
            tagName: tagName
        }

        fetch("/tags/save", {
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

tagMain.init()