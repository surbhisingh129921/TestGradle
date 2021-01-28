import org.apache.commons.lang.StringUtils;

import java.util.Calendar;

public enum PipelineEventTableType {
    MONTHLY,
    WEEKLY;

    public static String getTableName(Calendar calendar,String columnFamilyPrefix) {
        StringBuilder tableName = new StringBuilder();
        tableName.append(columnFamilyPrefix).append("_");
        switch ("WEEKLY") {
            case "WEEKLY":
                if (calendar.get(Calendar.WEEK_OF_YEAR) == 1 && calendar.get(Calendar.MONTH) == 11) {
                    tableName.append(calendar.get(Calendar.YEAR) + 1);
                } else {
                    tableName.append(calendar.get(Calendar.YEAR));
                }
                tableName.append("W");
                tableName.append(StringUtils.leftPad("" + (calendar.get(Calendar.WEEK_OF_YEAR)), 2, '0'));
                break;
            default:
                tableName.append(calendar.get(Calendar.YEAR)).append("M");
                tableName.append(StringUtils.leftPad("" + (calendar.get(Calendar.MONTH) + 1), 2, '0'));
                break;
        }
        return tableName.toString();
    }
}

