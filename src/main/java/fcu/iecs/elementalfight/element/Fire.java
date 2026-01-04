package fcu.iecs.elementalfight.element;

import fcu.iecs.elementalfight.core.Character;
import fcu.iecs.elementalfight.core.Element;
import fcu.iecs.elementalfight.core.GameState;

public class Fire extends Character {
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
