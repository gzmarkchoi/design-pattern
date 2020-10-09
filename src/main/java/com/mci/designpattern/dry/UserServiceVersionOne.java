package com.mci.designpattern.dry;

public class UserServiceVersionOne {
	private UserRepo userRepo;// 通过依赖注入或者IOC框架注入

	/**
	 * 	login() 函数并不需要调用 checkIfUserExisted() 函数，只需要调用一次 getUserByEmail() 函数，
	 * 	从数据库中获取到用户的 email、password 等信息，然后跟用户输入的 email、password 信息做对比，依次判断是否登录成功。
	 * 
	 * 
	 * 	实际上，这样的优化是很有必要的。因为 checkIfUserExisted() 函数和 getUserByEmail() 函数都需要查询数据库，
	 * 	而数据库这类的 I/O 操作是比较耗时的。我们在写代码的时候，应当尽量减少这类 I/O 操作。
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public User login(String email, String password) {
		boolean existed = userRepo.checkIfUserExisted(email, password);
		if (!existed) {
			// ... throw AuthenticationFailureException...
		}
		User user = userRepo.getUserByEmail(email);
		return user;
	}
}

public class UserRepo {
	public boolean checkIfUserExisted(String email, String password) {
		if (!EmailValidation.validate(email)) {
			// ... throw InvalidEmailException...
		}

		if (!PasswordValidation.validate(password)) {
			// ... throw InvalidPasswordException...
		}

		// ...query db to check if email&password exists...
	}

	public User getUserByEmail(String email) {
		if (!EmailValidation.validate(email)) {
			// ... throw InvalidEmailException...
		}
		// ...query db to get user by email...
	}
}
