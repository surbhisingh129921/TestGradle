import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Test {
    static int pipelineEventWeeklyDropRetention = 9;
    static int pipelineEventMonthlyDropRetention = 6;

    public static void main(String[] args) {
        Calendar calendar = determineCalendarForPETable(PipelineEventTableType.WEEKLY);
        String tableName = "DEPLOYED_MESSAGES_V6";
        tableName = PipelineEventTableType.getTableName(calendar, tableName);
        System.out.println("tableName "+tableName);

        System.out.println("week eight"+getCurrentWeek());
    }

    private static int getCurrentWeek() {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }
     static Calendar determineCalendarForPETable(PipelineEventTableType pipelineEventTableType) {
        GregorianCalendar calendar = new GregorianCalendar();
         System.out.println("time"+calendar.getTime());
         System.out.println("week of month "+calendar.WEEK_OF_MONTH);
         System.out.println("week of year "+calendar.WEEK_OF_YEAR);
        if (pipelineEventTableType == PipelineEventTableType.WEEKLY) {
            calendar.add(Calendar.WEEK_OF_YEAR, -pipelineEventWeeklyDropRetention);
        } else {
            calendar.add(Calendar.MONTH, -pipelineEventMonthlyDropRetention);
        }
        return calendar;
    }


}
