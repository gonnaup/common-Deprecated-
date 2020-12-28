package org.gonnaup.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回数据格式
 *
 * @author gonnaup
 * @version 2020/12/11 11:18
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1295622123420374671L;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 私有构造器
     *
     * @param code
     * @param message
     * @param data
     */
    private Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回构建器
     * @param code
     * @return
     */
    public static ResultBuilder code(String code) {
        return new ResultBuilder().code(code);
    }

    /**
     * build
     */
    public static final class ResultBuilder {

        private String code;

        private String message;

        private ResultBuilder() {
        }

        /**
         * 设置返回码
         *
         * @param code
         * @return
         */
        private ResultBuilder code(String code) {
            this.code = code;
            return this;
        }

        /**
         * 设置返回消息
         *
         * @param message
         * @return
         */
        public ResultBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置成功消息
         *
         * @return
         */
        public ResultBuilder success() {
            this.message = Message.SUCCESS.description();
            return this;
        }

        /**
         * 设置失败消息
         *
         * @return
         */
        public ResultBuilder fail() {
            this.message = Message.FAIL.description();
            return this;
        }

        /**
         * 设置返回数据并返回结果对象
         *
         * @param data
         * @param <T>
         * @return 结果对象
         */
        public <T> Result<T> data(T data) {
            return new Result<>(code, message == null ? "" : message, data);
        }

    }

    public enum Message {
        SUCCESS("success"),
        FAIL("fail");

        private final String description;

        private Message(String description) {
            this.description = description;
        }

        public String description() {
            return this.description;
        }

    }

}
