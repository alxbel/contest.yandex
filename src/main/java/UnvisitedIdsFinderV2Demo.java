import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
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

public class UnvisitedIdsFinderV2Demo {
    public static void main(String[] args) throws IOException {
        UnvisitedIdsFinderV2 idsFinder = new UnvisitedIdsFinderV2();
        idsFinder.getUserData(args);
        idsFinder.printUnvisitedUserIds(idsFinder.findUnvisitedUserIds());
    }
}

class UnvisitedIdsFinderV2 {

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

            Scanner scanner = new Scanner(new File(args[0]));
            int line = 0;
            while (scanner.hasNextLine()) {
                String lineContent = scanner.nextLine();
                if (line == 0) {
                    setN(Integer.parseInt(lineContent));
                    line++;
                    continue;
                }
                setIds(lineContent);
                break;
            }
        } else if (args.length == 0){
            while (getN() == null) {
                try {
                    //System.out.print(UnvisitedIdsFinder.N_INPUT_TEXT);
                    Scanner scanner = new Scanner(System.in);
                    int inputN = scanner.nextInt();
                    while (inputN < 2 || inputN > Math.pow(10, 6)) {
                        //System.out.print(UnvisitedIdsFinder.N_INPUT_TEXT);
                        inputN = scanner.nextInt();
                    }
                    setN(inputN);
                    do {
                        //System.out.printf(UnvisitedIdsFinder.NUMBERS_INPUT_FMT, inputN - 2);
                        scanner = new Scanner(System.in);
                        if (scanner.hasNextLine()) {
                            setIds(scanner.nextLine());
                        }
                    } while (getIds().size() != (inputN - 2));
                } catch (InputMismatchException e) {}
            }
        }
    }
}
