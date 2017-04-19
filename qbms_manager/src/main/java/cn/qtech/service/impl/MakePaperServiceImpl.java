package cn.qtech.service.impl;

import cn.qtech.domain.MakePaper;
import cn.qtech.mapper.MakePaperMapper;
import cn.qtech.service.MakePaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/17-18:46
 */
@Service
public class MakePaperServiceImpl implements MakePaperService {
    @Autowired
    private MakePaperMapper makePaperMapper;

    @Override
    public List<MakePaper> queryAll() {
        return null;
    }

    @Override
    public List<MakePaper> queryByUser(String userId) {
        return null;
    }

    @Override
    public boolean insert(MakePaper makePaper) {
        return makePaperMapper.insert(makePaper) > 0;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(MakePaper makePaper) {
        return false;
    }
}
