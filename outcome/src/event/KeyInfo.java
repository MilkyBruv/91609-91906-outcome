package event;

public class KeyInfo {
    
    public static boolean[] pressedKeys = new boolean[Short.MAX_VALUE];

    public static final boolean isKeyPressed(short keyCode) {

        return pressedKeys[keyCode];

    }

}
