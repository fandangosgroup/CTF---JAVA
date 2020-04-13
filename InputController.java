
package ctf;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
public class InputController extends JFrame{
    public char dir;
    public InputController(){
        this.setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                setDir(e.getKeyChar());
                if (getDir() == 'w' || getDir() == 'a' || getDir() == 's' || getDir() == 'd'){
                    System.out.println(getDir());
                }
            }
        });
    }
    public char getDir() {
        return dir;
    }
    public void setDir(char dir) {
        this.dir = dir;
    }
}