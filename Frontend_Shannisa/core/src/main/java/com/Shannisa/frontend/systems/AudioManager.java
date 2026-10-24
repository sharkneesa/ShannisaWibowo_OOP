package com.Shannisa.frontend.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {

    private static AudioManager instance;

    private Map<String, Sound> soundMap;
    private Map<String, Music> musicMap;

    private AudioManager() {
        soundMap = new HashMap<>();
        musicMap = new HashMap<>();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }

        return instance;
    }

    public Sound loadSound(String key, String filename) {

        if (!soundMap.containsKey(key)) {

            if (Gdx.files != null &&
                Gdx.files.internal(filename).exists()) {

                Sound sound =
                    Gdx.audio.newSound(
                        Gdx.files.internal(filename)
                    );

                soundMap.put(key, sound);

            } else {
                System.out.println(
                    "Sound file not found: " + filename
                );

                return null;
            }
        }

        return soundMap.get(key);
    }

    public Music loadMusic(String key, String filename) {

        if (!musicMap.containsKey(key)) {

            if (Gdx.files != null &&
                Gdx.files.internal(filename).exists()) {

                Music music =
                    Gdx.audio.newMusic(
                        Gdx.files.internal(filename)
                    );

                musicMap.put(key, music);

            } else {
                System.out.println(
                    "Music file not found: " + filename
                );

                return null;
            }
        }

        return musicMap.get(key);
    }

    public void init() {

        // Background music
        loadMusic("bgm", "audio/bgm.ogg");

        // Sound effects
        loadSound("shoot", "audio/shoot.mp3");
        loadSound("echo", "audio/echo.mp3");
        loadSound("chime", "audio/chime.mp3");
    }

    public void playSound(String key) {

        Sound sound = soundMap.get(key);

        if (sound != null) {
            sound.play();
        }
    }

    public void playMusic(String key) {

        Music music = musicMap.get(key);

        if (music != null) {
            music.setLooping(true);
            music.play();
        }
    }

    public void dispose() {

        for (Sound sound : soundMap.values()) {
            sound.dispose();
        }

        for (Music music : musicMap.values()) {
            music.dispose();
        }

        soundMap.clear();
        musicMap.clear();
    }

}
