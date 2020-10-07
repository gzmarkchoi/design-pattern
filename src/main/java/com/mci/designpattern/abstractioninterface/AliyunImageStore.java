package com.mci.designpattern.abstractioninterface;

import java.awt.*;

/*
    首先，AliyunImageStore 类中有些函数命名暴露了实现细节，比如，uploadToAliyun() 和 downloadFromAliyun()。
    如果开发这个功能的同事没有接口意识、抽象思维，那这种暴露实现细节的命名方式就不足为奇了，毕竟最初我们只考虑将图片存储在阿里云上。
    而我们把这种包含“aliyun”字眼的方法，照抄到 PrivateImageStore 类中，显然是不合适的。
    如果我们在新类中重新命名 uploadToAliyun()、downloadFromAliyun() 这些方法，那就意味着，我们要修改项目中所有使用到这两个方法的代码，
    代码修改量可能就会很大。

    其次，将图片存储到阿里云的流程，跟存储到私有云的流程，可能并不是完全一致的。比如，阿里云的图片上传和下载的过程中，需要生产 access token，而私有云不需要 access token。
    一方面，AliyunImageStore 中定义的 generateAccessToken() 方法不能照抄到 PrivateImageStore 中；
    另一方面，我们在使用 AliyunImageStore 上传、下载图片的时候，代码中用到了 generateAccessToken() 方法，如果要改为私有云的上传下载流程，这些代码都需要做调整。

    Ref Ch.09

    Not a good design in this class, better one in ImageStore class
 */
public class AliyunImageStore {
    //...

    public void createBucketIfNotExisting(String bucketName) {
        // ...
    }

    public String generateAccessToken() {
        // ...
    }

    public String uploadToAliyun(Image image, String bucketName, String accessToken) {
        //...
    }

    public Image downloadFromAliyun(String url, String accessToken) {
        //...
    }
}

// AliyunImageStore类的使用举例
public class ImageProcessingJob {
    private static final String BUCKET_NAME = "ai_images_bucket";
    //...

    public void process() {
        Image image = null; //处理图片，并封装为Image对象
        AliyunImageStore imageStore = new AliyunImageStore(/*省略参数*/);
        imageStore.createBucketIfNotExisting(BUCKET_NAME);
        String accessToken = imageStore.generateAccessToken();
        imageStore.uploadToAliyun(image, BUCKET_NAME, accessToken);
    }

}
