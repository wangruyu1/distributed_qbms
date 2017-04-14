package cn.qtech.service;

import cn.qtech.domain.PaperCategory;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/21-14:27
 */
public interface PaperCategoryService {
    public boolean addCategory(PaperCategory paperCategory);

    List<PaperCategory> queryCategories();

    boolean modifyCategory(PaperCategory paperCategory);

    boolean deleteCategory(String id);

    List<PaperCategory> queryCategoryByBatchIds(List<String> categoryIds);
}
