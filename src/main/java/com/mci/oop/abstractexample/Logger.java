package com.mci.oop.abstractexample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;

/*
	抽象类具有哪些特性。我总结了下面三点。
	- 抽象类不允许被实例化，只能被继承。也就是说，你不能 new 一个抽象类的对象出来（Logger logger = new Logger(…); 会报编译错误）。
	- 抽象类可以包含属性和方法。方法既可以包含代码实现（比如 Logger 中的 log() 方法），也可以不包含代码实现（比如 Logger 中的 doLog() 方法）。不包含代码实现的方法叫作抽象方法。
	- 子类继承抽象类，必须实现抽象类中的所有抽象方法。对应到例子代码中就是，所有继承 Logger 抽象类的子类，都必须重写 doLog() 方法。
*/
public abstract class Logger {
    private String name;
    private boolean enabled;
    private Level minPermittedLevel;


    public Logger(String name, boolean enabled, Level minPermittedLevel) {
        this.name = name;
        this.enabled = enabled;
        this.minPermittedLevel = minPermittedLevel;
    }

    public void log(Level level, String message) {
        boolean loggable = enabled && (minPermittedLevel.intValue() <= level.intValue());
        if (!loggable) return;
        doLog(level, message);
    }

    protected abstract void doLog(Level level, String message);
}

public class FileLogger extends Logger {
    private Writer fileWriter;
    public FileLogger(String name, boolean enabled, Level minPermittedLevel, String filepath) {
        super(name, enabled, minPermittedLevel);
        try {
            this.fileWriter = new FileWriter(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doLog(Level level, String mesage) {
    	// 格式化level和message,输出到日志文件
        fileWriter.write(...);
    }
}

public class MessageQueueLogger extends Logger {
    private MessageQueueClient msgQueueClient;

    public MessageQueueLogger(String name, boolean enabled, Level minPermittedLevel, MessageQueueClient msgQueueClient) {
        super(name, enabled, minPermittedLevel); this.msgQueueClient = msgQueueClient;
    }

    @Override
    protected void doLog(Level level, String mesage) {
        // 格式化level和message,输出到消息中间件
        msgQueueClient.send(...); }
}
