package com.mci.designpattern.dry;

/**
 * 	从代码实现逻辑上看起来是重复的，但是从语义上并不重复。所谓“语义不重复”指的是：从功能上来看，这两个函数干的是完全不重复的两件事情，
 * 	一个是校验用户名，另一个是校验密码。尽管在目前的设计中，两个校验逻辑是完全一样的，但如果按照第二种写法，将两个函数的合并，那就会存在潜在的问题。
 * 	在未来的某一天，如果我们修改了密码的校验逻辑，比如，允许密码包含大写字符，允许密码的长度为 8 到 64 个字符，那这个时候，
 * 	isValidUserName() 和 isValidPassword() 的实现逻辑就会不相同。我们就要把合并后的函数，重新拆成合并前的那两个函数。
 * @author Gzmar
 *
 */
public class UserAuthenticator {
	public void authenticate(String username, String password) {
		if (!isValidUsername(username)) {
			// ...throw InvalidUsernameException...
		}
		if (!isValidPassword(password)) {
			// ...throw InvalidPasswordException...
		}
		// ...省略其他代码...
	}

	private boolean isValidUsername(String username) {
		// check not null, not empty
		if (StringUtils.isBlank(username)) {
			return false;
		}
		// check length: 4~64
		int length = username.length();
		if (length < 4 || length > 64) {
			return false;
		}
		// contains only lowcase characters
		if (!StringUtils.isAllLowerCase(username)) {
			return false;
		}
		// contains only a~z,0~9,dot
		for (int i = 0; i < length; ++i) {
			char c = username.charAt(i);
			if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
				return false;
			}
		}
		return true;
	}

	private boolean isValidPassword(String password) {
		// check not null, not empty
		if (StringUtils.isBlank(password)) {
			return false;
		}
		// check length: 4~64
		int length = password.length();
		if (length < 4 || length > 64) {
			return false;
		}
		// contains only lowcase characters
		if (!StringUtils.isAllLowerCase(password)) {
			return false;
		}
		// contains only a~z,0~9,dot
		for (int i = 0; i < length; ++i) {
			char c = password.charAt(i);
			if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
				return false;
			}
		}
		return true;
	}
}
