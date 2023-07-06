package event;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import asset.AssetManager;

public final class KeyEventListener implements KeyListener {

    private GameEventManager game;

    public KeyEventListener(GameEventManager game) {

        this.game = game;

    }




    @Override
    public void keyPressed(KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.VK_W) {

            System.out.println("Key Pressed!");

            AssetManager.sounds.get("audio1").play();

        }

        if (event.getKeyCode() == KeyEvent.VK_S) {

            System.out.println("Key Pressed!");

            AssetManager.sounds.get("audio1").pause();

        }

        if (event.getKeyCode() == KeyEvent.VK_A) {

            System.out.println("Key Pressed!");

            AssetManager.sounds.get("audio1").stop();

        }

        if (event.getKeyCode() == KeyEvent.VK_D) {

            System.out.println("Key Pressed!");

            AssetManager.sounds.get("audio1").resume();

        }

    }


    
    @Override
    public void keyReleased(KeyEvent event) {

        //

    }

}
