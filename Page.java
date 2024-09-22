import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random.*;
import javax.swing.JPanel;

public class Page extends JPanel{
    // PagePlayGame(){
    //     setSize(1280,720);
    // }
    private int count = 2;
    private int [] move_x = new int[count];
    private int [] move_y = new int[count];
    private int [] move_status = new int[count];
    // status 0 ไปด้านซ้าย
    // status 1 ไปด้านขวา
    // status 2 ไปด้านบน
    // status 3 ไปด้านล่าง
    void setPosition(int id,int x, int y){
        this.move_x[id] = x;
        // this.move_y[id] = y;
    }
    public Page(){
        new RunThread(this).start();
    }
    void randomPosition(){
        for(int i = 0; i < count; i++){
            if(this.move_x[i]==0&&this.move_y[i]==0){
                int x = (int)(Math.random()*1000);
                int y = (int)(Math.random()*700);
                this.move_x[i] = x;
                this.move_y[i] = 200;
            }else{
                if(this.move_x[i]==1230){
                    move_status[i] = 0;
                }else if(this.move_x[i]==10){
                    move_status[i] = 1;
                }
                // 
                if(this.move_y[i]==710){
                    move_status[i] = 3;
                }else if(this.move_y[i]==10){
                    move_status[i] = 0;
                }
                // 
                if(move_status[i]==0){
                    this.move_x[i] = this.move_x[i] - 1;
                }else{
                    this.move_x[i] = this.move_x[i] + 1;
                }
            }
            repaint();
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        randomPosition();
        chkCollision();
        for(int i = 0; i < count; i++){
            g.drawImage(Toolkit.getDefaultToolkit().getImage("./images/1.png"), this.move_x[i], this.move_y[i],50,50,this);
        }
    }
    void chkCollision(){
        for(int i = 0; i < count; i++){
            for (int j = 0; j < count; j++) {
                if (move_x[i] == move_x[j] || move_y[i] == move_y[j]) {
                    // ถ้าชนกัน สุ่มเปลี่ยนทิศทางของวัตถุทั้งคู่
                    move_status[i] = (int)(Math.random()*4)+1; // สุ่มทิศทางใหม่ให้กับวัตถุ i
                    move_status[j] = (int)(Math.random()*4)+1; // สุ่มทิศทางใหม่ให้กับวัตถุ j
                }
                else{continue;}
            }
            repaint();
        }
    }
}
