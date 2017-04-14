package cn.qtech.service;

import cn.qtech.domain.SubjectWithBLOBs;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/23-16:45
 */
public interface SubjectService extends BaseService<SubjectWithBLOBs> {
    /**
     * 查询所有
     *
     * @return
     */
    public List<SubjectWithBLOBs> queryAll();

    /**
     * 根据分类查询
     *
     * @param categoryId
     * @return
     */
    public List<SubjectWithBLOBs> queryByCategory(String categoryId);

    /**
     * 根据用户查询
     *
     * @param userId
     * @return
     */
    public List<SubjectWithBLOBs> queryByUser(String userId);

    /**
     * 根据困难度查询
     *
     * @param id
     * @return
     */
    public List<SubjectWithBLOBs> queryByDifficulty(String id);

    boolean updateBasic(String name, String subjectCategoryId, String subjectDifficultyId, String subjectId);

    boolean updateContent(String content, String subjectId);

    boolean updateAnswer(String answer, String subjectId);

    List<SubjectWithBLOBs> querySubjectsByBatch(List<String> realIdList);

    int getCountByCategoryAndDifficulty(String categoryId, String difficultyId);

    List<SubjectWithBLOBs> queryAllByCategoryAndDifficulty(String categoryId, String difficultyId);
}
