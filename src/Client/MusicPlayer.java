package client;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;

    public MusicPlayer() {
    }

    public void play(int music_number) {
        switch (music_number) {
            case 0:
                this.playMusic("resources/victory_music.wav");
                break;
            case 1:
                this.playMusic("resources/test.wav");
        }
    }

    public void playMusic(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Loop the music
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        clip.stop(); // Stop playback
        clip.close(); // Release resources
    }

    // public void setVolume(int volume) {
    // if (clip != null && clip.isOpen()) {
    // FloatControl gainControl = (FloatControl)
    // clip.getControl(FloatControl.Type.MASTER_GAIN);

    // // Clamp volume between 0.0 (mute) and 1.0 (max)
    // volume = (int) Math.max(0.0f, Math.min(1.0f, volume));

    // // Convert linear volume to decibels (logarithmic scale)
    // float dB = (float) (20.0 * Math.log10(volume == 0.0 ? 0.0001 : volume));

    // gainControl.setValue(dB);
    // }
    // }

    // public static void main(String[] args) {

    // }
}
