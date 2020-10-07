package com.mci.oop.interfaceexample;

import java.util.ArrayList;
import java.util.List;

/*
	接口都有哪些特性。我也总结了三点。
	- 接口不能包含属性（也就是成员变量）。
	- 接口只能声明方法，方法不能包含代码实现。
	- 类实现接口的时候，必须实现接口中声明的所有方法。
 */
public interface Filter {
    void doFilter(RpcRequest req) throws RpcException;
}

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        // do something with authentication
    }
}

public class RateLimitFilter implements Filter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        // do something with rate limit
    }
}

public class Application {
    private List<Filter> filters = new ArrayList<>();

    public void handleRpcRequest(RpcRequest request) {
        try {
            for(Filter filter : filters) {
                filter.doFilter(request);
            }
        } catch(RpcException e) {
            // handle exceptions
        }
    }
}
