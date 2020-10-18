package com.tensquare.test.Entity;


import java.lang.*;
import java.util.*;

import lombok.*;

/**
 * @author Administrator
 * @description:
 * @version: 1
 * @date: 2020-10-15 04:58:06
 **/
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class DocTypeConfigVO  {
    private Long id;
    /**
     * String
     */
    private String docTypeCode;

    /**
     * String
     */
    private String docTypeName;

    /**
     * String
     */
    private String parentCode;

    /**
     * String
     */
    private String typeNumber;


    private Map<String, Object> dynamicAttrs;

    /**
     * 孩子节点集合
     */
    private List<DocTypeConfigVO> children;
}

