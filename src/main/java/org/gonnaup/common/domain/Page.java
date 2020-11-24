package org.gonnaup.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  分页返回数据
 * @author gonnaup
 * @version 1.0
 * @Created on 2020/11/10 10:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Page<T> {

    private static final long serialVersionUID = 2866879623140967599L;

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 数据总条数
     */
    private int total;

}
