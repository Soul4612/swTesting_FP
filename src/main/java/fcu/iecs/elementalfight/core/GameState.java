package fcu.iecs.elementalfight.core;

public class GameState {
    private double hp;
    private boolean hasSkill;

    public GameState(double hp, boolean hasSkill) {
        this.hp = hp;
        this.hasSkill = hasSkill;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public boolean isHasSkill() {
        return hasSkill;
    }

    public void setHasSkill(boolean hasSkill) {
        this.hasSkill = hasSkill;
    }
}
