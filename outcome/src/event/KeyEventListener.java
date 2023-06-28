package event;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public final class KeyEventListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent event) {
        
        if (event.getKeyCode() == KeyEvent.VK_W) {

            System.out.println("Key Pressed!");

        }

    }



    @Override
    public void keyReleased(KeyEvent event) {
        
        // 

    }
    
}
