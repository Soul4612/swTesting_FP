package fcu.iecs.elementalfight;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fcu.iecs.elementalfight.core.Character;
import fcu.iecs.elementalfight.core.GameState;
import fcu.iecs.elementalfight.element.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ElementalFight {
    private static final File file = new File("model/Character.json");
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);
    private static String instruction;
    private static Map<String, Character> characterMap = new HashMap<>();

    static {
        // 支援 LocalDateTime
        mapper.registerModule(new JavaTimeModule());
        // ISO 8601 字串
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // Utility class
    private ElementalFight() {
    }

    private static void load() {
        // 檔案不存在或是空檔案
        if (!file.exists() || file.length() == 0) {
            characterMap = new HashMap<>();
        } else {
            try {
                characterMap = mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                throw new RuntimeException("無法載入資料", e);
            }
        }
    }

    private static void save() {
        if (!file.getParentFile().exists()) {
            // 自動建立 model 資料夾
            file.getParentFile().mkdirs();
        }
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, characterMap);
        } catch (IOException e) {
            throw new RuntimeException("無法儲存分類資料", e);
        }
    }

    public static void start() {
        System.out.println("【五行競技場】開始遊戲！");
        load();

        while (true) {
            instruction = scanner.nextLine();
            if (instruction.equals("exit")) {
                break;
            } else if (instruction.equals("help")) {
                // TODO
            } else if (instruction.equals("create")) {
                // 輸入名稱
                System.out.print("輸入角色名稱: ");
                String name = scanner.nextLine();
                System.out.print("選擇角色屬性(1.水, 2.火, 3.木, 4.金, 5.土): ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> characterMap.put(name, new Water(name));
                    case "2" -> characterMap.put(name, new Fire(name));
                    case "3" -> characterMap.put(name, new Wood(name));
                    case "4" -> characterMap.put(name, new Metal(name));
                    case "5" -> characterMap.put(name, new Earth(name));
                    default -> {
                        System.out.println("無效的選擇！角色創建失敗。");
                        continue;
                    }
                }
                System.out.println("新角色 " + name + " 創建成功！屬性: " + characterMap.get(name).getElement());
            } else if (instruction.startsWith("fight")) {
                String[] instructions = instruction.split(" ");
                if (instructions.length != 3) {
                    System.out.println("指令格式錯誤。");
                    continue;
                }
                if (!characterMap.containsKey(instructions[1])) {
                    System.out.println("角色 " + instructions[1] + "不存在。");
                    continue;
                }
                if (!characterMap.containsKey(instructions[2])) {
                    System.out.println("角色 " + instructions[2] + "不存在。");
                    continue;
                }
                fight(characterMap.get(instructions[1]), characterMap.get(instructions[2]));
            } else if (instruction.equals("showplayerlist")) {
                for (Character c : characterMap.values()) {
                    System.out.println(c.getName() + " - " + c.getElement());
                }
            } else {
                System.out.println("無效的指令 " + instruction);
            }
        }

        save();
        System.out.println("【五行競技場】結束遊戲。");
    }

    private static void fight(Character player1, Character player2) {
        // 決定攻守方
        Character attacker = player1;
        Character defender = player2;
        // 決定攻方
        if (player2.getSpeed() > player1.getSpeed()) {
            attacker = player2;
        } else if (player1.getSpeed() == player2.getSpeed()) {
            if (player2.getBuiltTime().isBefore(player1.getBuiltTime())) {
                attacker = player2;
            } else if (player1.getBuiltTime().isEqual(player2.getBuiltTime())
                    && player2.getName().compareTo(player1.getName()) < 0) {
                attacker = player2;
            }
        }
        // 決定守方
        if (attacker != player1) {
            defender = player1;
        }
        // 複製一份狀態
        GameState atkState = new GameState(attacker.getHP(), attacker.isHasSkill());
        GameState defState = new GameState(defender.getHP(), defender.isHasSkill());

        System.out.println(attacker.getName() + " 先攻");
        label:
        while (true) {
            // 結束判定
            if (atkState.getHp() <= 0.0 || defState.getHp() <= 0.0) {
                break;
            }

            // 指令輸入
            System.out.print(attacker.getName() + ": ");
            instruction = scanner.nextLine();
            switch (instruction) {
                case "attack":
                    attacker.attack(defender, defState);
                    break;
                case "skill":
                    attacker.skill(atkState, defender, defState);
                    break;
                case "escape":
                    // 逃跑
                    System.out.println(attacker.getName() + " 逃跑了！");
                    // 設為陣亡
                    atkState.setHp(0.0);
                    break label;
                default:
                    System.out.println("無效的指令。");
                    break;
            }

            // 攻守交換
            Character temp = attacker;
            attacker = defender;
            defender = temp;
            GameState tempState = atkState;
            atkState = defState;
            defState = tempState;
        }

        // 結果輸出
        if (atkState.getHp() <= 0.0) {
            System.out.println(defender.getName() + " 獲勝！");
        } else {
            System.out.println(attacker.getName() + " 獲勝！");
        }
    }
}