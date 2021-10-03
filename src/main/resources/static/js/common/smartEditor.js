const smartEditor = {
    init: (target) => {
        smartEditor.createFrame(target)
        smartEditor.bind()
    },

    bind: () => {

    },

    createFrame: () => {
        const fontList = ['맑은 고딕','굴림','돋움','바탕','궁서','NotoSansKR'
            ,'Arial','Courier New','Verdana','Tahoma','Times New Roamn'];

        $('.summernote').summernote({
            height: 400,
            width: 1000,
            lang: "ko-KR",
            fontNames: fontList,
            fontNamesIgnoreCheck: fontList,
            callbacks: {
                onImageUpload: (files) => {
                    Array.from(files).forEach((file) => {
                        smartEditor.imageUpload(file)
                    })
                },
                onMediaDelete : function($target, editor, $editable) {
                    //TODO 이미지 삭제 버튼 클릭 시 처리 추가
                    console.log($target[0].src);
                }
            },
        });
    },

    imageUpload: (file) => {
        const formData = new FormData()
        formData.append("file", file)

        const successHandler = (data) => {
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