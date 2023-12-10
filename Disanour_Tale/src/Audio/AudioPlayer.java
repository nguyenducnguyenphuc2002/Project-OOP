package Audio;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFormat.Encoding;

public class AudioPlayer {
    private Clip clip;

    public AudioPlayer(File file) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            this.clip = AudioSystem.getClip();
            this.clip.open(dais);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void play() {
        if (this.clip != null) {
            this.stop();
            this.clip.setFramePosition(0);
            this.clip.start();
        }
    }

    public void stop() {
        if (this.clip.isRunning()) {
            this.clip.stop();
        }

    }

    public void close() {
        this.stop();
        this.clip.close();
    }
}
