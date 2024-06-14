// 쿠키 만들기
function saveCookie(cookie, value, expires) {
    let now = new Date();

    now.setSeconds(now.getSeconds() + expires);

    console.log(now.toUTCString());

    let myCookie = `${cookie}=${value};expires=${now.toUTCString()}`;

    document.cookie = myCookie;
}

// 쿠키 읽기
function readCookie(cookie) {
    let cook = document.cookie;
    console.log(cook);
    let cookArr = cook.split(";");

    // console.log(cookArr);
    let isFind = false;
    let searchName = cookie;

    console.log(searchName);
    for (let i = 0; i < cookArr.length; i++) {
        let cookName = cookArr[i].split("=")[0];
        console.log(cookName);

        if (cookName.trim() == searchName) {
            isFind = true;
        }
    }

    return isFind;
}

// 쿠키 삭제
function delCookie() {
    // // 삭제할 쿠키: myCook
    // // 삭제할 쿠키의 expires 값을 현재 시간으로 설정
    // let delName = "myCook";
    // let now = new Date();
    // let cookie = delName + "=;expires=" + now.toUTCString();
    // document.cookie = cookie;
}

// 쿼리스트링에서 파라미터 값 반환
function getParameter(paraName) {
    let returnVal = "";
}
