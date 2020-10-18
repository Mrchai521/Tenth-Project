package com.tensquare.test.service.impl;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.test.Entity.DocTypeConfig;
import com.tensquare.test.Entity.DocTypeConfigVO;
import com.tensquare.test.dao.TestDao;
import com.tensquare.test.service.TestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: 柴新峰
 * @date: 2020-10-17 15:57
 * @description:
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public List<DocTypeConfigVO> search() {
        List<DocTypeConfigVO> docTypeConfigVOList = getAllNodes();
        return docTypeConfigVOList;
    }

    /**
     * 将DocTypeConfig转为VO
     *
     * @param list
     * @return
     */
    private List<DocTypeConfigVO> toVO(List<DocTypeConfig> list) {
        List<DocTypeConfigVO> docTypeConfigVOList = new ArrayList<>();
        DocTypeConfigVO docTypeConfigVO;
        for (DocTypeConfig docTypeConfig : list) {
            docTypeConfigVO = new DocTypeConfigVO();
            BeanUtils.copyProperties(docTypeConfig, docTypeConfigVO);
            docTypeConfigVOList.add(docTypeConfigVO);
        }
        return docTypeConfigVOList;
    }

    /**
     * 获取所有节点
     *
     * @return
     */
    public List<DocTypeConfigVO> getAllNodes() {
//        List<DocTypeConfig> pList = testDao.findAll();
        // 获取根节点
        List<DocTypeConfig> pList = getRootEntities();
        List<DocTypeConfigVO> pAllList = this.toVO(pList);

        List<DocTypeConfigVO> resultList = new ArrayList<>();
        for (DocTypeConfigVO item : pAllList) {
            DocTypeConfigVO v = getNodesAndChildren(item);
            if (v.getChildren().isEmpty() && !checkNodes(pAllList, v)) {
                continue;
            }
            resultList.add(item);
        }
        return resultList;
    }

    private List<DocTypeConfig> getRootEntities() {
        List<DocTypeConfig> pList = testDao.findByParentCode("DxDocument");
        return pList;
    }

    /**
     * 递归查找当前节点及其子节点
     *
     * @param
     * @param
     * @return
     */
    private DocTypeConfigVO getNodesAndChildren(DocTypeConfigVO docTypeConfigVO) {
        List<DocTypeConfigVO> childList = new ArrayList<>();
        if (docTypeConfigVO != null) {
            //获取所有子节点
            List<DocTypeConfigVO> allChildren = getChildren(docTypeConfigVO.getDocTypeCode());

            //查找子节点，递归程序必须要有一个出口
            List<DocTypeConfigVO> voList = getChildren(docTypeConfigVO.getDocTypeCode());
            for (DocTypeConfigVO item : allChildren) {
                DocTypeConfigVO child = getNodesAndChildren(item);
                if (child.getChildren().isEmpty()) {
                    continue;
                }
                childList.add(child);
            }
            docTypeConfigVO.setChildren(childList);
            return docTypeConfigVO;
        }
        return null;
    }

    /**
     * 获取子节点
     *
     * @param docTypeCode
     * @return
     */
    private List<DocTypeConfigVO> getChildren(String docTypeCode) {
        DocTypeConfig byDocTypeCode = testDao.findByDocTypeCode(docTypeCode);
        List<DocTypeConfig> list = new ArrayList<>();
        if (byDocTypeCode != null) {
            //还有子节点
            list.add(byDocTypeCode);
        }
        //查找子节点，递归程序必须要有一个出口
        List<DocTypeConfig> categoryList = testDao.findByParentCode(docTypeCode);
        for (DocTypeConfig categoryItem : categoryList) {
            getChildren(categoryItem.getDocTypeCode());
        }
        return this.toVO(list);
    }

    /**
     * 节点检查
     *
     * @param nodes
     * @param targetNode
     * @return
     */
    private Boolean checkNodes(List<DocTypeConfigVO> nodes, DocTypeConfigVO targetNode) {
        for (DocTypeConfigVO item : nodes) {
            if (item.getId().equals(targetNode.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 递归查询本节点的id和孩子节点的id
     *
     * @param
     * @return
     */
    public Result selectCategoryAndChildrenById(String docTypeCode) {
        Set<DocTypeConfig> categorySet = Sets.newHashSet();
        findChildCatgory(categorySet, docTypeCode);

        List<String> categoryList = Lists.newLinkedList();
        if (docTypeCode != null) {
            for (DocTypeConfig categoryItem : categorySet) {
                categoryList.add(categoryItem.getDocTypeCode());
            }
        }
        return new Result(true, StatusCode.OK, "查询成功", categoryList);
    }

    //递归算法，算出子节点
    public Set<DocTypeConfig> findChildCatgory(Set<DocTypeConfig> categorySet, String docTypeCode) {
        DocTypeConfig category = testDao.findByDocTypeCode(docTypeCode);
        if (category != null) {
            //还有子节点
            categorySet.add(category);
        }
        //查找子节点，递归程序必须要有一个出口
        List<DocTypeConfig> categoryList = testDao.findByParentCode(docTypeCode);
        for (DocTypeConfig categoryItem : categoryList) {
            findChildCatgory(categorySet, categoryItem.getDocTypeCode());
        }
        return categorySet;
    }
}
