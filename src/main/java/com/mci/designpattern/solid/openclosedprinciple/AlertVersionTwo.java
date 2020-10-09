package com.mci.designpattern.solid.openclosedprinciple;

/**
    这样的代码修改实际上存在挺多问题的。
    - 一方面，我们对接口进行了修改，这就意味着调用这个接口的代码都要做相应的修改。
    - 另一方面，修改了 check() 函数，相应的单元测试都需要修改（关于单元测试的内容我们在重构那部分会详细介绍）。

    上面的代码改动是基于“修改”的方式来实现新功能的。如果我们遵循开闭原则，也就是“对扩展开放、对修改关闭”。
    那如何通过“扩展”的方式，来实现同样的功能呢？
 */
public class AlertVersionTwo {

    private AlertRule rule;
    private Notification notification;

    public AlertVersionTwo(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    // 改动一：添加参数timeoutCount
    public void check(String api, long requestCount, long errorCount, long timeoutCount, long durationOfSeconds) {
        long tps = requestCount / durationOfSeconds;
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
        // 改动二：添加接口超时处理逻辑
        long timeoutTps = timeoutCount / durationOfSeconds;
        if (timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }
}
