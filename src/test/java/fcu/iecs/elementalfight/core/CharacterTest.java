package fcu.iecs.elementalfight.core;

import static org.junit.jupiter.api.Assertions.*;
import fcu.iecs.elementalfight.element.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CharacterTest {

    @DisplayName("角色創建屬性值測試")
    @Test
    void createCharacter(){
        Character wa = new Water("Water");
        Character f = new Fire("Fire");
        Character wo = new Wood("Wood");
        Character m = new Metal("Metal");
        Character e = new Earth("Earth");

        assertAll(
                ()-> assertEquals(50.0, wa.getHP()),
                ()-> assertEquals(Element.WATER, wa.getElement()),

                ()-> assertEquals(15.0, f.getAtk()),
                ()-> assertFalse(f.isHasSkill()),

                ()-> assertEquals(45.0, wo.getHP()),
                ()-> assertEquals(8, wo.getSpeed()),

                ()-> assertEquals(50.0, m.getHP()),
                ()-> assertTrue(m.isHasSkill()),

                ()-> assertEquals(10.0, e.getAtk()),
                ()-> assertEquals(5, e.getSpeed())
        );
    }


    @DisplayName("水 測試")
    @Test
    void water() {
        Character waf = new Water("Water");
        Character wawo = new Water("Water");
        Character f = new Fire("Fire");
        Character wo = new Wood("Wood");
        GameState wafState = new GameState(waf.getHP(), waf.isHasSkill());
        GameState wawoState = new GameState(wawo.getHP(), wawo.isHasSkill());
        GameState fState = new GameState(f.getHP(), f.isHasSkill());
        GameState woState = new GameState(wo.getHP(), wo.isHasSkill());

        waf.attack(wafState, f, fState);
        wawo.attack(wawoState, wo, woState);
        assertAll(
                ()-> assertEquals(38.0, fState.getHp()),
                ()-> assertEquals(37.0, woState.getHp())
        );

        waf.attack(wafState, f, fState);
        wawo.attack(wawoState, wo, woState);
        assertAll(
                ()-> assertEquals(26.0, fState.getHp()),
                ()-> assertEquals(29.0, woState.getHp())
        );

        waf.attack(wafState, f, fState);
        wawo.attack(wawoState, wo, woState);
        assertAll(
                ()-> assertEquals(14.0, fState.getHp()),
                ()-> assertEquals(21.0, woState.getHp())
        );

        waf.skill(wafState, f, fState);
        wawo.skill(wawoState, wo, woState);
        assertAll(
                ()-> assertEquals(0.0, fState.getHp()),
                ()-> assertEquals(21.0, woState.getHp()),
                ()-> assertFalse(wafState.isHasSkill()),
                ()-> assertFalse(wawoState.isHasSkill())
        );

        //  測輸出
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        waf.skill(wafState, f, fState);
        assertEquals("技能使用次數已耗盡，無法使用技能！", out.toString().trim());

        System.setOut(originalOut);
    }

    @DisplayName("火 測試")
    @Test
    void fire() {
        Character fm = new Fire("Fire");
        Character fe = new Fire("Fire");
        Character m = new Metal("Metal");
        Character e = new Earth("Earth");
        GameState fmState = new GameState(fm.getHP(), fm.isHasSkill());
        GameState feState = new GameState(fe.getHP(), fe.isHasSkill());
        GameState mState = new GameState(m.getHP(), m.isHasSkill());
        GameState eState = new GameState(e.getHP(), e.isHasSkill());

        fm.attack(fmState, m, mState);
        fe.attack(feState, e, eState);
        assertAll(
                ()-> assertEquals(32.0, mState.getHp()),
                ()-> assertEquals(48.0, eState.getHp())
        );

        fm.attack(fmState, m, mState);
        fe.attack(feState, e, eState);
        assertAll(
                ()-> assertEquals(14.0, mState.getHp()),
                ()-> assertEquals(36.0, eState.getHp())
        );

        fm.attack(fmState, m, mState);
        fe.attack(feState, e, eState);
        assertAll(
                ()-> assertEquals(0.0, mState.getHp()),
                ()-> assertEquals(24.0, eState.getHp())
        );

        assertFalse(fmState.isHasSkill());
        assertFalse(feState.isHasSkill());
    }

    @DisplayName("金 測試")
    @Test
    void metal() {
        Character mwo = new Metal("Metal");
        Character mwa = new Metal("Metal");
        Character wo = new Wood("Wood");
        Character wa = new Water("Water");
        GameState mwoState = new GameState(mwo.getHP(), mwo.isHasSkill());
        GameState mwaState = new GameState(mwa.getHP(), mwa.isHasSkill());
        GameState woState = new GameState(wo.getHP(), wo.isHasSkill());
        GameState waState = new GameState(wa.getHP(), wa.isHasSkill());

        mwo.attack(mwoState, wo, woState);
        mwa.attack(mwaState, wa, waState);
        assertAll(
                ()-> assertEquals(33.0, woState.getHp()),
                ()-> assertEquals(42.0, waState.getHp())
        );

        mwo.attack(mwoState, wo, woState);
        mwa.attack(mwaState, wa, waState);
        assertAll(
                ()-> assertEquals(21.0, woState.getHp()),
                ()-> assertEquals(34.0, waState.getHp())
        );

        mwo.attack(mwoState, wo, woState);
        mwa.attack(mwaState, wa, waState);
        assertAll(
                ()-> assertEquals(9.0, woState.getHp()),
                ()-> assertEquals(26.0, waState.getHp())
        );

        mwo.skill(mwoState, wo, woState);
        mwa.skill(mwaState, wa, waState);
        assertAll(
                ()-> assertEquals(0.0, woState.getHp()),
                ()-> assertEquals(10.0, waState.getHp()),
                ()-> assertFalse(mwoState.isHasSkill()),
                ()-> assertFalse(mwaState.isHasSkill())
        );

        //  測輸出
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        mwo.skill(mwoState, wo, woState);
        assertEquals("技能使用次數已耗盡，無法使用技能！", out.toString().trim());

        System.setOut(originalOut);
    }

    @DisplayName("木 測試")
    @Test
    void wood() {
        Character we = new Wood("Wood");
        Character wf = new Wood("Wood");
        Character e = new Earth("Earth");
        Character f = new Fire("Fire");
        GameState weState = new GameState(we.getHP(), we.isHasSkill());
        GameState wfState = new GameState(wf.getHP(), wf.isHasSkill());
        GameState eState = new GameState(e.getHP(), e.isHasSkill());
        GameState fState = new GameState(f.getHP(), f.isHasSkill());

        we.attack(weState, e, eState);
        wf.attack(wfState, f, fState);
        assertAll(
                ()-> assertEquals(48.0, eState.getHp()),
                ()-> assertEquals(42.0, fState.getHp())
        );

        we.attack(weState, e, eState);
        wf.attack(wfState, f, fState);
        assertAll(
                ()-> assertEquals(36.0, eState.getHp()),
                ()-> assertEquals(34.0, fState.getHp())
        );

        we.attack(weState, e, eState);
        wf.attack(wfState, f, fState);
        assertAll(
                ()-> assertEquals(24.0, eState.getHp()),
                ()-> assertEquals(26.0, fState.getHp())
        );

        e.attack(eState, we, weState);

        f.attack(fState, wf, wfState);
        f.attack(fState, wf, wfState);
        assertAll(
                ()-> assertEquals(35.0, weState.getHp()),
                ()-> assertEquals(15.0, wfState.getHp())
        );


        we.skill(weState, e, eState);
        wf.skill(wfState, f, fState);
        assertAll(
                ()-> assertEquals(45.0, weState.getHp()),
                ()-> assertEquals(35.0, wfState.getHp()),
                ()-> assertFalse(weState.isHasSkill()),
                ()-> assertFalse(wfState.isHasSkill())
        );

        //  測輸出
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        we.skill(weState, e, eState);
        assertEquals("技能使用次數已耗盡，無法使用技能！", out.toString().trim());

        System.setOut(originalOut);
    }

    @DisplayName("土 測試")
    @Test
    void earth() {
        Character ew = new Earth("Earth");
        Character em = new Earth("Earth");
        Character w = new Water("Water");
        Character m = new Metal("Metal");
        GameState ewState = new GameState(ew.getHP(), ew.isHasSkill());
        GameState emState = new GameState(em.getHP(), em.isHasSkill());
        GameState wState = new GameState(w.getHP(), w.isHasSkill());
        GameState mState = new GameState(m.getHP(), m.isHasSkill());

        ew.attack(ewState, w, wState);
        em.attack(emState, m, mState);
        assertAll(
                ()-> assertEquals(38.0, wState.getHp()),
                ()-> assertEquals(42.0, mState.getHp())
        );

        ew.attack(ewState, w, wState);
        em.attack(emState, m, mState);
        assertAll(
                ()-> assertEquals(26.0, wState.getHp()),
                ()-> assertEquals(34.0, mState.getHp())
        );

        ew.attack(ewState, w, wState);
        em.attack(emState, m, mState);
        assertAll(
                ()-> assertEquals(14.0, wState.getHp()),
                ()-> assertEquals(26.0, mState.getHp())
        );

        assertFalse(ewState.isHasSkill());
        assertFalse(emState.isHasSkill());
    }
}