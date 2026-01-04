package fcu.iecs.elementalfight;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fcu.iecs.elementalfight.core.Character;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ElementalFight {
    private static final File file = new File("model/Character.json");
    private static ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(ElementalFight.class);
    private static Scanner scanner = new Scanner(System.in);
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

    public static void start() {
        logger.info("【五行競技場】開始遊戲！");
        load();

        while (true) {
            instruction = scanner.nextLine();
            if (instruction.equals("exit")) {
                break;
            } else if (instruction.equals("help")) {
                // TODO
            } else if (instruction.startsWith("create")) {
                // TODO
            } else if (instruction.startsWith("fight")) {
                // TODO
            } else {
                System.out.println("無效的指令。");
            }
        }

        save();
        logger.info("【五行競技場】結束遊戲。");
    }

    private static void load() {
        // 檔案不存在或是空檔案
        if (!file.exists() || file.length() == 0) {
            characterMap = new HashMap<>();
        } else {
            try {
                characterMap = mapper.readValue(file, new TypeReference<Map<String, Character>>() {
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
}