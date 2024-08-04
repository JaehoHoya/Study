function first(){
    second();
    console.log("첫번째");
}

function second(){
    third();
    console.log("두번째");
}
function third(){
    console.log("세번째");
}

first();

//스택에 쌓임 1,2,3 순으로 쌓이고 3부터 실행 

function run(){
    console.log("3초 뒤에 실행 ");
}
console.log("시작");
setTimeout(run,3000);
console.log("끝");

// 시작 끝 3초 뒤 실행.   비동기 개념 알 것  단일 스레드 (순차 실행) 개념 알 것 난블라킹
// javascript의 런타임인 node.js은 non-blocking하고 비동기적이다.
//https://velog.io/@eamon3481/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-%EC%8B%B1%EA%B8%80%EC%8A%A4%EB%A0%88%EB%93%9C%EC%9D%B8%EB%8D%B0-%EC%99%9C-%EB%B9%84%EB%8F%99%EA%B8%B0%EC%A0%81-%EC%9D%BC%EA%B9%8C

function longRunningTask(){
    // 오래걸리는 작업 
    console.log('작업 끝');

}
console.log('시작');
longRunningTask();
console.log('다음 작업');  //시작 작업끝 다음 작업  일반함수라 순차적으로 ,비동기적으로 동작하는 이벤트가 아니기 때문에


