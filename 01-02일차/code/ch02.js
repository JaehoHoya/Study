//함수 : 익명함수 , 선언적함수 ,화살표함수 


// 익명함수 : 변수에 함수를 넣은 . 특정 코드에서 한번만 사용할때 사용됨
let a= function(){
    console.log("a 익명 함수 입니다.");
}

//선언적 함수 
function b(){
    console.log("b 선언적 함수 입니다.");
}
// 화살표 함수 
let c =()=>{
    console.log("c 화살표 함수 입니다.")
}

a();
console.log(a);
b();
console.log(b);


console.log("예제 코드---------------------------");
const greet1 =function(name){
    console.log("안녕하세요"+name);
}
const greet2=(name)=>{
    console.log("안녕하세요"+name);
}

greet1("재호");
greet2("jaeho");

/*
console.log("예제 코드 2---------------------------");

console.log(greet1("하이"));

const greet1 =function(name){
    console.log("안녕하세요"+name);
}
*/

//선언적함수면은 먼저 읽어져서 위코드 예제2 처럼 에러 안뜸  익명함수는 먼저 선언을 해줘야함  함수를 호이스팅 개념 이해할것 익명함수 =화살표함수 둘다 먼저 선언 해줘야함 


const aa=(x)=>{
        return x*x;
}

console.log("aa 함수 리턴 값:"+aa(10));

function multiply(min,max){
    let output =1;
    for(let i=min; i<=max; i++){
    output *=i;
    }
    return output;
}
console.log(multiply(1,10));

function print(name,count){
    if(typeof(count) =="undefined"){
        count=1;
    }
    console.log(`${name}이/가 ${count}개 있습니다.`);
}

print("사과"); //  매개변수 넣지 않으면 undefined 개 가 뜸.
print("사과",3);

//콜백 함수 : 매게변수 안에 함수가 들어가는 형태 

function callTenTimes(callback){
    for(let i=0;i<=10;i++){
        callback();
    }
}

callTenTimes(function(){
    console.log("함수 호출");  // 익명함수   변수가 함수를 받음.  
})

// 표준 내장 함수 

// 내용이 숫자로 구성된 문자열 에서     문자를 숫자로 parseInt  정수부분만 가져옴. parseFloat 실수부분까지 . 


// 자바스크립트 객체 

let array =['a','b']; //베열

//객체

let object={
    name:'바나나',
    price: 1200,
    색: ["red","blue","pink"],
    method: function(){
        console.log("hello world \n");
        console.log(`${this.name}은 맛있어요. 근데 가격이 ${object.price}원 `) // 왜 굳이 this? 메소드가 포함되어 있는 객체를 지목 , 왜 object.price로 안할까 . 종속적으로 처리하기 위해   메소드 이름이 바뀌면 오류 
    }
};

console.log("이름:"+object.name+"\n가격:"+object.price);
console.log(object);
/*
for(let key in object){
    console.log(`${key}:${object[key]}`)
}
*/
object.method();
// 배열은 인덱스 접근 !! 객체는 속성명 접근 !! 
console.log(' ----------------------------------------------------------------')
/*
let products =[
    {
        name:'바나나',
        price: 1000,
        
    },
    {
        name:'사과',
        price: 1100,
        
    },
    {
        name:'딸기',
        price: 1200,
       
    },
    {
        name:'고구마',
        price: 1300,
        
    }

];

const method=(product)=>{
    console.log(`${product.name}의 ${product.price}원 입니다.`)
}

for(let product of products){
    method(product);
}

*/
// class--------------------class----------------------class

class Product{
    constructor(name,price){
        this.name=name;
        this.price=price;
    }
    print(){
        console.log(`${this.name}의 가격은 ${this.price}원 입니다.`)
    }
}

let products=[
    
    new Product("바나나",1200),
    new Product("사과",1300),
    new Product("딸기",1400),
    new Product("감자",1500),
    new Product("수박",1600),
    new Product("고구마",1700),
    
];

for(let product of products){
    product.print();
}

