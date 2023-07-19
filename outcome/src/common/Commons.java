package common;

public abstract class Commons {
    
    public static final boolean inRange(int value, int bottom, int top) {

        if (value >= bottom && value <= top) {

            return true;

        }

        return false;

    }

}
