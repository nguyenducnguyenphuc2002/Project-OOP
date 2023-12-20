package audio;

import storage.LoadAudio;

import java.io.File;
<<<<<<< HEAD

import javax.sound.sampled.*;

public class AudioPlayer {

	private Clip clip;

	public AudioPlayer(String fileName) {

		this.clip = LoadAudio.loadClip(fileName);



	}

	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}

	public void stop() {
		if(clip.isRunning()) clip.stop();
	}

	public void close() {
		stop();
		clip.close();
	}

}














=======
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFormat.Encoding;

public class AudioPlayer {
    private final Clip clip;

    public AudioPlayer(String fileName) {
        this.clip = LoadAudio.loadClip(fileName);
    }

    public void play() {
        if (this.clip != null) {
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
>>>>>>> NguyenPhuc
