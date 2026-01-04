package fcu.iecs.elementalfight.element;

import fcu.iecs.elementalfight.core.Character;
import fcu.iecs.elementalfight.core.Element;
import fcu.iecs.elementalfight.core.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Earth extends Character {
    private static final Logger logger = LogManager.getLogger(Earth.class);

    // Jackson 反序列化需要空建構子
    public Earth() {
    }

    public Earth(String name) {
        super(name, Element.EARTH, 60.0, 10.0, 5, false);
    }

    // 無技能
    @Override
    public void skill(GameState charState, Character op, GameState opState) {
        System.out.println("此元素角色無技能！");
    }
}
