
//이벤트 리스너 등록
document.addEventListener('DOMContentLoaded', function(){
    // "선택 상품 삭제" 버튼 클릭
    document.querySelector('.basketrowcmd a:first-child').addEventListener('click', function(){
        basket.delCheckedItem();
    });

    // "장바구니 비우기" 버튼 클릭
    document.querySelector('.basketrowcmd a:nth-child(2)').addEventListener('click', function(){
        basket.delAllItem();
    });

    // 장바구니 행 "삭제" 버튼 클릭
    document.querySelectorAll('.basketcmd a').forEach(
        function(item){
            item.addEventListener('click', function(){
                basket.delItem();
            });
        }
    );   

    // 수량변경 - 이벤트 델리게이션으로 이벤트 종류 구분해 처리
    document.querySelectorAll('.updown').forEach(
        function(item, idx){
            //수량 입력 필드 값 변경
            item.querySelector('input').addEventListener('keyup', function(){
                basket.changePNum(idx+1);
            });

            //수량 증가 화살표 클릭
            item.children[1].addEventListener('click', function(){
                basket.changePNum(idx+1);
            });
            //수량 감소 화살표 클릭
            item.children[2].addEventListener('click', function(){
                basket.changePNum(idx+1);
            });
        }
    );

    //앵커 # 대체해 스크롤 탑 차단
    document.querySelectorAll('a[href="#"]').forEach(function(item){
        item.setAttribute('href','javascript:void(0)');
    });

});

let basket = {
    totalCount: 0, 
    totalPrice: 0,
    //체크한 장바구니 상품 비우기
    delCheckedItem: function(){
        document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {
            item.parentElement.parentElement.parentElement.remove();
        });
        //AJAX 서버 업데이트 전송
    
        //전송 처리 결과가 성공이면
        this.reCalc();
        this.updateUI();
    },
    //장바구니 전체 비우기
    delAllItem: function(){
        document.querySelectorAll('.row.data').forEach(function (item) {
            item.remove();
          });
          //AJAX 서버 업데이트 전송
        
          //전송 처리 결과가 성공이면
          this.totalCount = 0;
          this.totalPrice = 0;
          this.reCalc();
          this.updateUI();
    },
    //재계산
    reCalc: function(){
        this.totalCount = 0;
        this.totalPrice = 0;
        document.querySelectorAll(".p_num").forEach(function (item) {
            var count = parseInt(item.getAttribute('value'));
            this.totalCount += count;
            var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
            this.totalPrice += count * price;
        }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg
    },
    //화면 업데이트
    updateUI: function () {
        document.querySelector('#sum_p_num').textContent = '상품갯수: ' + this.totalCount.formatNumber() + '개';
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원';
    },
    //개별 수량 변경
    changePNum: function (pos) {
        var item = document.querySelector('input[name=p_num'+pos+']');
        var p_num = parseInt(item.getAttribute('value'));
        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
        
        if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }

        item.setAttribute('value', newval);
        item.value = newval;

        var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
        //AJAX 업데이트 전송

        //전송 처리 결과가 성공이면    
        this.reCalc();
        this.updateUI();
    },
    delItem: function () {
        event.target.parentElement.parentElement.parentElement.remove();
    }
}

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};