package formatter;

import model.LogEntry;

public interface ILogFormatter {
    String format(LogEntry logEntry);
}
