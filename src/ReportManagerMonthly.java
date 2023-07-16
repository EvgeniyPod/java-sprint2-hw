import java.util.ArrayList;
import java.util.HashMap;

/** Класс считывания отчётов за каждый месяц
 * Включает в себя метод "infMonthly" - вывод информации об отчётах
 */
public class ReportManagerMonthly {

    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();

    FileReader fileReader = new FileReader();

    public void readMontlyReports() {
        for (int i = 1; i < 4; i++) {
            String fileName = "m.20210" + i + ".csv";
            ArrayList<String> lines = fileReader.readFileContents(fileName);

            if (lines.isEmpty()) {
                System.out.println("Файл " + fileName + " пустой.");
                return;
            }

            ArrayList<Record> expenses = new ArrayList<>();
            ArrayList<Record> incomes = new ArrayList<>();

            for (int j = 1; j < lines.size(); j++) {
                String line = lines.get(j);
                String[] values = line.split(",");
                Record record = lineToRecord(values);

                if (record.getExpense()) {
                    expenses.add(record);
                } else {
                    incomes.add(record);
                }
            }
            monthlyReports.put(i, new MonthlyReport(expenses, incomes));
        }

    }

    private Record lineToRecord(String[] values) {
        return new Record(values[0], Boolean.parseBoolean(values[1]), Integer.parseInt(values[2]), Double.parseDouble(values[3]));
    }

    static class Record {
        String name;
        Boolean expense;
        Integer quantity;
        Double price;

        public Record(String name, Boolean expense, Integer quantity, Double price) {
            this.name = name;
            this.expense = expense;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Boolean getExpense() {
            return expense;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Double getPrice() {
            return price;
        }
    }

    public void infMonthly() {

        if (monthlyReports.isEmpty()) {
            System.out.println("Отчёты за месяц не считаны. Считайте их!");
            return;
        }

        for (Integer month : monthlyReports.keySet()) {
            System.out.println("Месяц m.20210" + month + ".csv:");

            double maxProfitUnit = 0;
            String profitName = "";
            double maxUnprofitUnit = 0;
            String unprofitName = "";

            for (int i = 0; i < monthlyReports.get(month).incomes.size(); i++) {
                double profitUnit = monthlyReports.get(month).incomes.get(i).price * monthlyReports.get(month).incomes.get(i).quantity;
                if (profitUnit > maxProfitUnit) {
                    maxProfitUnit = profitUnit;
                    profitName = monthlyReports.get(month).incomes.get(i).name;
                }

                for (int j = 0; j < monthlyReports.get(month).expenses.size(); j++) {
                    double unprofitUnit = monthlyReports.get(month).expenses.get(j).price * monthlyReports.get(month).expenses.get(j).quantity;
                    if (unprofitUnit > maxUnprofitUnit) {
                        maxUnprofitUnit = unprofitUnit;
                        unprofitName = monthlyReports.get(month).expenses.get(j).name;
                    }
                }
            }

            System.out.println("Наибольшая прибыль: " + profitName + " = " + maxProfitUnit);
            System.out.println("Наибольшая трата: " + unprofitName + " = " + maxUnprofitUnit);
        }
    }

}
