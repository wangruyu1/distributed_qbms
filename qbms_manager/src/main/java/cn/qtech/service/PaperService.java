package cn.qtech.service;

import cn.qtech.domain.Paper;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/27-15:28
 */
public interface PaperService extends BaseService<Paper> {
    boolean addBaseinfoForPaper(Paper paper);

    Paper queryById(String paperId);

    boolean addContent(String paperId, String content);

    List<Paper> queryPapersByBatchPaperIds(List<String> userPaperIds);
}
