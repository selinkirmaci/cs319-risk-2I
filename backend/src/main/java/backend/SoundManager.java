package backend;

public class SoundManager
{
    private int volume;
    private boolean soundOn;
    private boolean musicOn;
    public SoundManager(int volume,boolean soundOn,boolean musicOn)
    {
        this.musicOn = musicOn;
        this.soundOn = soundOn;
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }
}
