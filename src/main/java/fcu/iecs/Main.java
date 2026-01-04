package fcu.iecs;

import fcu.iecs.elementalfight.ElementalFight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Scanner scanner = new Scanner(System.in);
    private static String instruction;
    private static String msg;
    private static String[] gameList = {"五行競技場"};

    public static void main(String[] args) {
        msg = "程式開始執行。";
        System.out.println(msg);
        logger.info(msg);

        while (true) {
            msg = "";
            System.out.println("請選擇遊戲。(輸入\"exit\"退出執行。)");
            for (int i = 0; i < gameList.length; i++) {
                System.out.println(i + 1 + ". " + gameList[i]);
            }
            instruction = scanner.nextLine();
            if (instruction.equals("exit")) {
                msg = "程式結束執行。";
                System.out.println(msg);
                logger.info(msg);
                break;
            } else if (instruction.equals("1")) {
                ElementalFight.start();
            } else {
                System.out.println("無效的指令。");
            }
        }
    }
}