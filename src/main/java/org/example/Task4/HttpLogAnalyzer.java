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
3.	Найдите в логе http-запросов GET-запросы изображений
(в пути к файлу есть /image, например, «/shuttle/technology/images/apu_mods-small.gif»).
Учитывайте только успешные запросы (статус 200). Сохраните доменное имя и путь к файлу.
*/
public class HttpLogAnalyzer {
    private static final String REGEXP =
            "^(\\S+).*\"GET ([^\"]*/image[^\"]*) HTTP/[0-9.]+\" 200.*$";

    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(
                    Paths.get("src/main/java/org/example/Task4/logs/access.log.20171202")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pattern pattern = Pattern.compile(REGEXP);

        // domain name -> file paths
        HashMap<String, List<String>> filesByDomain = new HashMap<>();

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String domain = matcher.group(1);
                String filePath = matcher.group(2);

                filesByDomain
                        .computeIfAbsent(domain, d -> new ArrayList<>())
                        .add(filePath);
            }
        }

        System.out.println("Файлы изображений запрашиваемые доменными именами:");
        for (String domain : filesByDomain.keySet()) {
            System.out.println(domain + ":");
            for (String filePath : filesByDomain.get(domain)) {
                System.out.println("\t" + filePath);
            }
        }
    }
}
