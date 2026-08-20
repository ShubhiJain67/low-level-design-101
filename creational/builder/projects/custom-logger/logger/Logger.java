package logger;

import appender.ILogAppender;
import builder.LogBuilder;
import formatter.ILogFormatter;
import model.LogEntry;
import model.LogLevel;

public class Logger {
    private final ILogAppender appender;
    private final ILogFormatter formatter;

    public Logger(ILogAppender appender, ILogFormatter formatter) {
        this.appender = appender;
        this.formatter = formatter;
    }
    public LogBuilder info(){
        return new LogBuilder(LogLevel.INFO);
    }
    public LogBuilder debug(){
        return new LogBuilder(LogLevel.DEBUG);
    }
    public LogBuilder error(){
        return new LogBuilder(LogLevel.ERROR);
    }
    public LogBuilder warn(){
        return new LogBuilder(LogLevel.WARN);
    }
    public LogBuilder fatal(){
        return new LogBuilder(LogLevel.FATAL);
    }
    
    public void log(LogEntry logEntry){
        var formattedLog = formatter.format(logEntry);
        appender.append(formattedLog);
    }

}
