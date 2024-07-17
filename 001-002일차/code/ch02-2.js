function rollDice(){
    return Math.floor(Math.random()*6)+1; 
}
//console.log(rollDice());

let game=()=>{

    let player ;
    let com;

    let Pcount=0;
    let Ccount=0;
    let Totalcount=0;
    console.log("가위바위보 게임을 시작해보아요")
    console.log("----------------------------------------------------------")
    for (let i=0;i<3;i++){
         player=rollDice();
         com=rollDice();

         if(player>com){
            Pcount +=1;
            Totalcount +=1;
            console.log(`${Totalcount}라운드 \n플레이어:${player},컴퓨터:${com}`)
            console.log('플레이어가 승리했습니다')
            console.log("----------------------------------------------------------")
            if(Totalcount==3){
                console.log(`스코어: 플레이어${Pcount} :컴퓨터 ${Ccount} \n최종결과 :플레이어 승리`);
                
            }
         }else{
            Ccount +=1;
            Totalcount+=1;
            console.log(`${Totalcount}라운드 \n플레이어:${player},컴퓨터:${com}`)
            console.log('컴퓨터가 승리했습니다')
            console.log("----------------------------------------------------------")
            if(Totalcount==3){
                console.log(`스코어: 플레이어${Pcount} :컴퓨터 ${Ccount} \n최종결과 :컴퓨터 승리`);
                
            }
         }
        }
    
}



game();