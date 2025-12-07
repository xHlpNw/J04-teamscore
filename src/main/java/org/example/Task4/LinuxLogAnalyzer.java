package org.example.Task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
1.	Найдите в лог-файле Linux все случаи неудачного входа в систему (authentication failure).
Соберите статистику: сколько было неудачных попыток с каждого хоста по дням.
*/
public class LinuxLogAnalyzer {
    // "... (1) ... 'authentication failure' ... 'rhost=' (2) ..."
    // (1) - дата, (2) - хост
    private static final String REGEXP =
            "([a-zA-Z]+\\s\\d{2}).*authentication failure.*rhost=([0-9-a-zA-Z.]+).*";

    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(
                    Paths.get("src/main/java/org/example/Task4/logs/Linux_2k.log")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pattern pattern = Pattern.compile(REGEXP);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "MMM dd yyyy", Locale.ENGLISH
        );

        // дата -> (хост -> количество неудачных попыток)
        HashMap<LocalDate, HashMap<String, Integer>> stats = new HashMap<>();

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String dateStr = matcher.group(1) + " 2025";
                String host = matcher.group(2);
                LocalDate date;

                try { // В файле встречается некорректная дата - Jan 00 - она игнорируется
                    date = LocalDate.parse(dateStr, formatter);
                } catch (DateTimeParseException e) {
                    date = null;
                }

                if (date != null) {
                    stats.computeIfAbsent(date, k -> new HashMap<>());
                    Map<String, Integer> hostCounts = stats.get(date);
                    hostCounts.put(host, hostCounts.getOrDefault(host, 0) + 1);
                }
            }
        }

        for (LocalDate date : stats.keySet()) {
            System.out.printf("Дата: %s\n", date);
            Map<String, Integer> hostCounts = stats.get(date);
            for (String host : hostCounts.keySet()) {
                System.out.printf("  Хост: %s - %s неудачных попыток входа в систему\n",
                        host, hostCounts.get(host));
            }
        }
    }
}