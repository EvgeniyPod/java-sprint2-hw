import java.util.ArrayList;
import java.util.HashMap;

/** Класс считывания отчёта за год
 * Включает в себя метод "infYearly" - вывод информации об отчёте
 */
public class ReportManagerYearly {

    //ArrayList<YearlyReport> yearlyReports = new ArrayList<>();
    HashMap<String, YearlyReport> yearlyReports = new HashMap<>();

    FileReader fileReader = new FileReader();

    public void readYearlyReports () {
        String fileName = "y.2021.csv";
        ArrayList<String> lines = fileReader.readFileContents(fileName);

        if (lines.isEmpty()) {
            System.out.println("Файл " + fileName + " пустой.");
            return;
        }

        ArrayList<ReportManagerYearly.RecordYear> expenses = new ArrayList<>();
        ArrayList<ReportManagerYearly.RecordYear> incomes = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] values = line.split(",");
            RecordYear recordYear = lineToRecordYear(values);

            if (recordYear.getExpense()) {
                expenses.add(recordYear);
            } else {
                incomes.add(recordYear);
            }
            yearlyReports.put("y.2021.csv", new YearlyReport(expenses, incomes));
        }
    }

    private RecordYear lineToRecordYear(String[] values) {
        return new RecordYear(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Boolean.parseBoolean(values[2]));
    }

    static class RecordYear {
        Integer numMonth;
        Integer amount;
        Boolean expense;

        public RecordYear(Integer numMonth, Integer amount, Boolean expense) {
            this.numMonth = numMonth;
            this.amount = amount;
            this.expense = expense;
        }

        public Integer getNumMonth() {
            return numMonth;
        }

        public Integer getAmount() {
            return amount;
        }

        public Boolean getExpense() {
            return expense;
        }
    }

    public void infYerly() {
        double priceExp = 0;
        double priceInc = 0;
        double middleInc = 0;
        double middleExp = 0;
        if (yearlyReports.isEmpty()) {
            System.out.println("Отчёт за год не считан. Считайте его!");
            return;
        }

        for (String year : yearlyReports.keySet()) {
            System.out.println("Год " + year + ":");
            for (int i = 0; i < yearlyReports.get(year).incomes.size(); i++) {
                priceInc = yearlyReports.get(year).incomes.get(i).amount;
                System.out.println("Прибыль за " + (i + 1) + " месяц = " + priceInc);
            }

            priceInc = 0;
            for (int i = 0; i < yearlyReports.get(year).incomes.size(); i++) {
                priceInc += yearlyReports.get(year).incomes.get(i).amount;
            }
            middleInc = priceInc / yearlyReports.get(year).incomes.size();
            System.out.println("Средний доход составляет: " + middleInc);

            for (int i = 0; i < yearlyReports.get(year).expenses.size(); i++) {
                priceExp += yearlyReports.get(year).expenses.get(i).amount;
            }

            middleExp = priceExp / yearlyReports.get(year).incomes.size();
            System.out.println("Средний расход составляет: " + middleExp);
        }
    }
}
