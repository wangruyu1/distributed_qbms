package cn.qtech.service;

import cn.qtech.domain.SubjectCategory;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/23-15:24
 */
public interface SubjectCategoryService {
    public List<SubjectCategory> queryAll();

    boolean addSubjectCategory(SubjectCategory subjectCategory);

    boolean modifySubjectCategory(SubjectCategory subjectCategory);

    boolean deleteSubjectCategory(String id);

    SubjectCategory queryById(String subjectCategoryId);

    List<SubjectCategory> queryCategoriesByBatchIds(List<String> categoryIds);

}
