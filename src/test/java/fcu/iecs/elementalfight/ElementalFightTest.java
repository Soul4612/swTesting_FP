package fcu.iecs.elementalfight;

import fcu.iecs.elementalfight.core.GameState;
import fcu.iecs.elementalfight.element.*;
import org.junit.jupiter.api.Test;
import fcu.iecs.elementalfight.core.Character;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ElementalFightTest {

    @Test
    void testHelpCommand() {
        String input = "help\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ElementalFight.start();

        String output = out.toString();
        assertTrue(output.contains("指令說明"));
        assertTrue(output.contains("【create】"));
        assertTrue(output.contains("【fight"));
    }

    @Test
    void testAttack() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Character Woody = new Wood("Woody");
        Character Aqua = new Water("Aqua");
        GameState AquaState = new GameState(Aqua.getHP(), Aqua.isHasSkill());

        Woody.attack(Aqua, AquaState);

        String output = out.toString();
        assertTrue(output.contains("Woody 攻擊了 Aqua，造成了 10.0 點傷害。"));
        assertTrue(output.contains("Aqua 剩餘血量 40.0"));
    }

    @Test
    void testSkill() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Character King = new Metal("King");
        Character Ash = new Earth("Ash");
        GameState KingState = new GameState(King.getHP(), King.isHasSkill());
        GameState AquaState = new GameState(Ash.getHP(), Ash.isHasSkill());

        King.skill(KingState, Ash, AquaState);

        String output = out.toString();
        assertTrue(output.contains("King 使用了技能【連擊】"));
        assertTrue(output.contains("King 攻擊了 Ash，造成了 10.0 點傷害。"));
        assertTrue(output.contains("Ash 剩餘血量 50.0"));
        assertTrue(output.contains("King 攻擊了 Ash，造成了 10.0 點傷害。"));
        assertTrue(output.contains("Ash 剩餘血量 40.0"));
    }

//    @Test
//    void testCreate() {
//        String input = "create\nLynx\n4create\nLynx\nexit\n";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        ElementalFight.start();
//
//        String output = out.toString();
//        assertTrue(output.contains("輸入角色名稱: "));
//        assertTrue(output.contains("選擇角色屬性(1.水, 2.火, 3.木, 4.金, 5.土): "));
//        assertTrue(output.contains("新角色 Lynx 創建成功！屬性: METAL"));
////        assertTrue(output.contains("角色已存在！"));
//    }
}