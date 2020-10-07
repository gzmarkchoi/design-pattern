package com.mci.designpattern.abstractioninterface;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceFilter {
    void doFilter(RpcRequest req) throws RpcException;
}

public class AuthenticationFilter implements InterfaceFilter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        //...
    }
}

public class RateLimitFilter implements InterfaceFilter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        //...
    }
}

public class Application {
    // filters.add(new AuthenticationFilter());
    // filters.add(new RateLimitFilter());
    private List<InterfaceFilter> filters = new ArrayList<>();

    public void handleRpcRequest(RpcRequest req) {
        try {
            for (InterfaceFilter filter : fitlers) {
                filter.doFilter(req);
            }
        } catch(RpcException e) {
            // ...处理过滤结果...
        }
        // ...省略其他处理逻辑...
    }
}
