package com.mci.designpattern.lod;

public class NetworkTranksporterVersionTwo {
    // 省略属性和其他方法...
    public Byte[] send(String address, Byte[] data) {
      //...
    }
}


public class HtmlDownloader {
	private NetworkTranksporterVersionTwo transporter;// 通过构造函数或IOC注入

	// HtmlDownloader这里也要有相应的修改
	public Html downloadHtml(String url) {
		HtmlRequest htmlRequest = new HtmlRequest(url);
		Byte[] rawHtml = transporter.send(htmlRequest.getAddress(), htmlRequest.getContent().getBytes());
		return new Html(rawHtml);
	}
}


public class Document {
	private Html html;
	private String url;

	public Document(String url, Html html) {
		this.html = html;
		this.url = url;
	}
	// ...
}

// 通过一个工厂方法来创建Document
public class DocumentFactory {
	private HtmlDownloader downloader;

	public DocumentFactory(HtmlDownloader downloader) {
		this.downloader = downloader;
	}

	public Document createDocument(String url) {
		Html html = downloader.downloadHtml(url);
		return new Document(url, html);
	}
}