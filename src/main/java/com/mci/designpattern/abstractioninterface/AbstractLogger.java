package com.mci.designpattern.abstractioninterface;

import java.io.FileWriter;
import java.io.Writer;

public abstract class AbstractLogger {
    private String name;
    private boolean enabled;
    private int minPermittedLevel;

    public AbstractLogger(String name, boolean enabled, int minPermittedLevel) {
        this.name = name;
        this.enabled = enabled;
        this.minPermittedLevel = minPermittedLevel;
    }

    public void log(int level, String message) {
        boolean loggable = enabled && (minPermittedLevel <= level);
        if (!loggable) return;
        doLog(level, message);
    }

    protected abstract void doLog(int level, String message);
}

public class FileLogger extends AbstractLogger {
    private Writer fileWriter;

    public FileLogger(String name, boolean enabled, int minPermittedLevel, String filepath) {
        super(name, enabled, minPermittedLevel);
        this.fileWriter = new FileWriter(filepath);
    }

    @Override
    public void doLog(int level, String message) {
        // 格式化level和message,输出到日志文件
        fileWriter.write();
    }
}

public class MessageQueueLogger extends AbstractLogger {
    private MessageQueueClient msgQueueClient;

    public MessageQueueLogger(String name, boolean enabled, int minPermittedLevel, MessageQueueClient msgQueueClient) {
        super(name, enabled, minPermittedLevel);
        this.msgQueueClient = msgQueueClient;
    }

    @Override
    protected void doLog(int level, String message) {
        msgQueueClient.send();
    }
}