package appender;

public class ConsoleAppender implements ILogAppender {
    @Override
    public void append(String logEntry) {
        System.out.println(logEntry);
    }
}
