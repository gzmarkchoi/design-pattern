package com.mci.designpattern.lod;

import java.util.List;
import java.util.Map;

/**
 * 	这个 Serialization 类来说，只包含两个操作，确实没有太大必要拆分成两个接口。
 * 	但是，如果我们对 Serialization 类添加更多的功能，实现更多更好用的序列化、反序列化函数，我们来重新考虑一下这个问题。
 * 
 * 
 * 	如果我们既不想违背高内聚的设计思想，也不想违背迪米特法则，那我们该如何解决这个问题呢？实际上，通过引入两个接口就能轻松解决这个问题
 * @author Gzmar
 *
 */

interface Serializable {
	String serialize(Object object);
	String serializeMap(Map<String, String> map);
	String serializeList(List<String> map);
}

interface Deserializable {
	Object deserialize(String text);
	Map deserializeMap(String mapString);
	List deserializeList(String listString);
}

public class Serialization implements Serializable, Deserializable {
	@Override
	public String serialize(Object object) {
		String serializedResult;
		// ...
		return serializedResult;
	}

	@Override
	public Object deserialize(String str) {
		Object deserializedResult;
		// ...
		return deserializedResult;
	}
}

public class DemoClass_1 {
	private Serializable serializer;

	public Demo(Serializable serializer) {
    this.serializer = serializer;
  }
	// ...
}

public class DemoClass_2 {
	private Deserializable deserializer;

	public Demo(Deserializable deserializer) {
    this.deserializer = deserializer;
  }
	// ...
}