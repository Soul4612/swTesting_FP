package fcu.iecs.elementalfight.element;

import fcu.iecs.elementalfight.core.Character;
import fcu.iecs.elementalfight.core.Element;
import fcu.iecs.elementalfight.core.GameState;

public class Wood extends Character {
    // Jackson 反序列化需要空建構子
    public Wood() {
    }

    public Wood(String name) {
        super(name, Element.WOOD, 45.0, 10.0, 8, true);
    }

    // 治癒(HP+20)
    @Override
    public void skill(GameState charState, Character op, GameState opState) {
        if (!charState.isHasSkill()) {
            System.out.println("技能使用次數已耗盡，無法使用技能！");
        } else {
            // 已使用技能
            charState.setHasSkill(false);
            System.out.println(name + " 使用了技能【治癒】");
            // 治癒(HP+20)
            charState.setHp(charState.getHp() + 20);
            // 不能超過原血量上限
            if (charState.getHp() > getHP()) charState.setHp(getHP());
            System.out.println(name + " 剩餘血量 " + charState.getHp());
        }
    }
}
