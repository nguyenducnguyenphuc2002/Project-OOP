package audio;

import ui.LoadAudio;

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














