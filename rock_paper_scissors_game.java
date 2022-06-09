public class Main
{
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);

        // 難易度選択
        String inputLevel;
        System.out.println("～～～～～じゃんけん～～～～～");
        while(true){
            System.out.println("難易度を選択して下さい。(1: 易しい, 2: 普通, 3: 難しい)");
            inputLevel = scan.nextLine();
            if(inputLevel.equals("1") || inputLevel.equals("2") ||inputLevel.equals("3")){
                break;
            } else {
                System.out.println("1, 2, 3 の中で入力して下さい\n");
            }
        }
        int level =  Integer.parseInt(inputLevel);
        System.out.println("level = " + level);
        
        
        // じゃんけん
        String[][] type = new String[][]{{"1","グー"},{"2","チョキ"},{"3","パー"}};
        String inputMyNum;
        int myNum;
        int enemyNum;
        String myType;
        String enemyType;
        int[] randomNum ={1, 2, 3, 1, 2, 3};
        
        System.out.println("\nじゃんけん... (1: グー, 2: チョキ, 3: パー)");
        // あいこならループ
        while(true){
            // "1","2","3"でないならループ
            while(true){
                inputMyNum = scan.nextLine();
                if(inputMyNum.equals("1") || inputMyNum.equals("2") || inputMyNum.equals("3")){
                    myNum = Integer.parseInt(inputMyNum);
                    myType = type[myNum-1][1];
                    break;
                } else {
                    System.out.println("1, 2, 3 の中で入力して下さい\n");
                }
            }
            
            // enemyNumを難易度毎にランダムで決める
            if(level != 2){
                int win = myNum + 1;
                int lose = myNum - 1;
                if(win == 4){
                    win = 1;
                }
                if(lose == 0){
                    lose = 3;
                }
                
                // 難易度 易しい
                if(level == 1){
                    randomNum[3] = win;
                    randomNum[4] = win;
                    randomNum[5] = myNum;
                } else {
                    // 難易度 難しい    
                    randomNum[3] = lose;
                    randomNum[4] = lose;
                    randomNum[5] = myNum;
                }
            }
            
            // 相手の出す手をランダムで決める
            int num =  (int) (Math.random() * (randomNum.length));
            enemyNum = randomNum[num];
            enemyType = type[enemyNum-1][1];
            
            System.out.println("\nあなた: " + myType + ", 相手: " + enemyType);
            
            // 自分と相手の手が違うなら、終了
            if(myNum != enemyNum){
                break;
            }else{
                System.out.println("あいこ！\n");
                System.out.println("あいこで... (1: グー, 2: チョキ, 3: パー)");
            }
        }
        
        // 結果
        if((myNum == 1 && enemyNum == 2) || (myNum == 2 && enemyNum == 3) || (myNum == 3 && enemyNum == 1)){
            System.out.println("\nあなたの勝ちだ");
        } else {
            System.out.println("\nおまえの負けだ");
        }
    }
}
