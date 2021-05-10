var bullet = ['1번', '2번', '3번'];
var swiper = new Swiper('.mid_swiper-container', {
  pagination: {
    el: '.mid_swiper-pagination',
    clickable: true,
    renderBullet: function (index, className) {
      return '<div class="' + className + '"><span>' + (bullet[index]) + '</span></div>';
    }
  },
  navigation: {
    nextEl: '.mid_swiper-button-next',
    prevEl: '.mid_swiper-button-prev',
  },
});
