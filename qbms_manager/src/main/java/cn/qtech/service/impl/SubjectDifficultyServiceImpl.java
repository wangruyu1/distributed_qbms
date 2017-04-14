package cn.qtech.service.impl;

import cn.qtech.domain.SubjectDifficulty;
import cn.qtech.mapper.SubjectDifficultyMapper;
import cn.qtech.service.SubjectDifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/22-14:27
 */
@Service
public class SubjectDifficultyServiceImpl implements SubjectDifficultyService {
    @Autowired
    private SubjectDifficultyMapper subjectDifficultyMapper;

    @Override
    public List<SubjectDifficulty> queryAll() {
        return subjectDifficultyMapper.queryAll();
    }

    @Override
    public boolean addSubjectDifficulty(SubjectDifficulty subjectDifficulty) {
        return subjectDifficultyMapper.insert(subjectDifficulty) > 0;
    }

    @Override
    public boolean modifySubjectDifficulty(SubjectDifficulty subjectDifficulty) {
        return subjectDifficultyMapper.updateByPrimaryKey(subjectDifficulty) > 0;
    }

    @Override
    public boolean deleteSubjectDifficulty(String id) {
        return subjectDifficultyMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<SubjectDifficulty> queryDifficultiesByBatchIds(List<String> difficultyIds) {
        return subjectDifficultyMapper.queryDifficultiesByBatchIds(difficultyIds);
    }
}
