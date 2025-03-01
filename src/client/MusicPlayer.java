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

    }

    // music_number
    // 1 : cowboy_ai
    // 2 : UIUIA UIA UIA
    // 3 : lofi

    public void play(int music_number) {
        switch (music_number) {
            case 0:
                vic = getClass().getResourceAsStream("/resources/sfx/bg/victory_music.wav");
                this.playMusic(vic, true);
                break;
            case 1:
                test = getClass().getResourceAsStream("/resources/sfx/bg/test.wav");
                this.playMusic(test, true);
                break;
            case 2:
                lofi = getClass().getResourceAsStream("/resources/sfx/bg/lofi.wav");
                this.playMusic(lofi, true);
                break;
            default:
                System.out.println("Invalid music number.");
        }
    }

    public void sfx(String sfx_number) {
        switch (sfx_number) {
            case "click":
                click = getClass().getResourceAsStream("/resources/sfx/click.wav");
                this.playSFX(click, false);
                break;
            case "pew":
                pew = getClass().getResourceAsStream("/resources/sfx/pew.wav");
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
}
