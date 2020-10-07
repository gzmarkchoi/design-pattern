package com.mci.designpattern.wrongpractice;

import java.util.List;

/*
    违反了面向对象编程的封装特性，相当于将面向对象编程风格退化成了面向过程编程风格。
    Ref -> Ch.07 for more detailed notes
 */
public class ShoppingCartWrong {
    private int itemsCount;
    private double totalPrice;
    private List<ShoppingCartItem> items = new ArrayList<>();

    /*
        看看itemsCount 和 totalPrice两个属性，虽然我们将它们定义成 private 私有属性，但是提供了 public 的 getter、setter 方法，
        这就跟将这两个属性定义为 public 公有属性，没有什么两样了。外部可以通过 setter 方法随意地修改这两个属性的值。除此之外，
        任何代码都可以随意调用 setter 方法，来重新设置 itemsCount、totalPrice 属性的值，这也会导致其跟 items 属性的值不一致。
     */
    public int getItemsCount() {
        return this.itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ShoppingCartItem> getItems() {
        return this.items;
    }

    public void addItem(ShoppingCartItem item) {
        items.add(item);
        itemsCount++;
        totalPrice += item.getPrice();
    }
    // ...
}