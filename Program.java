import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static boolean isDataOk = false;


    // Главный метод
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in, "ibm866");
        isDataOk = false;
        String data = "";
        int len;
        String surname = "";
        while (!isDataOk) {
            isDataOk = true;
            data = inputString(scanner);
            len = length(data);
            if (len == 6) {
                String[] strs = data.split(" ");
                surname = strs[0];
                String firstName = strs[1];
                String lastName = strs[2];
                LocalDate date = getDate(strs[3]);
                long phone = getPhoneNumer(strs[4]);
                String sex = getSex(strs[5]);
            }
        }
        scanner.close();
        writeToFile(data, surname);
    }

    // Ввод строки
    public static String inputString(Scanner scanner) {
        System.out.println("Введите Ваши данные:");
        System.out.println("фамилия имя отчество через пробел,");
        System.out.println("дата рождения в формате дд.мм.гггг,");
        System.out.println("номер телефона (только цифры), ");
        System.out.println("пол (m / f).");
        System.out.println("Все данные вводите одной строкой через пробел: \n");
        String inputString = scanner.nextLine();
        return inputString;
    }

    // Количество введённых данных
    public static int length(String str){
        String[] strs = str.split(" ");
        int lenthOfString = strs.length;
        if (lenthOfString < 6) {
            isDataOk = false;
            System.err.println("Вы ввели не все данные");
        } else if (lenthOfString > 6) {
            isDataOk = false;
            System.err.println("Вы ввели слишком много данных");
        } else {

        }
        return lenthOfString;
    }

    // Високосный ли год
    public static boolean isYearLeap(int year) {
        if ((year % 4) == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) return true;
                else return false;
            }
            return true;
        }
        return false;
    }

    // Получить дату из строки
    public static LocalDate getDate(String dateOneString){
        LocalDate birthday = null;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            birthday = LocalDate.parse(dateOneString, dtf);
        } catch (DateTimeParseException e) {
            isDataOk = false;
            System.err.println("Некорректная дата");
        }
        return birthday;
    }

    public static long getPhoneNumer(String str) {
        long phoneNum = 0;
        try {
            phoneNum = Long.parseLong(str);
        } catch (NumberFormatException e) {
            isDataOk = false;
            System.err.println("Невалидный номер телефона");
        }
        return phoneNum;
    }

    public static String getSex(String str) {
        if (!str.equals("m") && !str.equals("f")) {
            isDataOk = false;
            System.err.println("Неверно указан пол");
            return null;
        } else {
            return str;
        }
    }

    public static void writeToFile(String str, String surname) throws IOException {
        try {
            FileWriter fw = new FileWriter(String.format("%s.txt", surname), true);
            fw.write(str);
            fw.append('\n');
            fw.close();
        } catch (Exception e) {
            System.err.println("Ошибка записи в файл");
        }

    }
}
