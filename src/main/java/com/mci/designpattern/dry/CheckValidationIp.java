package com.mci.designpattern.dry;

/*
 * 	而在这个例子中，尽管两段代码的实现逻辑不重复，但语义重复，也就是功能重复，我们认为它违反了 DRY 原则。
 * 	我们应该在项目中，统一一种实现思路，所有用到判断 IP 地址是否合法的地方，都统一调用同一个函数。
 * 
 */
public class CheckValidationIp {

	public boolean isValidIp(String ipAddress) {
		if (StringUtils.isBlank(ipAddress))
			return false;
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." 
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." 
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		return ipAddress.matches(regex);
	}

	public boolean checkIfIpValid(String ipAddress) {
		if (StringUtils.isBlank(ipAddress)) {
			return false;
		}
		String[] ipUnits = StringUtils.split(ipAddress, '.');
		
		if (ipUnits.length != 4) {
			return false;
		}
		
		for (int i = 0; i < 4; ++i) {
			int ipUnitIntValue;
			try {
				ipUnitIntValue = Integer.parseInt(ipUnits[i]);
			} catch (NumberFormatException e) {
				return false;
			}
			if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
				return false;
			}
			if (i == 0 && ipUnitIntValue == 0) {
				return false;
			}
		}
		
		return true;
	}
}
