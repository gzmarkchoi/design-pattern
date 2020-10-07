package com.mci.designpattern.wrongpractice;


import java.util.Collections;
import java.util.List;

public class ShoppingCartRight {
    // ...
    public List<ShoppingCartItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }
}

/*
    通过 Java 提供的 Collections.unmodifiableList() 方法，让 getter 方法返回一个不可被修改的 UnmodifiableList 集合容器，
    而这个容器类重写了 List 容器中跟修改数据相关的方法，比如 add()、clear() 等方法。一旦我们调用这些修改数据的方法，
    代码就会抛出 UnsupportedOperationException 异常，这样就避免了容器中的数据被修改。
 */
class UnmodifiableList<E> extends UnmodifiableCollection<E> implements List<E> {
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }
    public void clear() {
        throw new UnsupportedOperationException();
    }
    // ...
}

//ShoppingCartRight cart = new ShoppingCartRight();
//List<ShoppingCartItem> items = cart.getItems();
//items.clear();//抛出UnsupportedOperationException异常
