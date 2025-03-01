package client;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip music, sfx;

    public MusicPlayer() {
    }

    // music_number
    // 1 : cowboy_ai
    // 2 : UIUIA UIA UIA
    // 3 : lofi

    public void play(int music_number) {
        switch (music_number) {
            case 0:
                this.playMusic("resources/sfx/bg/victory_music.wav", true);
                break;
            case 1:
                this.playMusic("resources/sfx/bg/test.wav", true);
                break;
            case 2:
                this.playMusic("resources/sfx/bg/lofi.wav", true);
                break;
            default:
                System.out.println("Invalid music number.");
        }
    }

    public void sfx(String sfx_number) {
        switch (sfx_number) {
            case "click":
                this.playSFX("resources/sfx/click.wav", false);
                break;
            case "pew":
                this.playSFX("resources/sfx/pew.wav", false);
                break;
            default:
                System.out.println("Invalid SFX number.");
        }
    }

    public void playMusic(String filePath, boolean loop) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
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

    public void playSFX(String filePath, boolean loop) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
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
