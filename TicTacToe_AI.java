// 6 7 8
// 3 4 5
// 0 1 2
import java.util.ArrayList;
public class TicTacToe_AI{
    
    
   
    
    public int nextmove(boolean[][] opponent, boolean[][] mine, int count){
        if(count==0) return 2*StdRandom.uniform(5);
        int z = obviousmove(opponent, mine);
        if (z!=-1) return z;
        z = obviousmove(mine, opponent);
        if (z!=-1) return z;
        
        
        ArrayList<Integer> mynums = new ArrayList<Integer>(); 
        ArrayList<Integer> hisnums = new ArrayList<Integer>();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if (mine[i][j]) mynums.add(i+3*j);
                if (opponent[i][j]) hisnums.add(i+3*j);
            }
        }
        
        
        switch(count){
            case 1: if (!opponent[1][1]) return 4;
            return (2*StdRandom.uniform(2)+6*StdRandom.uniform(2));
            
            
            case 2: 
            if(!mine[1][1] && !opponent[1][1]){
                if(mynums.get(0) == 8-hisnums.get(0)) return (mynums.get(0)+2-2*mynums.get(0)%3); //after this take the last corner
                if(hisnums.get(0)%2 == 1) return 4; //after take the corner not adjacent to his odd one
                return 8-mynums.get(0); //automatically handled
            }
            
            if(mynums.get(0)==4){
                if(hisnums.get(0)%2==0) return (8-hisnums.get(0));//after take the corner not adjacent to his odd one
                if(hisnums.get(0)<4) return 8; //auto
                else return 0; //auto
            }
            
            else return (8-mynums.get(0)); //auto
            
                
            case 3:
            if(mynums.get(0)==4){
                if((hisnums.get(0)%2 == hisnums.get(1)%2) &&  (hisnums.get(1)%2==1)){
                    return (2*StdRandom.uniform(2)+6*StdRandom.uniform(2));//auto
                }
                if((hisnums.get(0)%2 == hisnums.get(1)%2) && (hisnums.get(1)%2 ==0)){
                    return ((2*StdRandom.uniform(4))+1);//auto
                }
                if(hisnums.get(0)%2 == 0) return (8-hisnums.get(0));
                else return (8-hisnums.get(1));
            }
                
            else return (mynums.get(0)+2-2*mynums.get(0)%3); //auto
            
            case 4:
            if(opponent[0][1]){
                if(opponent[2][0]) return 8;
                return 2;
            }
            if(opponent[2][1]){ 
                if(opponent[0][0]) return 6;
                return 0;
            }
            if(opponent[1][0]){
                if(opponent[0][2]) return 8;
                return 6;
            }
            if(opponent[1][2]){
                if(opponent[0][0]) return 2;
                return 0;
            }
            if(!opponent[0][0] && !mine[0][0]) return 0;
            if(!opponent[2][0] && !mine[2][0]) return 2;
            if(!opponent[0][2] && !mine[0][2]) return 6;
            return 8;
            default:
            int i = StdRandom.uniform(3);
            int j = StdRandom.uniform(3);
            while(opponent[i][j] || mine[i][j]){
                i = StdRandom.uniform(3);
                j = StdRandom.uniform(3);
                }
            return (i+3*j);
            }
        }
    //Run this the usual way to see if there are obvious wins
    //Flip opponent and mine to see how to avoid an obvious loss
    private int obviousmove(boolean[][] opponent, boolean[][] mine){
        //check horizontal and vertical 
        for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(!opponent[i][j] && 
                       ((mine[(i+1)%3][j] && mine[(i+2)%3][j]) ||
                        (mine[i][(j+1)%3] && mine[i][(j+2)%3])))return (i+3*j);
                }
         }
         //check diagonal
         for(int i=0; i<3; i++){
             if(!opponent[i][i] && mine[(i+1)%3][(i+1)%3] && 
                mine[(i+2)%3][(i+2)%3]) return 4*i;
             if(!opponent[i][2-i] && mine[(i+1)%3][(4-i)%3] 
                    && mine[(i+2)%3][(3-i)%3]) return (i+3*(2-i));
         }
        return -1;
    }
    public static void main(String[] args){
    }
}