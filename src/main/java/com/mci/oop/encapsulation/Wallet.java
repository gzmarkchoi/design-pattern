package com.mci.oop.encapsulation;


import org.springframework.util.IdGenerator;

import java.math.BigDecimal;

/*
    Ref. Ch.05 for more detailed notes
 */
public class Wallet {
    private String id;
    private long createTime;
    private BigDecimal balance;
    private long balanceLastModifiedTime;
    // ...

	public Wallet() {
		this.id = IdGenerator.getInstance().generate();
		this.createTime = System.currentTimeMillis();
		this.balance = BigDecimal.ZERO;
		this.balanceLastModifiedTime = System.currentTimeMillis();
	}

    /*
	        只暴露了 increaseBalance() 和 decreaseBalance() 方法，并没有暴露 set 方法。对于 balanceLastModifiedTime 这个属性，
	        它完全是跟 balance 这个属性的修改操作绑定在一起的。
	        只有在 balance 修改的时候，这个属性才会被修改。所以，我们把 balanceLastModifiedTime 这个属性的修改操作完全封装在了 increaseBalance() 和 decreaseBalance() 两个方法中，
	        不对外暴露任何修改这个属性的方法和业务细节。这样也可以保证 balance 和 balanceLastModifiedTime 两个数据的一致性。
     */
	public String getId() {
		return this.id;
	}

	public long getCreateTime() {
		return this.createTime;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public long getBalanceLastModifiedTime() {
		return this.balanceLastModifiedTime;
	}

	public void increaseBalance(BigDecimal increasedAmount) {
		if (increasedAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidAmountException("...");
		}
		this.balance.add(increasedAmount);
		this.balanceLastModifiedTime = System.currentTimeMillis();
	}

	public void decreaseBalance(BigDecimal decreasedAmount) {
		if (decreasedAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidAmountException("...");
		}
		if (decreasedAmount.compareTo(this.balance) > 0) {
			throw new InsufficientAmountException("...");
		}
		this.balance.subtract(decreasedAmount);
		this.balanceLastModifiedTime = System.currentTimeMillis();
	}
}
