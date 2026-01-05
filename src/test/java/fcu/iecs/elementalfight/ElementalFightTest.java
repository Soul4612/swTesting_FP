package fcu.iecs.elementalfight;

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

}