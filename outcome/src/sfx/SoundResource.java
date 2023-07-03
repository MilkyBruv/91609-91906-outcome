package sfx;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.Main;

public final class SoundResource {

    private SourceDataLine audioObject;

    /**
     * Loads supplied file and creates threaded audio clip
     * 
     * @param fileName Name of audio file (ONLY .wav)
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    public SoundResource(String fileName) throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                Main.class.getResourceAsStream("../res/" + fileName));
        AudioFormat audioFormat = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
        this.audioObject = (SourceDataLine) AudioSystem.getLine(info);
        this.audioObject.open(audioFormat);

    }

    /**
     * Plays audioObject
     */
    public final void play() {

        this.audioObject.start();

    }

    /**
     * Stops audioObject
     */
    public final void stop() {

        this.audioObject.stop();

    }

}
