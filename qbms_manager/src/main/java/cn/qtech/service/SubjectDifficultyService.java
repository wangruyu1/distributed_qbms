package cn.qtech.service;

import cn.qtech.domain.SubjectDifficulty;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/22-14:26
 */
public interface SubjectDifficultyService {
    public List<SubjectDifficulty> queryAll();

    boolean addSubjectDifficulty(SubjectDifficulty subjectDifficulty);

    boolean modifySubjectDifficulty(SubjectDifficulty subjectDifficulty);

    boolean deleteSubjectDifficulty(String id);

    List<SubjectDifficulty> queryDifficultiesByBatchIds(List<String> difficultyIds);
}
