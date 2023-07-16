import java.util.Scanner;
/**
 * Класс вывода консольного меню
 * @author Евгений Подолский
 * @version 1.1
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportManagerMonthly reportManagerMonthly = new ReportManagerMonthly();
        ReportManagerYearly reportManagerYearly = new ReportManagerYearly();
        Checker checker = new Checker(reportManagerYearly, reportManagerMonthly);

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                reportManagerMonthly.readMontlyReports();
                System.out.println("Месячные отчёты успешно считаны!");
            }

            else if (command == 2) {
                reportManagerYearly.readYearlyReports();
                System.out.println("Годовой отчёт успешно считан!");
            }

            else if (command == 3) {
                checker.Check();
            }

            else if (command == 4) {
                reportManagerMonthly.infMonthly();
            }

            else if (command == 5) {
                reportManagerYearly.infYerly();
            }

            else if (command == 6) {
                System.out.println("Работа программы завершена, досвидания!");
                return;
            } else {
                System.out.println("Данная команда отсутствует, выберите от 1 до 6.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("1. Считать месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("6. Завершить работу программы ");
    }
}

