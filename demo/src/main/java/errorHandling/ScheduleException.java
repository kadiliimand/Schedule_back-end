package errorHandling;

public class ScheduleException extends RuntimeException {
    public ScheduleException(String message) {
        super(message);
    }
}
