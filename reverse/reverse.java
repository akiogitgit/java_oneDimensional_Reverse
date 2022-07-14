import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Main
{
    // 右に置いた時に相手の石をひっくり返すメソッド 引数(ArrayList, Array), 返り値(ArrayList)
    public static ArrayList<String> turnOverPlaceToRight(ArrayList<String> boards, String[] stoneInfo){
        // 隣の石が違う色ならひっくり返し続ける。
        for(int i = 2; i < boards.size(); i++){
            if(boards.get(boards.size()-i) != (stoneInfo[0])){
                boards.set(boards.size()-i, stoneInfo[0]);
            } else {
                break;
            }
        }
        return boards;
    }
    
    // 左に置いた時に相手の石をひっくり返すメソッド 引数(ArrayList, Array), 返り値(ArrayList)
    public static ArrayList<String> turnOverPlaceToLeft(ArrayList<String> boards, String[] stoneInfo){
        // 隣の石が違う色ならひっくり返し続ける。
        for(int i = 1; i < boards.size(); i++){
            if(boards.get(i) != stoneInfo[0]){
                boards.set(i, stoneInfo[0]);
            } else {
                break;
            }
        }
        return boards;
    }
        
    public static void main(String[] args) {
        System.out.println("🔵🔴一次元リバース🔴🔵");
        java.util.Scanner scan = new java.util.Scanner(System.in);
        Map<String, String> directionMap = new HashMap<>(){{put("1", "左"); put("2", "右");}}; // 方向をオブジェクトで定義
        
        int maxNumberOfStones;
        while(true){
            System.out.println("石が何個になるまでやりますか");
            try{
                maxNumberOfStones = Integer.parseInt(scan.nextLine());
                break;
            } catch(Exception e) {
                System.out.println("数字を入力して下さい");
             }
         }

        // 盤上の初期値を定義
        // ArrayList<String> boards = new ArrayList<String>(Arrays.asList("🔴","🔴","🔴","🔴","🔵","🔴","🔵","🔴","🔴"));
        ArrayList<String> boards = new ArrayList<String>(Arrays.asList("🔴","🔵"));
        
        // 盤上の数がmaxNumberofStonesになるまで繰り返す
        while(boards.size() < maxNumberOfStones){
            
            // ~~~~~~~~~~~~~~~ 自分のターン ~~~~~~~~~~~~~~~
            String myStone[] = {"🔵",""}; // 自分の石の色と置く方向を格納
            // 盤上にそれぞれの色がある場合、-1以外のインデックスを格納
            int hasSameColorStone[] = {boards.indexOf(myStone[0]), -1};
            
            while(true){
                System.out.println("\n1. (左), 2. (右) どちらに置きますか？");
                System.out.println(boards);
                // 入力した値がnumber以外ならcatchで処理する
                try{
                    int myDirection = Integer.parseInt(scan.nextLine());
                    // 1か2以外の数字は受け付けない
                    if(myDirection == 1 || myDirection == 2){
                        myStone[1]= String.valueOf(myDirection);
                        break;
                    }
                    System.out.println("1, 2 のどちらかを入力して下さい");
                } catch(Exception e) {
                    System.out.println("数字を入力して下さい");
                }
            }
            
            // 右端に置く
            if(myStone[1].equals("2")){
                boards.add(myStone[0]);
                // 盤上に同じ色の石がある場合に、ひっくり返すメソッドを実行
                if(hasSameColorStone[0] != -1){
                    boards = turnOverPlaceToRight(boards, myStone);
                }
            }
            // 左端に置く
            else {
                boards.add(0, myStone[0]);
                // 盤上に同じ色の石がある場合に、ひっくり返すメソッドを実行
                if(hasSameColorStone[0] != -1){
                    boards = turnOverPlaceToLeft(boards, myStone);
                }
            }
            
            // enemyStone[1]="1" => "左", enemyStone[1]="2" => "右" 
            System.out.println("\nあなたは" + directionMap.get(myStone[1]) + "に置きました");
            System.out.println(boards);
            
            
            // ~~~~~~~~~~~~~~~ 相手のターン ~~~~~~~~~~~~~~~
            if(boards.size() >= maxNumberOfStones){
                break;
            }
            String[] enemyStone = {"🔴",""}; // 相手の色と置く方向を格納
            enemyStone[1] = String.valueOf(new java.util.Random().nextInt(2)+1); // 1か2
            hasSameColorStone[1] = boards.indexOf(enemyStone[0]); // 🔴がないと-1
            
            // 右端に置く
            if(enemyStone[1].equals("2")){
                boards.add(enemyStone[0]);
                // 盤上に同じ色の石がある場合に、ひっくり返すメソッドを実行
                if(hasSameColorStone[1] != -1){
                    boards = turnOverPlaceToRight(boards, enemyStone);
                }
            } 
            
            // 左端に置く
            else {
                boards.add(0, enemyStone[0]);
                // 盤上に同じ色の石がある場合に、ひっくり返すメソッドを実行
                if(hasSameColorStone[1] != -1){
                    boards = turnOverPlaceToLeft(boards, enemyStone);
                }
            }
            
            // enemyStone[1]="1" => "左", enemyStone[1]="2" => "右" 
            System.out.println("\n相手は" + directionMap.get(enemyStone[1]) + "に置きました");
            System.out.println(boards);
        }
        
        // ~~~~~~~~~~~~~~~ 結果発表 ~~~~~~~~~~~~~~~
        // 自分と相手の石の数
        int myScore = Collections.frequency(boards, "🔵");
        int enemyScore = Collections.frequency(boards, "🔴");
        String message = "あなたの勝ち";
        
        if(enemyScore > myScore)       message = "あなたの負け";
        else if(enemyScore == myScore) message = "引き分け";
        
        System.out.println("\n--------- 結果 --------");
        System.out.println(myScore + " 対 " + enemyScore + "で" + message + "！");
    }
}
