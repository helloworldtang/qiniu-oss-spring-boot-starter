package com.tangcheng.qiniu.oss.autoconfigure;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UpCompletionHandler;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.StringMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author tangcheng
 * 2018/04/13
 */
public class QiniuUploadManager {

    private UploadManager uploadManager;
    /**
     * 上传凭证
     */
    private final String token;

    public QiniuUploadManager(UploadManager uploadManager, String token) {
        this.uploadManager = uploadManager;
        this.token = token;
    }

    /**
     * 上传字节数组
     *
     * @param data 上传的数据
     * @param key  上传数据保存的文件名
     */
    public Response put(final byte[] data, final String key) throws QiniuException {
        return uploadManager.put(data, key, token, null, null, false);
    }

    /**
     * 上传字节数组
     *
     * @param data     上传的数据
     * @param key      上传数据保存的文件名
     * @param params   自定义参数，如 params.put("x:foo", "foo")
     * @param mime     指定文件mimetype
     * @param checkCrc 是否验证crc32
     * @return
     * @throws QiniuException
     */
    public Response put(final byte[] data, final String key, StringMap params,
                        String mime, boolean checkCrc) throws QiniuException {
        return uploadManager.put(data, key, token, params, mime, checkCrc);
    }

    /**
     * 上传文件
     *
     * @param filePath 上传的文件路径
     * @param key      上传文件保存的文件名
     */
    public Response put(String filePath, String key) throws QiniuException {
        return uploadManager.put(filePath, key, token, null, null, false);
    }

    /**
     * 上传文件
     *
     * @param filePath 上传的文件路径
     * @param key      上传文件保存的文件名
     * @param params   自定义参数，如 params.put("x:foo", "foo")
     * @param mime     指定文件mimetype
     * @param checkCrc 是否验证crc32
     */
    public Response put(String filePath, String key, StringMap params,
                        String mime, boolean checkCrc) throws QiniuException {
        return uploadManager.put(new File(filePath), key, token, params, mime, checkCrc);
    }

    /**
     * 上传文件
     *
     * @param file 上传的文件对象
     * @param key  上传文件保存的文件名
     */
    public Response put(File file, String key) throws QiniuException {
        return uploadManager.put(file, key, token, null, null, false);
    }

    /**
     * 上传文件
     *
     * @param file     上传的文件对象
     * @param key      上传文件保存的文件名
     * @param mime     指定文件mimetype
     * @param checkCrc 是否验证crc32
     */
    public Response put(File file, String key, StringMap params,
                        String mime, boolean checkCrc) throws QiniuException {
        return uploadManager.put(file, key, token, params, mime, checkCrc);
    }

    /**
     * 异步上传数据
     *
     * @param data     上传的数据
     * @param key      上传数据保存的文件名
     * @param params   自定义参数，如 params.put("x:foo", "foo")
     * @param mime     指定文件mimetype
     * @param checkCrc 是否验证crc32
     * @param handler  上传完成的回调函数
     */
    public void asyncPut(final byte[] data, final String key, StringMap params,
                         String mime, boolean checkCrc, UpCompletionHandler handler) throws IOException {
        uploadManager.asyncPut(data, key, token, params, mime, checkCrc, handler);
    }

    /**
     * 流式上传，通常情况建议文件上传，可以使用持久化的断点记录。
     *
     * @param stream sha
     * @param key    上传文件保存的文件名
     * @param params 自定义参数，如 params.put("x:foo", "foo")
     * @param mime   指定文件mimetype
     */
    public Response put(InputStream stream, String key, StringMap params,
                        String mime) throws QiniuException {
        return uploadManager.put(stream, key, token, params, mime);
    }

}
