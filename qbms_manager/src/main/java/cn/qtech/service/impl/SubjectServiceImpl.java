package cn.qtech.service.impl;

import cn.qtech.domain.SubjectWithBLOBs;
import cn.qtech.mapper.SubjectMapper;
import cn.qtech.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/23-16:46
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;


    @Override
    public boolean insert(SubjectWithBLOBs subjectWithBLOBs) {
        return subjectMapper.insert(subjectWithBLOBs) > 0;
    }

    @Override
    public boolean delete(String id) {
        return subjectMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean update(SubjectWithBLOBs subjectWithBLOBs) {
        return subjectMapper.updateByPrimaryKey(subjectWithBLOBs) > 0;
    }

    @Override
    public List<SubjectWithBLOBs> queryAll() {
        return subjectMapper.queryAll();
    }

    @Override
    public List<SubjectWithBLOBs> queryByCategory(String categoryId) {
        return subjectMapper.queryByCategory(categoryId);
    }

    @Override
    public List<SubjectWithBLOBs> queryByUser(String userId) {
        return subjectMapper.queryByUser(userId);
    }

    @Override
    public List<SubjectWithBLOBs> queryByDifficulty(String id) {
        return subjectMapper.queryByDifficulty(id);
    }

    @Override
    public boolean updateBasic(String name,String subjectCategoryId, String subjectDifficultyId, String subjectId) {
        return subjectMapper.updateBasic(name,subjectCategoryId, subjectDifficultyId, subjectId);
    }

    @Override
    public boolean updateContent(String content, String subjectId) {
        return subjectMapper.updateContent(content,subjectId);
    }

    @Override
    public boolean updateAnswer(String answer, String subjectId) {
        return subjectMapper.updateAnswer(answer,subjectId);
    }

    @Override
    public List<SubjectWithBLOBs> querySubjectsByBatch(List<String> realIdList) {
        return subjectMapper.querySubjectsByBatch(realIdList);
    }

    @Override
    public int getCountByCategoryAndDifficulty(String categoryId, String difficultyId) {
        return subjectMapper.getCountByCategoryAndDifficulty(categoryId,difficultyId);
    }

    @Override
    public List<SubjectWithBLOBs> queryAllByCategoryAndDifficulty(String categoryId, String difficultyId) {
        return subjectMapper.queryAllByCategoryAndDifficulty(categoryId,difficultyId);
    }
}
