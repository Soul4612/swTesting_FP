package fcu.iecs.elementalfight.core;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fcu.iecs.elementalfight.element.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

// Jackson 多形反序列化設定
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "element",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Water.class, name = "WATER"),
        @JsonSubTypes.Type(value = Fire.class, name = "FIRE"),
        @JsonSubTypes.Type(value = Wood.class, name = "WOOD"),
        @JsonSubTypes.Type(value = Metal.class, name = "METAL"),
        @JsonSubTypes.Type(value = Earth.class, name = "EARTH")
})
public abstract class Character {
    private static final Logger logger = LogManager.getLogger(Character.class);

    protected String name;
    protected Element element;
    protected double HP;
    protected double atk;
    protected int speed;
    protected boolean hasSkill;
    protected LocalDateTime builtTime;

    // Jackson 反序列化需要空建構子
    public Character() {
    }

    public Character(String name, Element element, double HP, double atk, int speed, boolean hasSkill) {
        this.name = name;
        this.element = element;
        this.HP = HP;
        this.atk = atk;
        this.speed = speed;
        this.hasSkill = hasSkill;
        this.builtTime = LocalDateTime.now();
    }

    // 攻擊
    // 相生相剋
    // 水生木，木生火，火生土，土生金，金生水
    // 水剋火，火剋金，金剋木，木剋土，土剋水
    // 生 *0.8 ，剋 *1.2
    public void attack(GameState charState, Character op, GameState opState) {
        opState.setHp(opState.getHp() - (atk * getMultiplier(this, op)));
        if (opState.getHp() < 0.0) {
            opState.setHp(0.0);
        }
    }

    public static double getMultiplier(Character character, Character op) {
        double multiplier = 1.0;
        if (character.element == Element.WATER) {
            if (op.element == Element.WOOD) multiplier = 0.8;
            if (op.element == Element.FIRE) multiplier = 1.2;
        } else if (character.element == Element.FIRE) {
            if (op.element == Element.EARTH) multiplier = 0.8;
            if (op.element == Element.METAL) multiplier = 1.2;
        } else if (character.element == Element.WOOD) {
            if (op.element == Element.FIRE) multiplier = 0.8;
            if (op.element == Element.EARTH) multiplier = 1.2;
        } else if (character.element == Element.METAL) {
            if (op.element == Element.WATER) multiplier = 0.8;
            if (op.element == Element.WOOD) multiplier = 1.2;
        } else if (character.element == Element.EARTH) {
            if (op.element == Element.METAL) multiplier = 0.8;
            if (op.element == Element.WATER) multiplier = 1.2;
        }
        return multiplier;
    }

    public abstract void skill(GameState charState, Character op, GameState opState);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isHasSkill() {
        return hasSkill;
    }

    public void setHasSkill(boolean hasSkill) {
        this.hasSkill = hasSkill;
    }

    public LocalDateTime getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(LocalDateTime builtTime) {
        this.builtTime = builtTime;
    }
}
