import java.util.ArrayList;

public class MonthlyReport {

    ArrayList<ReportManagerMonthly.Record> expenses;
    ArrayList<ReportManagerMonthly.Record> incomes;

    public MonthlyReport(ArrayList<ReportManagerMonthly.Record> expenses, ArrayList<ReportManagerMonthly.Record> incomes) {
        this.expenses = expenses;
        this.incomes = incomes;
    }
}
