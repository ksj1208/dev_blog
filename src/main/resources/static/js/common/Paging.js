class Paging {

    constructor(totalCount, pageCount, listCount, nowPage) {
        this.maxRow = totalCount
        this.maxPage = pageCount
        this.totalRow = listCount
        this.nowPage = nowPage
        this.maxPage = 10
        this.minPage = 0
        this.pageTag = arguments[4] == null? document.getElementById('pageList'): arguments[4]
    }

    showPaging() {
        this.pageTag.innerHTML = ''
        let pagingLength = 1

        if (this.totalRow > this.maxRow)
            pagingLength = (this.totalRow % this.maxRow === 0) ? (this.totalRow / this.maxRow) : Math.floor(this.totalRow / this.maxRow) + 1

        this.minPage = Math.floor((this.nowPage - 1) / this.maxPage)

        let start = this.minPage * this.maxPage + 1
        let end = (this.minPage + 1) * this.maxPage

        if (this.minPage > 0)
            this.pageTag.innerHTML += '<li value="' + (start - 1) + '"> <a href="#"><<</a></li>'

        if ((1 < this.nowPage))
            this.pageTag.innerHTML += '<li value="' + (Number(this.nowPage) - 1) + '"> <a href="#"><</a></li>'

        for(let i = start; i <= end; i++) {
            if(i > pagingLength)
                break
            if(i == this.nowPage)
                this.pageTag.innerHTML += '<li value="' + i + '"><a href="#" style="color: #0056b3">' + i + '</a></li>'
            else
                this.pageTag.innerHTML += '<li value="' + i + '"><a href="#">' + i + '</a></li>'
        }

        if(pagingLength > this.nowPage)
            this.pageTag.innerHTML += '<li value="' + (Number(this.nowPage) + 1) + '"><a herf="#)">></a></li>'
        if(end < pagingLength)
            this.pageTag.innerHTML += '<li value="' + (end + 1) + '"><a herf="#)">>></a></li>'
    }

    attachClickEvent(searchFunction) {
        $('#pageList li').each(function(){
            $(this).click(function(){
                this.nowPage = $(this).attr("value");
                searchFunction($(this).attr("value"));
            });
        });
    }
}