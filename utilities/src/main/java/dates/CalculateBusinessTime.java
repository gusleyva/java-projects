package dates;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CalculateBusinessTime {
    private LocalDateTime holidayStartDate = null;
    private LocalDateTime holidayEndDate = null;

    public static void main(String args[]) {
        String holidayJson = "{\"start\":\"2019-12-24T21:00:00\",\"end\":\"2019-12-25T21:00:00\"}";
        CalculateBusinessTime calculateBusinessTime = new CalculateBusinessTime();

        try {
            printResult(calculateBusinessTime.getBusinessTime(holidayJson, LocalDateTime.parse("2019-12-01T00:00:00"), 60*60));
            printResult(calculateBusinessTime.getBusinessTime(holidayJson, LocalDateTime.parse("2019-12-24T21:00:00"), 1));
            printResult(calculateBusinessTime.getBusinessTime(holidayJson, LocalDateTime.parse("2019-12-24T20:30:00"), 60*60));
            printResult(calculateBusinessTime.getBusinessTime(holidayJson, LocalDateTime.parse("2019-12-25T00:00:00"), 1));
            printResult(calculateBusinessTime.getBusinessTime(holidayJson, LocalDateTime.parse("2019-12-25T00:00:00"), -1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print calculated business time
     * @param result
     */
    public static void printResult(LocalDateTime result) {
        System.out.println(result);
    }

    /**
     * Calculate business time
     *
     * Idea is add amounts of seconds related to the holiday day if the time
     * is inside period.
     * I.e - time = 2019-12-24T21:00:00, endDate = 2019-12-25T21:00:00
     * timeToAdd = 75600, duration = 1
     * result = 2019-12-25T21:00:01
     * @param holidayDatesJson
     * @param time
     * @param duration
     * @return
     * @throws ParseException
     */
    public LocalDateTime getBusinessTime(String holidayDatesJson, LocalDateTime time, int duration) throws ParseException {
        long timeToAdd = 0L;

        parseHolidayDates(holidayDatesJson);
        LocalDateTime timePlusSeconds = time.plusSeconds(duration);

        if (timePlusSeconds.isAfter(holidayStartDate) && timePlusSeconds.isBefore(holidayEndDate)) {
            if (timePlusSeconds.getDayOfMonth() == holidayEndDate.getDayOfMonth()) {
                timeToAdd = Duration.between(time, holidayEndDate).getSeconds();
            } else if (timePlusSeconds.getDayOfMonth() == holidayStartDate.getDayOfMonth() && duration < 0) {
                timeToAdd = Duration.between(holidayStartDate, time).getSeconds() * -1;
            } else {
                timeToAdd = Duration.between(holidayStartDate, holidayEndDate).getSeconds();
            }
        }
        return time.plusSeconds(timeToAdd).plusSeconds(duration);
    }

    /**
     *
     * @param holidayJson
     * @return
     * @throws ParseException
     */
    public JSONObject getJsonObj(String holidayJson) throws ParseException {
        return (JSONObject)new JSONParser().parse(holidayJson);
    }

    /**
     *
     * @param holidayJson
     * @throws ParseException
     */
    public void parseHolidayDates(String holidayJson) throws ParseException{
        JSONObject holidayDates = getJsonObj(holidayJson);
        String startDate = (String)holidayDates.get("start");
        String endDate = (String)holidayDates.get("end");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        holidayStartDate = LocalDateTime.parse(startDate, formatter);
        holidayEndDate = LocalDateTime.parse(endDate, formatter);
    }

}
