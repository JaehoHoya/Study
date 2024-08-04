// 1일 차 

console.log("hello world");

// 현재 시간을 구하는 코드 
let hours= (new Date()).getHours();
console.log(hours);
console.log(hours< 3 || 8<hours);
console.log(3 <=hours && hours <=8);

let pi =3.14;
console.log(pi);

let radius =10;

console.log(`둘레: ${2 *pi *radius}`);
console.log(`넓이: ${pi *radius *radius}`);
//let var const 차이 알것 .
/* var: 중복선언 재할당 가능 
   let: 중복선언 불가 재할당 가능 
   const: 중복선언 재할당 불가.



   var는 함수 단위 스코프 
   ler const 블록단위 스코프 

   호이스팅 방식 의미 알것.
*/
let a ="hello";
a +="world";
a+="!";
console.log(a);

// 증감 연산자 전위 후위
let number =10;
console.log("초기값:",number);
console.log(number++);
console.log(number--);
console.log(number);



console.log("초기값:",number);
console.log(++number);
console.log(--number);
console.log(number);


//자료형 
let b = "안녕";
console.log(typeof(b));
// 값을 결정해주지 않았기에 undefined 값이 결정될때 데이터 타입이 결정됨 
let c;
console.log(typeof(c));

// 자료형 변환 
console.log(Number("52"));
console.log(Number(false));
console.log(Number("안녕하세요"));
// 값 Not a Number  NaN 형변환은 했으나 숫자는 아니다.

//자동 자료형 변환 
console.log("52"+273); //52273
console.log(52+273);   //325 



// 일치 연산자 
console.log(`0==""-> ${0 ==""}`);
console.log(`0===""-> ${0 ===""}`); // === 자료형 값 모두 같아야 함  


// const  상수 : 항상 같은 수 라는 의미 변수와 반대되는 개념이며  변하지 않을 대상에 상수를 적용 

const name ="정재호";
//name ="안녕";

/* ***************************** */
// 조건문 

// 오전 오후 구분하기 
let date =new Date();

if(date.getHours()<12){
    console.log("오전입니다.");
}
else {
console.log("오후입니다.");
}


if( hours<11){
    console.log("아침 먹을 시간");
}else{
    if(hours<15){
        console.log("점심 먹을 시간");
    }
    else{
        console.log("저녁 먹을 시간");
    }
}

// 스위치 

let input = 12;

switch (input %2){
    case 0:
        console.log("짝수");
        break;
    case 1:
        console.log("홀수");
        break;
}


switch (date.getMonth()+1){
    case 12:
    case 1:
    case 2:
        console.log("겨울입니다.");
        break;
    case 3:
    case 4:
    case 5:
        console.log("봄입니다.");
        break;
    case 6:
    case 7:
    case 8:
        console.log("여름입니다.");
        break;
    case 9:
    case 10:
    case 11:
        console.log("가을입니다.");
        break;

    default:
        console.log("지구가 아닙니다.");
        break;
}

// 반복문 

for(let i =0; i<3; i++){
    console.log("집가고 싶다 ");

}

// 배열 

let array=[1,1,2,3,4,5];

for( let i =array.length-1;i>=0;i--){
    console.log(array[i]);
}

// for in for of 차이 확인할것 
for(let i in array){
    console.log(`${i}번째 요소 ${array[i]}`);
}
// 인덱스를 가져옴 

console.log("-------");

for(let i of array){
    console.log(i)
}
// 바로 요소를 가져옴 

// 중첩 반복문 

let output="";

for(let i=0;i<3;i++){
    for(let j=0;j<i+1;j++){
        output +="*";
    }
    output +="\n";
}
console.log(output);

let output2 ="";

// break
let i=0;
let out;

while(1){
    if(array[i]%2==0){
        out= array[i];
        break;
    }
    i =i+1;
}
console.log(`처음 발견한 짝수는 ${out}입니다.`)

for( let i =1; i<10; i++){
    if(i%2==0){
        continue;
    }
    console.log(i);
}

// 스코프 내부에서 이름 중복 
console.log("------")
let d = 1;
let e =1;
{
    let d=2;
    {
        let d=3;
        console.log(d);
        console.log(e);
    }
    console.log(d);
    console.log(e);
}


