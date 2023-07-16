import java.util.ArrayList;

public class YearlyReport {
    ArrayList<ReportManagerYearly.RecordYear> expenses;
    ArrayList<ReportManagerYearly.RecordYear> incomes;

    public YearlyReport(ArrayList<ReportManagerYearly.RecordYear> expenses, ArrayList<ReportManagerYearly.RecordYear> incomes) {
        this.expenses = expenses;
        this.incomes = incomes;
    }
}
