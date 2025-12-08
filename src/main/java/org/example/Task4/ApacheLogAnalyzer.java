package org.example.Task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
2.	Извлеките из лога Apache сообщения об ошибках, которые не касаются mod_jk
(он не упоминается в тексте сообщения). Если в сообщении указан адрес клиента (пример в строке 580).
*/
public class ApacheLogAnalyzer {
    private static final String REGEXP =
            "^\\[.*?]\\s+\\[error](?!.*mod_jk)(?: \\[client ([0-9.]+)])?\\s+(.*)$";

    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(
                    Paths.get("src/main/java/org/example/Task4/logs/Apache_2k.log")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pattern pattern = Pattern.compile(REGEXP);

        // IP -> error list
        HashMap<String, List<String>> errorsByIp = new HashMap<>();

        List<String> errorsWithoutIp = new ArrayList<>();

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String client = matcher.group(1);
                String errorMessage = matcher.group(2);

                if (client != null) {
                    errorsByIp.computeIfAbsent(client, c -> new ArrayList<>());
                    List<String> clientErrors = errorsByIp.get(client);
                    clientErrors.add(errorMessage);
                } else {
                    errorsWithoutIp.add(errorMessage);
                }
            }
        }

        System.out.println("Ошибки клиентов:");
        for (String client : errorsByIp.keySet()) {
            System.out.println(client + ":");
            for (String errorMessage : errorsByIp.get(client)) {
                System.out.println("\t" + errorMessage);
            }
        }

        System.out.println("\nОшибки без указания клиента:");
        for (String errorMessage : errorsWithoutIp) {
            System.out.println("\t" + errorMessage);
        }
    }
}
