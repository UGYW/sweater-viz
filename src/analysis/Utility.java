package analysis;

import org.jfree.data.time.SimpleTimePeriod;

import java.time.LocalDateTime;
import java.util.Date;

public class Utility {
    public static Date makeDate() {
        // TODO: determine what arguments are needed
        return new Date();
    }

    public static SimpleTimePeriod makeTimePeriod(Date start, Date end) {
        return new SimpleTimePeriod(start, end);
    }

    // TODO: add others as needed
}
