package com.mci.designpattern.practice.refactoring;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 	第二轮重构：提高代码的可测试性
 * 
 * 	- generate() 函数定义为静态函数，会影响使用该函数的代码的可测试性；
 * 	- generate() 函数的代码实现依赖运行环境（本机名）、时间函数、随机函数，所以 generate() 函数本身的可测试性也不好。
 * 		- 从 getLastfieldOfHostName() 函数中，将逻辑比较复杂的那部分代码剥离出来，定义为 getLastSubstrSplittedByDot() 函数。
 * 		因为 getLastfieldOfHostName() 函数依赖本地主机名，所以，剥离出主要代码之后这个函数变得非常简单，可以不用测试。
 * 		我们重点测试 getLastSubstrSplittedByDot() 函数即可。
 * 		- 将 generateRandomAlphameric() 和 getLastSubstrSplittedByDot() 这两个函数的访问权限设置为 protected。
 * 		这样做的目的是，可以直接在单元测试中通过对象来调用两个函数进行测试。
 * 		- 给 generateRandomAlphameric() 和 getLastSubstrSplittedByDot() 两个函数添加 Google Guava 
 * 		的 annotation @VisibleForTesting。这个 annotation 没有任何实际的作用，只起到标识的作用，告诉其他人说，
 * 		这两个函数本该是 private 访问权限的，之所以提升访问权限到 protected，只是为了测试，只能用于单元测试中。
 * 
 * @author Gzmar
 *
 */
public class RandomIdGeneratorVersionTwo implements LogTraceIdGenerator {
	private static final Logger logger = LoggerFactory.getLogger(RandomIdGenerator.class);

	@Override
	public String generate() {
		String substrOfHostName = getLastfieldOfHostName();
		long currentTimeMillis = System.currentTimeMillis();
		String randomString = generateRandomAlphameric(8);
		String id = String.format("%s-%d-%s", substrOfHostName, currentTimeMillis, randomString);
		
		return id;
	}

	private String getLastfieldOfHostName() {
		String substrOfHostName = null;
		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			substrOfHostName = getLastSubstrSplittedByDot(hostName);
		} catch (UnknownHostException e) {
			logger.warn("Failed to get the host name.", e);
		}
		
		return substrOfHostName;
	}

	@VisibleForTesting
	protected String getLastSubstrSplittedByDot(String hostName) {
		String[] tokens = hostName.split("\\.");
		String substrOfHostName = tokens[tokens.length - 1];
		
		return substrOfHostName;
	}

	@VisibleForTesting
	protected String generateRandomAlphameric(int length) {
		char[] randomChars = new char[length];
		int count = 0;
		Random random = new Random();
		
		while (count < length) {
			int maxAscii = 'z';
			int randomAscii = random.nextInt(maxAscii);
			boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
			boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
			boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
			
			if (isDigit || isUppercase || isLowercase) {
				randomChars[count] = (char) (randomAscii);
				++count;
			}
		}
		
		return new String(randomChars);
	}
}