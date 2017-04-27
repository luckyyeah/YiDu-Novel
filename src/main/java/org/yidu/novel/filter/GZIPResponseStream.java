package org.yidu.novel.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <p>
 * GZIPResponseStream
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class GZIPResponseStream extends ServletOutputStream {
    /**
     * ByteArrayOutputStream
     */
    protected ByteArrayOutputStream baos = null;
    /**
     * GZIPOutputStream
     */
    protected GZIPOutputStream gzipstream = null;
    /**
     * 关闭标识
     */
    protected boolean closed = false;
    /**
     * HttpServletResponse
     */
    protected HttpServletResponse response = null;
    /**
     * ServletOutputStream
     */
    protected ServletOutputStream output = null;

    /**
     * 构造方法
     * 
     * @param response
     *            HttpServletResponse
     * @throws IOException
     *             IO异常
     */

    public GZIPResponseStream(HttpServletResponse response) throws IOException {
        super();
        closed = false;
        this.response = response;
        this.output = response.getOutputStream();
        baos = new ByteArrayOutputStream();
        gzipstream = new GZIPOutputStream(baos);
    }

    /**
     * 关闭所有流文件
     * 
     * @throws IOException
     *             IO异常
     */
    public void close() throws IOException {
        if (closed) {
            throw new IOException("This output stream has already been closed");
        }
        gzipstream.finish();

        byte[] bytes = baos.toByteArray();

        response.addHeader("Content-Length", Integer.toString(bytes.length));
        response.addHeader("Content-Encoding", "gzip");
        output.write(bytes);
        output.flush();
        output.close();
        closed = true;
    }

    /**
     * 将流内内容强制写出
     * 
     * @throws IOException
     *             IO异常
     */
    public void flush() throws IOException {
        if (closed) {
            throw new IOException("Cannot flush a closed output stream");
        }
        gzipstream.flush();
    }

    /**
     * 将整数写入流内
     * 
     * @param b
     *            整数
     * 
     * @throws IOException
     *             IO异常
     */
    public void write(int b) throws IOException {
        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }
        gzipstream.write((byte) b);
    }

    /**
     * 将字节数组写入流内
     * 
     * @param b
     *            字节数组
     * @throws IOException
     *             IO异常
     */
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    /**
     * 将字节数组中的指定长度写入流内
     * 
     * @param b
     *            字节数组
     * @param off
     *            开始地址
     * @param len
     *            要写入的长度
     * @throws IOException
     *             IO异常
     */
    public void write(byte[] b, int off, int len) throws IOException {
        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }
        gzipstream.write(b, off, len);
    }

    /**
     * 判断是否关闭了
     * 
     * @return 是否关闭了标识
     */
    public boolean closed() {
        return (this.closed);
    }

    /**
     * reset
     * 
     */
    public void reset() {
        // noop
    }

}