package com.tensquare.base.service.impl;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: 柴新峰
 * @date: 2020-08-30 17:39
 * @description: 标签的service实现
 */
@Service
@Transactional
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelDao labelDao;

    @Override
    public void save(Label label) {
        labelDao.save(label);
    }

    @Override
    public void update(Label label) {
        labelDao.save(label);
    }

    @Override
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    @Override
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    @Override
    public Label findById(String id) {
        Optional<Label> label = labelDao.findById(id);
        if (label.isPresent() == true) {
            return label.get();
        }
        return null;
    }

    @Override
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 声明一个集合，用于存储所有条件
                ArrayList<Predicate> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    //根据标签的名称，模糊查询
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    // 等值查询标签的标签
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                // 创建一个数组，作为最终的返回值条件
                Predicate[] parr = new Predicate[list.size()];
                // 把list转为数组
                list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });
    }

    @Override
    public Page<Label> findPageSearch(Label label, int page, int size) {
        Pageable pageAble = PageRequest.of(page - 1, size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             * 方法名: toPredicate
             * 方法描述:  jpa的条件查询
             * 修改日期: 2019/1/7 19:20
             * @param root 根对象,要把条件封装到哪个对象中
             * @param criteriaQuery 封装查询的关键字. 比如group by order by
             * @param criteriaBuilder 封装条件对象 如果直接返回null,表示不封装任何的条件
             * @return javax.persistence.criteria.Predicate
             * @author taohongchao
             * @throws
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //声明一个集合，用户存储所有的条件
                ArrayList<Predicate> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    //根据标签名称模糊查询
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    //等值查询
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] prr = new Predicate[list.size()];
                list.toArray(prr);
                return criteriaBuilder.and(prr);
            }
        }, pageAble);
    }
}
