package audio;

import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private static HashMap<String, Clip> clips;
    private static AudioInputStream audioIn;
    private static int framePosition;

    public static void init() {
        clips = new HashMap<String, Clip>();
        framePosition = 0;
    }

    // Sound FX
    public static void loadSound(String file, String name, float volume) {
        if (clips.get(name) != null)
            return;
        Clip clip;
        try {
            audioIn = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResourceAsStream(file));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl)
            clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clips.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + file);
            e.printStackTrace();
        }
    }

    // Background Music
    public static void loadBackgroundMusic(String file, String name, float volume, boolean loop) {
        if (clips.get(name) != null)
            return;
        Clip clip;
        try {
            audioIn = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResourceAsStream(file));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clips.put(name, clip);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + file);
            e.printStackTrace();
        }
    }

    public static void play(String filename) {
        play(filename, framePosition);
    }

    public static void play(String filename, int frames) {
        Clip c = clips.get(filename);
        if (c == null)
            return;
        if (c.isRunning())
            c.stop();
        c.setFramePosition(frames);
        while (!c.isRunning())
            c.start();
    }

    public static void stop(String filename){
        if (clips.get(filename) == null) return;
        if (clips.get(filename).isRunning())
            clips.get(filename).stop();
    }

    public static void resume(String filename){
        if (clips.get(filename) == null) return;
        if (!clips.get(filename).isRunning())
            clips.get(filename).start();
    }

    public static void stopBackgroundMusic(String string) {
        if (clips.get(string) == null) return;
        if (clips.get(string).isRunning())
            clips.get(string).stop();
    }

    public static void setFramePosition(String filename, int frames) {
        if (clips.get(filename) == null) return;
        clips.get(filename).setFramePosition(frames);
    }

    public static void setLoop(String filename) {
        if (clips.get(filename) == null) return;
        clips.get(filename).loop(Clip.LOOP_CONTINUOUSLY);
    } 
}