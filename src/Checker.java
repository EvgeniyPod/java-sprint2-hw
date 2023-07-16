import java.util.HashMap;

/** Класс для сравнения отчётов за месяцы и годового */
public class Checker {
    public ReportManagerYearly reportManagerYearly;
    public ReportManagerMonthly reportManagerMonthly;

    public Checker(ReportManagerYearly reportManagerYearly, ReportManagerMonthly reportManagerMonthly) {
        this.reportManagerYearly = reportManagerYearly;
        this.reportManagerMonthly = reportManagerMonthly;
    }

    public void Check() {
        if (reportManagerYearly.yearlyReports.isEmpty() || reportManagerMonthly.monthlyReports.isEmpty()) {
            System.out.println("Отчёт за год или отчёты за месяц не считаны. Считайте их!");
            return;
        }

        HashMap<Integer, Double> monthIncome = new HashMap<>();
        HashMap<Integer, Double> monthExpense = new HashMap<>();
        HashMap<Integer, Integer> yearIncome = new HashMap<>();
        HashMap<Integer, Integer> yearExpense = new HashMap<>();

        double incomeSumYear = 0;
        double expenseSumYear = 0;


        for (Integer month : reportManagerMonthly.monthlyReports.keySet()) {

            double incomeSum = 0;
            double expenseSum = 0;

            MonthlyReport monthlyReport = reportManagerMonthly.monthlyReports.get(month);

            for (ReportManagerMonthly.Record income : monthlyReport.incomes) {
                incomeSum += income.price * income.quantity;
                monthIncome.put(month, incomeSum);
            }

            for (ReportManagerMonthly.Record expense : monthlyReport.expenses) {
                expenseSum += expense.price * expense.quantity;
                monthExpense.put(month, expenseSum);
            }
        }

        YearlyReport yearlyReport = reportManagerYearly.yearlyReports.get("y.2021.csv");

        for (ReportManagerYearly.RecordYear income : yearlyReport.incomes) {
            yearIncome.put(income.numMonth, income.amount);
        }

        for (ReportManagerYearly.RecordYear expense : yearlyReport.expenses) {
            yearExpense.put(expense.numMonth, expense.amount);
        }

        for (Integer month : monthIncome.keySet()) {
            double totalPriceMonth = monthIncome.get(month);
            double totalPriceYear = yearIncome.get(month);
            if (totalPriceYear != totalPriceMonth) {
                System.out.println("Есть несоответствие в годовом отчёте и отчёте за " + month + " месяц по прибыли");
                return;
            }
        }

        for (Integer month : monthExpense.keySet()) {
            double totalPriceMonth = monthExpense.get(month);
            double totalPriceYear = yearExpense.get(month);
            if (totalPriceYear != totalPriceMonth) {
                System.out.println("Есть несоответствие в годовом отчёте и отчёте за " + month + " месяц по самой большой трате");
                return;
            }
        }

        System.out.println("Все отчёты совпадают.");
            }

        }

