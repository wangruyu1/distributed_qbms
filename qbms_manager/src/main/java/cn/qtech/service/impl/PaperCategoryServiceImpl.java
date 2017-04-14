package cn.qtech.service.impl;

import cn.qtech.domain.PaperCategory;
import cn.qtech.mapper.PaperCategoryMapper;
import cn.qtech.service.PaperCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/21-14:28
 */
@Service
public class PaperCategoryServiceImpl implements PaperCategoryService {
    @Autowired
    private PaperCategoryMapper paperCategoryMapper;

    @Override
    public boolean addCategory(PaperCategory paperCategory) {
        return paperCategoryMapper.insert(paperCategory) > 0;
    }

    @Override
    public List<PaperCategory> queryCategories() {
        return paperCategoryMapper.queryAll();
    }

    @Override
    public boolean modifyCategory(PaperCategory paperCategory) {
        return paperCategoryMapper.updateByPrimaryKey(paperCategory) > 0;
    }

    @Override
    public boolean deleteCategory(String id) {
        return paperCategoryMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<PaperCategory> queryCategoryByBatchIds(List<String> categoryIds) {
        return paperCategoryMapper.queryCategoryByBatchIds(categoryIds);
    }
}
