package gamestate;

public class SharedData {
    private int score;
    private int totalMonstersKilled;
    private int sluggerKills;
    private int arachnikKills;
    private int monkeyKills;
    private int heroKills;
    private int coinsCollected;

    public int getTotalMonstersKilled() {
        return totalMonstersKilled;
    }

    public void setTotalMonstersKilled(int totalMonstersKilled) {
        this.totalMonstersKilled = totalMonstersKilled;
    }

    public int getSluggerKills() {
        return sluggerKills;
    }

    public void setSluggerKills(int sluggerKills) {
        this.sluggerKills = sluggerKills;
    }

    public int getArachnikKills() {
        return arachnikKills;
    }

    public void setArachnikKills(int arachnikKills) {
        this.arachnikKills = arachnikKills;
    }

    public int getMonkeyKills() {
        return monkeyKills;
    }

    public void setMonkeyKills(int monkeyKills) {
        this.monkeyKills = monkeyKills;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public void setCoinsCollected(int coinsCollected) {
        this.coinsCollected = coinsCollected;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHeroKills() {
        return heroKills;
    }

    public void setHeroKills(int heroKills) {
        this.heroKills = heroKills;
    }
}