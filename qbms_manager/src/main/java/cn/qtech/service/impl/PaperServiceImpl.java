package cn.qtech.service.impl;

import cn.qtech.domain.Paper;
import cn.qtech.mapper.PaperMapper;
import cn.qtech.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/27-15:29
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;

    @Override
    public List<Paper> queryAll() {
        return paperMapper.queryAll();
    }

    @Override
    public List<Paper> queryByUser(String userId) {
        return null;
    }

    @Override
    public boolean insert(Paper paper) {
        return paperMapper.insert(paper) > 0;
    }

    @Override
    public boolean delete(String id) {
        return paperMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean update(Paper paper) {
        return paperMapper.updateByPrimaryKey(paper) > 0;
    }

    @Override
    public boolean addBaseinfoForPaper(Paper paper) {
        return paperMapper.addBaseinfo(paper) > 0;
    }

    @Override
    public Paper queryById(String paperId) {
        return paperMapper.selectByPrimaryKey(paperId);
    }

    @Override
    public boolean addContent(String paperId, String content) {
        return paperMapper.addContent(paperId,content) > 0;
    }

    @Override
    public List<Paper> queryPapersByBatchPaperIds(List<String> userPaperIds) {
        return paperMapper.queryPapersByBatchPaperIds(userPaperIds);
    }
}
