public class TicTacToe_Visualizer{
    
    static boolean xs_turn = true;
    static int count=0;
    static boolean xcells[][] = new boolean[3][3];
    static boolean ocells[][] = new boolean[3][3];
    static int gamemode = 0;
    static boolean cpugoesfirst;
    
    public static int checkwin(){
        for(int i=0; i<3;i++){
            if(xcells[i][0] && xcells[i][1] && xcells[i][2]) return -1;
            if(xcells[0][i] && xcells[1][i] && xcells[2][i]) return -1;
            if(ocells[i][0] && ocells[i][1] && ocells[i][2]) return 1;
            if(ocells[0][i] && ocells[1][i] && ocells[2][i]) return 1;
        }
        if(xcells[0][0] && xcells[1][1] && xcells[2][2]) return -1;
        if(xcells[0][2] && xcells[1][1] && xcells[2][0]) return -1;
        if(ocells[0][0] && ocells[1][1] && ocells[2][2]) return 1;
        if(ocells[0][2] && ocells[1][1] && ocells[2][0]) return 1;
        return 0;
    }
    
    public static void drawboard(){
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, 512);
        double x[] = {170, 170, 172, 172};
        double y[] = {0, 512, 512, 0};
        StdDraw.filledPolygon(x,y);
        StdDraw.filledPolygon(y,x);
        x[0]=340; x[1]= 340; x[2]= 342; x[3]= 342;
        StdDraw.filledPolygon(x,y);
        StdDraw.filledPolygon(y,x);
        StdDraw.show();
    }
    
    public static void drawmenu()throws InterruptedException {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, 512);
        StdDraw.rectangle(256, 300, 100, 50);
        StdDraw.rectangle(128, 170, 100, 50);
        StdDraw.rectangle(384, 170, 100, 50);
        StdDraw.text(256,300,"Human vs. Human");
        StdDraw.text(128,170,"Computer vs. Human");
        StdDraw.text(384,170,"Human vs. Computer");
        StdDraw.show();
        while(true){
            if(StdDraw.mousePressed()){
               
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                if(156<x && x<356 && 250<y && y<350){
                    gamemode=1;
                    Thread.sleep(200);
                    return;
                }
                if(28<x && x<228 && 120<y && y<220){
                    gamemode=2;
                    cpugoesfirst=true;
                    Thread.sleep(200);
                    return;
                }
                if(284<x && x<484 && 120<y && y<220){
                    gamemode=2;
                    cpugoesfirst=false;
                    Thread.sleep(200);
                    return;
                }
            }
        }
    }
    
    
    public static void drawsymbol(int x, int y){
        //0 <= x,y <= 2
        if (xs_turn){
            double xslash[] = {5+170*x,10+170*x, 165+170*x, 160+170*x};
            double yslash[] = {10+170*y,5+170*y,160+170*y,165+170*y};
            StdDraw.filledPolygon(xslash,yslash);
            xslash[0]=165+170*x; 
            xslash[1]=170+170*x; //Mistake but I like how this ended up looking
            xslash[2]=5+170*x;
            xslash[3]=10+170*x;
            StdDraw.filledPolygon(xslash,yslash);
            xcells[x][y] = true;
        }
        else{
            StdDraw.filledCircle(85+170*x, 85+170*y, 70);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(85+170*x, 85+170*y, 65);
            StdDraw.setPenColor(StdDraw.BLACK);
            ocells[x][y] = true;
        }
        xs_turn = !xs_turn;
        count++;
        StdDraw.show();
    }
    
    public static void humangame(){
        drawboard();
        while(true){
            if(StdDraw.mousePressed()){
               
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int cellx;
                int celly;
                
                
                if(x<=170) cellx = 0;
                else if (172<=x && x<= 340) cellx = 1;
                else if (342<= x) cellx = 2;
                else cellx = -1;
                
                if(y<=170) celly = 0;
                else if (172<=y && y<= 340) celly = 1;
                else if (342 <= y) celly = 2;
                else celly = -1;
                if((cellx!= -1) && (celly!=-1)){
                    if(!xcells[cellx][celly] && !ocells[cellx][celly]){
                        drawsymbol(cellx, celly);
                    } 
                }
                int check = checkwin();
                if(check == -1){
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.text(256,0,"Player 1 Wins!");
                    StdDraw.setPenColor(StdDraw.BLACK);
                    break;
                }
                else if(check == 1){
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.text(256,0,"Player 2 Wins!");
                    StdDraw.setPenColor(StdDraw.BLACK);
                    break;
                }
                if (count == 9){
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.text(256,0,"Kat's Game");
                    StdDraw.setPenColor(StdDraw.BLACK);
                    break;
                }
            }
        }
    }
    
    public static void cpugame(){
        drawboard();
        TicTacToe_AI A = new TicTacToe_AI();
        
        if(cpugoesfirst){
            int temp = A.nextmove(ocells, xcells, count);
            drawsymbol(temp%3, (temp - temp%3)/3);
            while(true){
                if(StdDraw.mousePressed()){
               
                    double x = StdDraw.mouseX();
                    double y = StdDraw.mouseY();
                    int cellx;
                    int celly;
                
                
                    if(x<=172) cellx = 0;
                    else if (172<=x && x<= 340) cellx = 1;
                    else cellx = 2;
                
                    if(y<=172) celly = 0;
                    else if (172<=y && y<= 340) celly = 1;
                    else celly = 2;
                    if(!xcells[cellx][celly] && !ocells[cellx][celly]){
                        drawsymbol(cellx, celly);
                        int check = checkwin();
                        if(check == 1){
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.text(256,0,"You Win!");
                            StdDraw.setPenColor(StdDraw.BLACK);
                            break;
                        }
                        temp = A.nextmove(ocells, xcells,count);
                        drawsymbol(temp%3, (temp - temp%3)/3);
                        check = checkwin();
                        if(check == -1){
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.text(256,0,"CPU Wins!");
                            StdDraw.setPenColor(StdDraw.BLACK);
                            break;
                        }
                        if (count == 9){
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.text(256,0,"Kat's Game");
                            StdDraw.setPenColor(StdDraw.BLACK);
                            break;
                        }
                    }
                }
            }
        }
        else{
            while(true){
                if(StdDraw.mousePressed()){
               
                    double x = StdDraw.mouseX();
                    double y = StdDraw.mouseY();
                    int cellx;
                    int celly;
                
                
                    if(x<=172) cellx = 0;
                    else if (172<=x && x<= 340) cellx = 1;
                    else cellx = 2;
                
                    if(y<=172) celly = 0;
                    else if (172<=y && y<= 340) celly = 1;
                    else celly = 2;
                    if(!xcells[cellx][celly] && !ocells[cellx][celly]){
                        drawsymbol(cellx, celly);
                        int check = checkwin();
                        if(check == -1){
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.text(256,0,"You Win!");
                            StdDraw.setPenColor(StdDraw.BLACK);
                            break;
                        }
                        if (count == 9){
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.text(256,0,"Kat's Game");
                            StdDraw.setPenColor(StdDraw.BLACK);
                            break;
                        }
                        int temp = A.nextmove(xcells, ocells,count);
                        drawsymbol(temp%3, (temp - temp%3)/3);
                        check = checkwin();
                        if(check == 1){
                            StdDraw.setPenColor(StdDraw.RED);
                            StdDraw.text(256,0,"CPU Wins!");
                            StdDraw.setPenColor(StdDraw.BLACK);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    
    
    public static void main(String[] args)throws InterruptedException {
        while(true){  
            xs_turn = true;
            count=0;
            xcells = new boolean[3][3];
            ocells = new boolean[3][3];
            gamemode = 0;
            drawmenu();
            if(gamemode==1) humangame();
            if(gamemode==2) cpugame();
            StdDraw.text(256,510,"Reset");
            StdDraw.rectangle(256, 510, 30, 15);
            while(true){
                StdDraw.show(0);
                if(StdDraw.mousePressed()){
                    double x = StdDraw.mouseX();
                    double y = StdDraw.mouseY();
                    if(236<x && x<276 && 495<y && y<525) break;
                }
            }
        }
    }
}
    
    
    