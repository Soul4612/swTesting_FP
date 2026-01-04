package fcu.iecs.elementalfight.element;

import fcu.iecs.elementalfight.core.Character;
import fcu.iecs.elementalfight.core.Element;
import fcu.iecs.elementalfight.core.GameState;

public class Metal extends Character {
    // Jackson 反序列化需要空建構子
    public Metal() {
    }

    public Metal(String name) {
        super(name, Element.METAL, 50.0, 10.0, 10, true);
    }

    // 攻擊兩次
    @Override
    public void skill(GameState charState, Character op, GameState opState) {
        if (!charState.isHasSkill()) {
            System.out.println("技能使用次數已耗盡，無法使用技能！");
        } else {
            // 已使用技能
            charState.setHasSkill(false);
            System.out.println(name + " 使用了技能【連擊】");
            // 攻擊兩次
            attack(op, opState);
            attack(op, opState);
        }
    }
}
