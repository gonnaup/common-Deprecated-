package org.gonnaup.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页参数对象
 *
 * @author gonnaup
 * @version 1.0
 * @Created on 2020/11/10 10:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Pageable {

    /**
     * 页码号
     */
    private int page;

    /**
     * 查询条数
     */
    private int size;

    /**
     * 计算偏移量
     *
     * @return
     */
    public int getOffset() {
        return (page - 1) * size;
    }

}