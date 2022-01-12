const toastEditor = {
    editor: null,

    createFrame() {
        const { Editor } = toastui;
        const { codeSyntaxHighlight } = Editor.plugin;

        toastEditor.editor = new Editor({
            el: document.querySelector('#editor'),
            previewStyle: 'vertical',
            height: '500px',
            plugins: [[codeSyntaxHighlight]]
        });

        toastEditor.setImageUploader(toastEditor.editor)
    },

    getValue() {
        return toastEditor.editor.getMarkdown()
    },

    setValue(content) {
        toastEditor.editor.setMarkdown(content)
    },

    setImageUploader(editor) {
        editor.removeHook("addImageBlobHook")
        editor.addHook("addImageBlobHook", (blob, callback) => {
            const formData = new FormData()
            const successHandler = (data) => {
                callback(data, "iamge")
            }

            formData.append("file", blob)

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
        })
    }
}