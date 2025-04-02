// 우편번호 찾기 찾기 화면을 넣을 element
var element_wrap = document.getElementById("wrap");
const inputAddress = document.getElementById("input-address");
const form = document.getElementById("category-search-form");

form.addEventListener("submit", submit);
sample3_execDaumPostcode();

function submit(event) {
    event.preventDefault();

    if (inputAddress.value === "") {
        alert("주소를 입력해주세요.");
        return;
    }

    const formData = new FormData(this);
    const data = {};


    formData.forEach((value, key) => {
        data[key] = value;
    });

    this.submit();
}

function sample3_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ""; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === "R") {
                // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else {
                // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            inputAddress.value = addr;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize: function (size) {
            // element_wrap.style.height = size.height + "px";
            // element_wrap.style.height = 466 + "px";
        },
        width: "100%",
        height: "100%",
    }).embed(element_wrap, {
        autoClose: false,
    });

    // iframe을 넣은 element를 보이게 한다.
    element_wrap.style.display = "block";
}
