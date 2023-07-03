package event;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import asset.AssetManager;

public final class KeyEventListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.VK_W) {

            System.out.println("Key Pressed!");

            AssetManager.sounds.get("audio1").play();

        }

    }

    @Override
    public void keyReleased(KeyEvent event) {

        //

    }

}
