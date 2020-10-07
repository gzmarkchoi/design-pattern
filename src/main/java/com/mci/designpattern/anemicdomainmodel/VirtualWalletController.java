package com.mci.designpattern.anemicdomainmodel;


import java.math.BigDecimal;

public class VirtualWalletController {
    // 通过构造函数或者IOC框架注入
    private VirtualWalletService virtualWalletService;

    public BigDecimal getBalance(Long walletId) { 
    	// ... 
    } //查询余额
    
    public void debit(Long walletId, BigDecimal amount) { 
    	// ... 
    } //出账
    
    public void credit(Long walletId, BigDecimal amount) { 
    	// ... 
    } //入账
    
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) { 
    	// ...
    } //转账
}
