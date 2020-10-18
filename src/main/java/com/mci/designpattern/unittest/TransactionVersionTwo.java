package com.mci.designpattern.unittest;

/**
 * 	Ref.Ch.29
 * 	@author Gzmar
 *
 */
public class TransactionVersionTwo {
	
	public TransactionVersionTwo(String preAssignedId, Long buyerId, Long sellerId, Long productId, String orderId) {
		//...
		fillTransactionId(preAssignId);
		//...
	}

	/**
	 * 	构造函数中并非只包含简单赋值操作。交易 id 的赋值逻辑稍微复杂。我们最好也要测试一下，
	 * 	以保证这部分逻辑的正确性。为了方便测试，我们可以把 id 赋值这部分逻辑单独抽象到一个函数中
	 * @param preAssignedId
	 */
	protected void fillTransactionId(String preAssignedId) {
		if (preAssignedId != null && !preAssignedId.isEmpty()) {
			this.id = preAssignedId;
		} else {
			this.id = IdGenerator.generateTransactionId();
		}
		if (!this.id.startWith("t_")) {
			this.id = "t_" + preAssignedId;
		}
	}
	
	// ...
	// 添加一个成员变量及其set方法
	private WalletRpcService walletRpcService;

	public void setWalletRpcService(WalletRpcService walletRpcService) {
		this.walletRpcService = walletRpcService;
	}

	private TransactionLock lock;
	
	public void setTransactionLock(TransactionLock lock) {
		this.lock = lock;
	}
	
	protected boolean isExpired() { 
		long executionInvokedTimestamp = System.currentTimestamp(); 
		return executionInvokedTimestamp - createdTimestamp > 14days; 
	}
	
	// ...
	public boolean execute() throws InvalidTransactionException {
		// ...
		// 删除下面这一行代码
		// WalletRpcService walletRpcService = new WalletRpcService();
		// ...
		try {
			isLocked = lock.lock();
			//...
		} finally {
			if (isLocked) {
				lock.unlock();
			}
		}
		
		if (isExpired()) { 
			this.status = STATUS.EXPIRED; 
			return false; 
		}
	}
}



public void testExecute() {
	Long buyerId = 123L;
	Long sellerId = 234L;
	Long productId = 345L;
	Long orderId = 456L;

	TransactionLock mockLock = new TransactionLock() {
		public boolean lock(String id) {
			return true;
		}

		public void unlock() {
		}
	};

	Transction transaction = new Transaction(null, buyerId, sellerId, productId, orderId);
	transaction.setWalletRpcService(new MockWalletRpcServiceOne());
	transaction.setTransactionLock(mockLock);
	boolean executedResult = transaction.execute();
	assertTrue(executedResult);
	assertEquals(STATUS.EXECUTED, transaction.getStatus());
}

public class TransactionLock { 
	public boolean lock(String id) { 
		return RedisDistributedLock.getSingletonIntance().lockTransction(id); 
	} 
	
	public void unlock() { 
		RedisDistributedLock.getSingletonIntance().unlockTransction(id); 
	}
}


public void testExecute_with_TransactionIsExpired() {
	Long buyerId = 123L;
	Long sellerId = 234L;
	Long productId = 345L;
	Long orderId = 456L;
	
	TransactionVersionTwo transaction = new TransactionVersionTwo(null, buyerId, sellerId, productId, orderId) {
	    protected boolean isExpired() {
	    	return true;
	    }
	};
	boolean actualResult = transaction.execute();
	assertFalse(actualResult);
	assertEquals(STATUS.EXPIRED, transaction.getStatus());
}