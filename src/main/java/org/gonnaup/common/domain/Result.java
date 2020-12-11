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
     * 创建构建器
     *
     * @param <T>
     * @return
     */
    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<>();
    }

    /**
     * build
     *
     * @param <T>
     */
    public static final class ResultBuilder<T> {
        private String code;
        private String message;
        private T data;

        private ResultBuilder() {
        }

        public ResultBuilder<T> withCode(String code) {
            this.code = code;
            return this;
        }

        public ResultBuilder<T> withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResultBuilder<T> success() {
            this.message = Message.SUCCESS.description();
            return this;
        }

        public ResultBuilder<T> fail() {
            this.message = Message.FAIL.description();
            return this;
        }

        public ResultBuilder<T> withData(T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            Result<T> result = new Result<T>();
            result.code = this.code == null ? "0" : this.code;
            result.message = this.message == null ? "" : this.message;
            result.data = this.data;
            return result;
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
