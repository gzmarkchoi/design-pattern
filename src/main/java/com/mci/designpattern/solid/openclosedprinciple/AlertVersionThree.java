package com.mci.designpattern.solid.openclosedprinciple;

import java.util.ArrayList;
import java.util.List;

/**
	我们先重构一下之前的 Alert 代码，让它的扩展性更好一些。重构的内容主要包含两部分：
	- 第一部分是将 check() 函数的多个入参封装成 ApiStatInfo 类；
	- 第二部分是引入 handler 的概念，将 if 判断逻辑分散在各个 handler 中。
 */
public class AlertVersionThree {
    private List<AlertHandler> alertHandlers = new ArrayList<AlertHandler>();

    public void addAlertHandler(AlertHandler alertHandler) {
        this.alertHandlers.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : alertHandlers) {
            handler.check(apiStatInfo);
        }
    }
}



