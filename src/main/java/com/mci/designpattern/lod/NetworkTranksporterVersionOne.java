package com.mci.designpattern.lod;

/**
 * 	作为一个底层网络通信类，我们希望它的功能尽可能通用，而不只是服务于下载 HTML，所以，我们不应该直接依赖太具体的发送对象 HtmlRequest。
 * 	从这一点上讲，NetworkTransporter 类的设计违背迪米特法则，依赖了不该有直接依赖关系的 HtmlRequest 类。
 * 
 * @author Gzmar
 *
 */
public class NetworkTranksporterVersionOne {
    // 省略属性和其他方法...
    public Byte[] send(HtmlRequest htmlRequest) {
      //...
    }
}

public class HtmlDownloader {
	private NetworkTranksporterVersionOne transporter;// 通过构造函数或IOC注入

	public Html downloadHtml(String url) {
		Byte[] rawHtml = transporter.send(new HtmlRequest(url));
		return new Html(rawHtml);
	}
}

public class Document {
	private Html html;
	private String url;

	public Document(String url) {
		this.url = url;
		HtmlDownloader downloader = new HtmlDownloader();
		this.html = downloader.downloadHtml(url);
	}
	// ...
}

