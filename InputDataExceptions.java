package Lesson3_DZ;

public class InputDataExceptions extends RuntimeException {

    public InputDataExceptions() {
        super("Введены некорректные данные!");
    }
    
    public InputDataExceptions(String message) {
        super(message);
    }

    public InputDataExceptions(int codeError) {
        super();
        if (codeError > 6) {
            System.out.println("Введено большое количество данных!");
        } else {
            System.out.println("Введено малое количество данных!");
        }
    }

    public InputDataExceptions(int codeError, String message) {
        super(message);
        if (codeError == 0) {
            System.out.println("Фамилия введена некорректно!");
        } else if (codeError == 1) {
            System.out.println("Имя введено некорректно!");
        } else if (codeError == 2) {
            System.out.println("Отчество введено некорректно!");
        }
    }

}
