package storage;


import audio.AudioPlayer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class LoadAudio {
    public static String audio = "music/level1-1";
    public static String JUMP = "sfx/jump";
    public static String SCRATCH = "sfx/scratch";

    public static Clip loadClip(String fileName) {
        File file = new File("resources/"+fileName+".wav");
        Clip clip = null;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);

            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(
                            decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public static AudioPlayer loadKill(String fillName){
        return new AudioPlayer(fillName);

    }

    public static AudioPlayer loadbgMusic(String fileName){
        return new AudioPlayer(fileName);
    }
}
