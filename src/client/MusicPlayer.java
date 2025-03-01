package client;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class MusicPlayer {
    private Clip music, sfx;
    private InputStream vic, test, lofi, click, pew;

    public MusicPlayer() {
      loadAudio();
    }

    // music_number
    // 1 : cowboy_ai
    // 2 : UIUIA UIA UIA
    // 3 : lofi

    public void play(int music_number) {
        switch (music_number) {
            case 0:
                this.playMusic(vic, true);
                break;
            case 1:
                this.playMusic(test, true);
                break;
            case 2:
                this.playMusic(lofi, true);
                break;
            default:
                System.out.println("Invalid music number.");
        }
    }

    public void sfx(String sfx_number) {
        switch (sfx_number) {
            case "click":
                this.playSFX(click, false);
                break;
            case "pew":
                this.playSFX(pew, false);
                break;
            default:
                System.out.println("Invalid SFX number.");
        }
    }

    public void playMusic(InputStream musicStream, boolean loop) {
        try {
            BufferedInputStream bufferedStream = new BufferedInputStream(musicStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
            music = AudioSystem.getClip();
            music.open(audioStream);

            if (loop) {
                music.loop(Clip.LOOP_CONTINUOUSLY);
            }

            music.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSFX(InputStream musicStream, boolean loop) {
        try {
            BufferedInputStream bufferedStream = new BufferedInputStream(musicStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
            sfx = AudioSystem.getClip();
            sfx.open(audioStream);
            if (loop) {
                sfx.loop(Clip.LOOP_CONTINUOUSLY);
            }
            sfx.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (music != null && music.isRunning()) {
            music.stop();
            music.close();
        }
    }

    public void stopSFX() {
        if (sfx != null && sfx.isRunning()) {
            sfx.stop();
            sfx.close();
        }
    }

    private void loadAudio() {
        try {
            vic = getClass().getResourceAsStream("/resources/sfx/bg/victory_music.wav");
            test = getClass().getResourceAsStream("/resources/sfx/bg/test.wav");
            lofi = getClass().getResourceAsStream("/resources/sfx/bg/lofi.wav");
            click = getClass().getResourceAsStream("/resources/sfx/click.wav");
            pew = getClass().getResourceAsStream("/resources/sfx/pew.wav");
            System.out.println("[log: Audio loaded successfully]");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load Audio]");
        }
    }
}
