const boardWrite = {

    init: () => {
        smartEditor.init()
        boardWrite.bind()
    },

    bind: () => {
        document.getElementById('saveBtn').addEventListener('click', boardWrite.save)
    },

    save: () => {
        boardWrite.createRequest()
    },

    createRequest: () => {
        const content = document.getElementById('content').value
        const request = {
            content: content,
            subject: "tetst"
        }

        fetch("/boards/save", {
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

boardWrite.init()