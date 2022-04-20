const tag = {
    tags: [],

    tagList: new Map,

    init() {
        this.searchArea = document.getElementById('searchArea');
        this.autoMaker = document.getElementById('autoMaker');
        this.tagsValue = document.getElementById('tagsValue');

        this.inputTag = document.querySelectorAll("input[name='tagCode']");
        this.selectTagList();
        this.eventBind();
    },

    eventBind() {
        this.searchArea.addEventListener('keyup', (e) => this.onKeyupSearchArea(e))
        this.searchArea.addEventListener('keydown', () => this.onKeydownSearchArea())
        this.inputTag.forEach((tag) => {
            this.tagList.set(tag.dataset.name, tag.dataset.code)
        })
    },

    async selectTagList() {
        const request = {
            page: 0,
            size: 100,
        }

        const fetchReq = await fetch("/tags/사용?" + $.param(request));
        const result = await fetchReq.json();
        const tagList = result.tagList.content;

        tagList.map((item) => {
            tag.tags.push({key:item.tagName, value:item.tagId})
        })

        tag.tags.push();
    },

    onKeydownSearchArea() {
        $('#insert_target').val('');
    },

    onKeyupSearchArea(e) {
        const txt = this.searchArea.value;  //입력된 데이터
        this.autoMaker.innerHTML = '';

        if(! txt) return;

        this.showTagList(txt);

        if(e.key == 'Enter') {
            if(tag.tagList.get(txt))
                return alert("이미 등록된 태그입니다.");

            const row = `
                    <div style="display: inline-block"><label class="tag-name">${txt}</label> 
                    <button onclick="tag.deleteTag(this)">X</button></div>`;

            tag.tagsValue.innerHTML += row;
            tag.searchArea.value = '';
            tag.tagList.set(txt, -1);
        }
        this.onclickAutoMaker();
    },

    showTagList(txt) {
        this.tags.forEach(function(arg){
            if(arg.key.indexOf(txt) > -1 )
                this.autoMaker.innerHTML += `<div data-key="${arg.value}">${arg.key}</div>`;
        });
    },

    onclickAutoMaker() {
        $('#autoMaker').children().each(function(){
            $(this).click(function(){
                if(tag.tagList.get($(this).text()))
                    return alert("이미 등록된 태그입니다.");

                const row = `
                    <div style="display: inline-block"><label class="tag-name">${$(this).text()}</label> 
                    <button onclick="tag.deleteTag(this)">X</button></div>`;
                tag.tagList.set($(this).text(), $(this).data('key'));
                tag.tagsValue.innerHTML += row;
                tag.autoMaker.innerHTML = '';
                tag.searchArea.value = '';
            });
        });
    },

    deleteTag(target) {
        target.parentNode.remove();
        tag.tagList.delete(target.previousElementSibling.innerText);
    },
}

tag.init();