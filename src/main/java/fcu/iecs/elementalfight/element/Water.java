package fcu.iecs.elementalfight.element;

import fcu.iecs.elementalfight.core.Character;
import fcu.iecs.elementalfight.core.Element;
import fcu.iecs.elementalfight.core.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Water extends Character {
    private static final Logger logger = LogManager.getLogger(Water.class);

    // Jackson 反序列化需要空建構子
    public Water() {
    }

    public Water(String name) {
        super(name, Element.WATER, 50.0, 10.0, 20, true);
    }

    // 若對方血量低於等於 30% 則斬殺
    @Override
    public void skill(GameState charState, Character op, GameState opState) {
        if (!charState.isHasSkill()) {
            System.out.println("技能使用次數已耗盡，無法使用技能！");
        } else {
            // 已使用技能
            charState.setHasSkill(false);
            // 若對方血量低於等於 30% 則斬殺
            if (opState.getHp() / op.getHP() <= 0.3) {
                opState.setHp(0.0);
            } else {
                System.out.println("對手血量未到斬殺線！Miss!");
            }
        }
    }
}
