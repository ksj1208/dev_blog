const main = {
    init: () => {
        main.bind()
    },

    bind: () => {
        document.body.addEventListener('scroll', main.onScrollBody)
        document.querySelector('.menuBtn').addEventListener('click', main.onClickMenuBtn)
        document.querySelector('.sideBarDim').addEventListener('click', main.onClickSideBar)
        document.getElementById('search_open').addEventListener('click', main.onClickSearchOpen)
    },

    onClickMenuBtn: () => {
        const menuBtn = document.querySelector('.menuBtn')
        const sideBar = document.querySelector('.sideBarWrap')

        if(menuBtn.classList.contains('active')) {
            document.body.classList.remove('scroll-lock')
            menuBtn.classList.remove('active')
            sideBar.classList.remove('on')
        } else {
            document.body.classList.add('scroll-lock')
            menuBtn.classList.add('active')
            sideBar.classList.add('on')
        }
    },

    onClickSideBar: () => {
        const sideBarDim = document.querySelector('.sideBarDim')
        const menuBtn = document.querySelector('.menuBtn')
        const sideBar = document.querySelector('.sideBarWrap')

        document.body.classList.remove('scroll-lock')
        menuBtn.classList.remove('active')
        sideBar.classList.remove('on')
    },

    onClickSearchOpen: () => {
        const searchOpen = document.getElementById('search_open')
        const searchArea = document.querySelector('.search_area')
        const inputText = document.querySelector('.inp_txt')

        if(searchOpen.classList.contains('active')) {
            searchOpen.classList.remove('active')
            searchArea.classList.remove('on')
            inputText.value = ''
        } else {
            inputText.focus()
            searchOpen.classList.add('active')
            searchArea.classList.add('on')
        }
    },

    onScrollBody: () => {
        const height = document.body.scrollTop
        const element = document.querySelector('.tag')

        if (height >= 550) {
            $('.tag').addClass('fixed-menu');
            $('.tag').css({
                "top": $('header').outerHeight() + 10 + "px"
            });
            Stickyfill.add(element);
        } else {
            $('.tag').removeClass('fixed-menu');
            $('.tag').css({
                "top": $('header').outerHeight() + 10 + "px"
            });
            Stickyfill.remove(element);
        }
    }
}

main.init()