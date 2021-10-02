const smartEditor = {
    init: (target) => {
        smartEditor.createFrame(target)
        smartEditor.bind()
    },

    bind: () => {

    },

    createFrame: () => {
            $('.summernote').summernote({
            height: 400,
            width: 1000,
            lang: "ko-KR",
            callbacks: {
                onImageUpload: (files) => {
                    Array.from(files).forEach((file) => {
                        smartEditor.imageUpload(file)
                    })
                }
            }
        });
    },

    imageUpload: (file) => {
        const formData = new FormData()
        formData.append("file", file)

        const successHandler = (data) => {
            console.log(data)
            $('.summernote').summernote('editor.insertImage', data)
        }

        fetch("/files/imageUpload", {
            method: "POST",
            headers: {
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            body: formData
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
            .catch((error) => alert(error))
    }
}