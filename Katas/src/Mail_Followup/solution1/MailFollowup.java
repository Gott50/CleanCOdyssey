package Mail_Followup.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Arrays.asList;

class MailFollowup {
    static Date pointInTime(Date now, String emailAddress) {
        Date out = (Date) now.clone();

        ArrayList<String> command = filterEmpty(emailAddress.replace("@followup.cc", "").split("\\d+-?"));
        ArrayList<String> number = filterEmpty(emailAddress.replace("@followup.cc", "").split("\\D+"));

        for (int i = 0; i < command.size(); i++) {
            adjustDate(out, Integer.parseInt(number.get(i)),
                    removePlural(command, i));
        }
        return out;
    }

    private static String removePlural(ArrayList<String> command, int i) {
        return command.get(i).endsWith("s") ?
                command.get(i).substring(0, command.get(i).length() - 1) :
                command.get(i);
    }

    private static void adjustDate(Date out, int num, String com) {
        switch (com) {
            case "day":
                addDays(out, num);
                break;
            case "week":
                addDays(out, num * 7);
                break;
            case "hour":
                out.setHours(out.getHours() + num);
                break;
            case "aug":
                if (out.getMonth() >= 8) out.setYear(out.getYear() + 1);
                out.setMonth(8);
                out.setDate(num);
                break;
            case "am":
                setTime(out, num);
                break;
            case "pm":
                setTime(out, num + 12);
                break;

        }
    }

    @NotNull
    private static ArrayList<String> filterEmpty(String[] array) {
        ArrayList<String> command = new ArrayList<>(asList(array));
        command.removeIf(String::isEmpty);
        return command;
    }

    private static void setTime(Date out, int hours) {
        out.setHours(hours);
        out.setMinutes(0);
        out.setSeconds(0);
    }

    private static void addDays(Date out, int days) {
        out.setDate(out.getDate() + days);
    }

}
