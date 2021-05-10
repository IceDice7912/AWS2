document.addEventListener("DOMContentLoaded", function() {

    var mySwiper = new Swiper('.swiper-container', {
        slidesPerView: 4, //슬라이드를 한번에 4개씩 보여줌
        slidesPerGroup: 4, // 그룹으로 묶는 수, SlidePerview와 같은 값을 지정하는게 좋음
        observer: true,
        observeParents: true,
        spaceBetween: 24, //슬라이드간 padding값 24px씩 떨어뜨려줌
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },

        
        // If we need pagination 
        pagination: {
            el: '.swiper-pagination',
            clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
            type: 'progressbar', // And if we need scrollbar 진행바
        },
        
        // Navigation arrows 
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        //반응형일때 크기조정
        breakpoints: {
            1280: {
                slidesPerView: 3,
                slidesPerGroup: 3,
            },
            720: {
                slidesPerView: 1,
                slidesPerGroup: 1,
            }
        }
    });
    
});

