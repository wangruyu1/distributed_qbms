package cn.qtech.service.impl;

import cn.qtech.domain.SubjectCategory;
import cn.qtech.mapper.SubjectCategoryMapper;
import cn.qtech.service.SubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/23-15:24
 */
@Service
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Autowired
    private SubjectCategoryMapper subjectCategoryMapper;

    @Override
    public List<SubjectCategory> queryAll() {
        return subjectCategoryMapper.queryAll();
    }

    @Override
    public boolean addSubjectCategory(SubjectCategory subjectCategory) {
        return subjectCategoryMapper.insert(subjectCategory) > 0;
    }

    @Override
    public boolean modifySubjectCategory(SubjectCategory subjectCategory) {
        return subjectCategoryMapper.updateByPrimaryKey(subjectCategory) > 0;
    }

    @Override
    public boolean deleteSubjectCategory(String id) {
        return subjectCategoryMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public SubjectCategory queryById(String subjectCategoryId) {
        return subjectCategoryMapper.selectByPrimaryKey(subjectCategoryId);
    }

    @Override
    public List<SubjectCategory> queryCategoriesByBatchIds(List<String> categoryIds) {
        return subjectCategoryMapper.queryCategoriesByBatchIds(categoryIds);
    }

}
