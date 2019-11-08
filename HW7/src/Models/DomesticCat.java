package Models;

public class DomesticCat extends Cat implements IPlayable{
    private String master;
    public boolean isLaying;

    public DomesticCat() {}

    public DomesticCat(String name, String breed, String master) {
        super(name, breed);
        this.master = master;
        isLaying = false;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public void play(boolean isPlaying){
        System.out.println(isPlaying ? "PREPARE FOR OBLIVION!" : "sleepy mode: activated");
    }

    public void layOnSofa() {
        isLaying = !isLaying;
        System.out.println(isLaying ? "purrrrrrr, I'm sleeping, hooman" : "FEED ME");
    }

    public void spoilShoes() {
        System.out.println("Bomb has been planted \uD83D\uDC4C");
    }
}