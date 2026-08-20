package logger;
  
import appender.ConsoleAppender;
import formatter.JSONFormatter;
  
public class LoggerManager {
  
    private final Logger logger;
    private LoggerManager() {
        this.logger = new Logger(
            new ConsoleAppender(),
            new JSONFormatter()
        );
    }
  
      // Bill Pugh Singleton
      private static class Holder {
  
          private static final LoggerManager INSTANCE = new LoggerManager();
      }
  
      public static LoggerManager getInstance() {
          return Holder.INSTANCE;
      }
  
      public Logger getLogger() {
          return logger;
      }
  }