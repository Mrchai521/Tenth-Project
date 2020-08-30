package com.tensquare.base.service.impl;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
