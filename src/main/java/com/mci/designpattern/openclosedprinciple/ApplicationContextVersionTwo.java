package com.mci.designpattern.openclosedprinciple;

public class ApplicationContextVersionTwo {
    private AlertRule alertRule;
    private Notification notification;
    private AlertVersionThree alert;

    public void initializeBeans() {
        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
        notification = new Notification(/*.省略参数.*/); //省略一些初始化代码
        alert = new AlertVersionThree();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        // 改动三：注册handler
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));
    }
    //...省略其他未改动代码...
}

class DemoApplicationContextVersionTwo {
    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ...省略apiStatInfo的set字段代码
        apiStatInfo.setTimeoutCount(289); // 改动四：设置tiemoutCount值
        ApplicationContextVersionTwo.getInstance().getAlert().check(apiStatInfo);
    }
}