import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  https://contest.yandex.ru/contest/17160/problems/
 *  В сервисе Киношечка зарегистрировано n пользователей. Все пользователи, за исключением двух, в последние два месяца
 *  посещали сайт. Нужно определить id пользователей, которые сайт не посещали.
 *
 * Формат ввода
 * Первая строка содержит число n — количество зарегистрированных пользователей. Это целое число в диапазоне от 2 до 106.
 * Во второй строке через пробел заданы различные n - 2 целых числа. Каждое из них не превосходит n и больше нуля.
 *
 * Формат вывода
 * Нужно в одной строке вывести по возрастанию два пропущенных числа, разделённые пробелом.
 *
 * Пример
 * Ввод 	        Вывод
 *
 * 7
 * 6 4 1 2 3        5 7
 */

public class UnvisitedIdsFinderV1Demo {
    public static void main(String[] args) throws IOException {
        UnvisitedIdsFinderV1 idsFinder = new UnvisitedIdsFinderV1();
        idsFinder.getUserData(args);
        idsFinder.printUnvisitedUserIds(idsFinder.findUnvisitedUserIds());
    }
}

class UnvisitedIdsFinderV1 {

    public static final String N_INPUT_TEXT = "Enter n: ";
    public static final String NUMBERS_INPUT_FMT = "Enter [%d] numbers: ";

    private Integer n;
    private List<Integer> ids = new ArrayList<>();

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public void setIds(String inputIds) {
        try {
            setIds(Arrays.stream(inputIds.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        } catch (NumberFormatException e) {}
    }

    public void printUnvisitedUserIds(List<Integer> ids) {
        for (Integer i : ids) {
            System.out.print(i + " ");
        }
    }

    public List<Integer> findUnvisitedUserIds() {
        List<Integer> unvisited = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (unvisited.size() == 2) break;
            if (!ids.contains(i)) {
                unvisited.add(i);
            }
        }
        return unvisited;
    }

    public void getUserData(String[] args) throws IOException {
        if (args.length == 1 && "input.txt".equals(args[0])) {
            try (FileReader reader = new FileReader(args[0]);
                 BufferedReader br = new BufferedReader(reader)) {

                String line;
                int lineCnt = 0;
                while ((line = br.readLine()) != null) {
                    if (lineCnt == 0) {
                        setN(Integer.parseInt(line));
                        lineCnt++;
                        continue;
                    }
                    setIds(line);
                    break;
                }
            }
        } else if (args.length == 0){
            while (getN() == null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                    int inputN = Integer.parseInt(br.readLine());
                    while (inputN < 2 || inputN > Math.pow(10, 6)) {
                        inputN = Integer.parseInt(br.readLine());
                    }
                    setN(inputN);
                    do {
                        setIds(br.readLine());
                    } while (getIds().size() != (inputN - 2));
                }
            }
        }
    }
}
