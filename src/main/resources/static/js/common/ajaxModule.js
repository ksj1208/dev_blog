const ajaxModule = {
    contentType: 'application/json;charset=UTF-8',
    dataType: 'json',
    csrfToken: $("meta[name='_csrf']").attr("content"),
    csrfHeader: $("meta[name='_csrf_header']").attr("content"),
    
    //get 요청
    get: function (_url, _parameters, _callback) {
        if (_url != null) {
            //5번째 파라미터로 동기화 방식을 받을 수 있다.
            //존재 여부에 따라서 넘기지 않으면 기본 true
            let _async = arguments[4];
            if (_async == null) _async = true;
            //에러 시 처리할 함수를 4번째 파라미터로 받을 수 있다.
            let _errorMsg = arguments[3];
            //get 요청이기 때문에 쿼리스트링으로 변환
            let queryString = '?' + $.param(_parameters);

            $.ajax({
                type: "GET",
                url: _url + queryString,
                async: _async,
                success: function (rst) {
                    _callback(rst);
                },
                error: function (e) {
                    if (_errorMsg != null)
                        _errorMsg(e);
                    else
                        alert("System Error!!");
                }
            });

        } else {
            alert("System Error!!");
            return false;
        }
    },

    post: function (_url, _parameters, _callback) {
        if (_url != null) {
            let _errorMsg = arguments[3];
            let _async = arguments[4];

            if (_async == null) _async = true;

            $.ajax({
                type: "POST",
                url: _url,
                data: JSON.stringify(_parameters),
                async: _async,
                contentType: this.contentType,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(ajaxModule.csrfHeader, ajaxModule.csrfHeader);
                },
                success: function (rst) {
                    _callback(rst);
                },
                error: function (e) {
                    if (_errorMsg != null)
                        _errorMsg(e);
                    else
                        alert("System Error!!");
                }
            });

        } else {
            alert("System Error!!");
            return false;
        }
    },

    patch: function (_url, _parameters, _callback) {
        if (_url != null) {
            let _errorMsg = arguments[3];
            let _async = arguments[4];

            if (_async == null) _async = true;

            $.ajax({
                type: "PATCH",
                url: _url,
                data: JSON.stringify(_parameters),
                async: _async,
                contentType: this.contentType,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(ajaxModule.csrfHeader, ajaxModule.csrfHeader);
                },
                success: function (rst) {
                    _callback(rst);
                },
                error: function (e) {
                    if (_errorMsg != null)
                        _errorMsg(e);
                    else
                        alert("System Error!!");
                }
            });
        } else {
            alert("System Error!!");
            return false;
        }
    },

    delete: function (_url, _parameters, _callback) {
        if (_url != null) {
            let _errorMsg = arguments[3];
            let _async = arguments[4];

            if (_async == null) _async = true;

            $.ajax({
                type: "DELETE",
                url: _url,
                data: JSON.stringify(_parameters),
                async: _async,
                contentType: this.contentType,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(ajaxModule.csrfHeader, ajaxModule.csrfHeader);
                },
                success: function (rst) {
                    _callback(rst);
                },
                error: function (e) {
                    if (_errorMsg != null)
                        _errorMsg(e);
                    else
                        alert("System Error!!");
                }
            });

        } else {
            alert("System Error!!");
            return false;
        }
    }
}