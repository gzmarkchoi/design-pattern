package com.mci.designpattern.solid.openclosedprinciple;

/**
 ApplicationContext 是一个单例类，负责 Alert 的创建、组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作。
 */
public class ApplicationContextVersionOne {
    private AlertRule alertRule;
    private Notification notification;
    private AlertVersionThree alert;

    public void initializeBeans() {
        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
        notification = new Notification(/*.省略参数.*/); //省略一些初始化代码
        alert = new AlertVersionThree();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
    }
    public AlertVersionThree getAlert() { return alert; }

    // 饿汉式单例
    private static final ApplicationContextVersionOne instance = new ApplicationContextVersionOne();
    private ApplicationContextVersionOne() {
        initializeBeans();
    }
    public static ApplicationContextVersionOne getInstance() {
        return instance;
    }
}

class DemoApplicationContextVersionOne {
    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ...省略设置apiStatInfo数据值的代码
        ApplicationContextVersionOne.getInstance().getAlert().check(apiStatInfo);
    }
}
