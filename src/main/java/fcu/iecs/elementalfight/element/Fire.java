package fcu.iecs.elementalfight.element;

import fcu.iecs.elementalfight.core.Character;
import fcu.iecs.elementalfight.core.Element;
import fcu.iecs.elementalfight.core.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Fire extends Character {
    private static final Logger logger = LogManager.getLogger(Fire.class);

    // Jackson 反序列化需要空建構子
    public Fire() {
    }

    public Fire(String name) {
        super(name, Element.FIRE, 50.0, 15.0, 5, false);
    }

    // 無技能
    @Override
    public void skill(GameState charState, Character op, GameState opState) {
        System.out.println("此元素角色無技能！");
    }
}
