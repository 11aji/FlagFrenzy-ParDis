import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip;

    public SoundEffect(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);  // rewind to the beginning
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.setFramePosition(0);  // rewind to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
