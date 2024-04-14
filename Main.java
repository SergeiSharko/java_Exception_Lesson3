package Lesson3_DZ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите следующие данные, разделенные пробелом:\n" +
                "Фамилия Имя Отчество дата_рождения(формата dd.mm.yyyy) номер_телефона пол(символ f или m)");
        Scanner scan = new Scanner(System.in);
        String inputData = scan.nextLine();
        scan.close();
        int sizeData = 6;
        String[] arrInputString = inputData.trim().split(" ");

        if (arrInputString.length != sizeData) {
            throw new InputDataExceptions(arrInputString.length);
        }

        checkFioForCorrect(arrInputString);
        checkDateOfBirthForCorrect(arrInputString[3]);
        checkPhoneNumberForCorrect(arrInputString[4]);
        checkSexForCorrect(arrInputString[5]);

        File file = new File(arrInputString[0] + ".txt");
        writeToFile(arrInputString, file);
    }

    public static void writeToFile(String[] arrInputString, File file) {

        try (FileWriter fr = new FileWriter(file, true)) {

            StringBuilder inputStrFormat = new StringBuilder();
            
            for (int i = 0; i < arrInputString.length; i++) {
                inputStrFormat.append("<");
                inputStrFormat.append(arrInputString[i]);
                inputStrFormat.append(">");
            }
            fr.append(inputStrFormat);
            fr.append('\n');
            System.out.println("Данные записаны в txt-файл с названием, равным фамилии!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkFioForCorrect(String[] arrInputString) {

        for (int i = 0; i < 3; i++) {
            char[] currElement = arrInputString[i].toCharArray();
            if (currElement.length <= 1) {
                throw new InputDataExceptions(i, "Некорректный ввод ФИО!");
            }

            for (int j = 0; j < currElement.length; j++) {
                if (!Character.isLetter(currElement[j])) {
                    throw new InputDataExceptions(i, "Некорректный ввод ФИО!");
                }
            }
        }
    }

    public static void checkDateOfBirthForCorrect(String dateOfBirth) {

        try {

            String tempDateBirth = dateOfBirth.replace(".", "-");
            String[] arrFromDateBirth = tempDateBirth.split("-");

            for (int i = 0; i < (arrFromDateBirth.length - 1) / 2; i++) {
                var temp = arrFromDateBirth[i];
                arrFromDateBirth[i] = arrFromDateBirth[arrFromDateBirth.length - 1 - i];
                arrFromDateBirth[arrFromDateBirth.length - 1 - i] = temp;
            }

            String dateBirthForLocalDate = String.join("-", arrFromDateBirth);

            LocalDate currDateBirth = LocalDate.parse(dateBirthForLocalDate);

            if (currDateBirth.getYear() < 1910 || currDateBirth.getYear() > 2024) {
                throw new InputDataExceptions("Год рождения введен некорректно!");
            }

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new InputDataExceptions("Дата дня рождения введена некорректно!");
        }
    }

    public static void checkPhoneNumberForCorrect(String phoneNumber) {
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new InputDataExceptions("Телефонный номер введен некорректно!");
        }
    }

    public static void checkSexForCorrect(String sex) {
        if (sex.length() > 1) {
            throw new InputDataExceptions("Пол введен некорректно!");
        } else if (!sex.equalsIgnoreCase("f") && !sex.equalsIgnoreCase("m")) {
            throw new InputDataExceptions("Пол введен некорректно!");
        }
    }
}
