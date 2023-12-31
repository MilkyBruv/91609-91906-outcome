package entity.player;

import java.io.IOException;

import gfx.ImageResource;

public abstract class PlayerAnimations {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final float RUNNING_FRAME_INTERVAL = 20.0f;
	public static final int IDLE_FRAME_INTERVAL = 100;

	private static ImageResource spritesheet = null;

	public static final ImageResource[][] idleFrames = new ImageResource[2][4];
	public static final ImageResource[][] runningFrames = new ImageResource[2][4];

	public static final void init() {

		try {

			spritesheet = new ImageResource("player.png");

		} catch (IOException e) {

			e.printStackTrace();

		}

		// Assign all frames from player spritesheet
		idleFrames[RIGHT][0] = spritesheet.getSubImageByDimensions(0, 0, 32, 32);
		idleFrames[RIGHT][1] = spritesheet.getSubImageByDimensions(1, 0, 32, 32);
		idleFrames[RIGHT][2] = spritesheet.getSubImageByDimensions(2, 0, 32, 32);
		idleFrames[RIGHT][3] = spritesheet.getSubImageByDimensions(3, 0, 32, 32);

		idleFrames[LEFT][0] = spritesheet.getSubImageByDimensions(0, 1, 32, 32);
		idleFrames[LEFT][1] = spritesheet.getSubImageByDimensions(1, 1, 32, 32);
		idleFrames[LEFT][2] = spritesheet.getSubImageByDimensions(2, 1, 32, 32);
		idleFrames[LEFT][3] = spritesheet.getSubImageByDimensions(3, 1, 32, 32);

		runningFrames[RIGHT][0] = spritesheet.getSubImageByDimensions(4, 0, 32, 32);
		runningFrames[RIGHT][1] = spritesheet.getSubImageByDimensions(5, 0, 32, 32);
		runningFrames[RIGHT][2] = spritesheet.getSubImageByDimensions(6, 0, 32, 32);
		runningFrames[RIGHT][3] = spritesheet.getSubImageByDimensions(7, 0, 32, 32);

		runningFrames[LEFT][0] = spritesheet.getSubImageByDimensions(4, 1, 32, 32);
		runningFrames[LEFT][1] = spritesheet.getSubImageByDimensions(5, 1, 32, 32);
		runningFrames[LEFT][2] = spritesheet.getSubImageByDimensions(6, 1, 32, 32);
		runningFrames[LEFT][3] = spritesheet.getSubImageByDimensions(7, 1, 32, 32);

	}

}