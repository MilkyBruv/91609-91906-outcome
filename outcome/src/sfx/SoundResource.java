package sfx;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.Main;

public final class SoundResource {

    private Clip clip;

    public SoundResource(String fileName) throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        this.clip = AudioSystem.getClip();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("../res/" + fileName));
        clip.open(audioInputStream);

    }
    
}
