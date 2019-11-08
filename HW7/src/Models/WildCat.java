package Models;

public class WildCat extends Cat implements IPlayable {
    public WildCat() {}

    public WildCat(String name, String breed) {
        super(name, breed);
    }

    public void play(boolean isPlaying){
        System.out.println(isPlaying ? "jumping around" : "hiding");
    }

    public void hatePeople() {
        System.out.println("HISSSSSSS!");
    }
}