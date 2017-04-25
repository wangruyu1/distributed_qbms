package cn.qtech.rabbitmq;

import cn.qtech.constant.UserPaperStatus;
import cn.qtech.domain.MakePaper;
import cn.qtech.domain.Paper;
import cn.qtech.domain.UserPaper;
import cn.qtech.domain.UserPaperWithBLOBs;
import cn.qtech.domain.dto.MakePaperDTO;
import cn.qtech.service.UserPaperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RabbitReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitReceiver.class);
    @Autowired
    private UserPaperService userPaperService;

    @StreamListener(Source.MANAGER_INPUT)
    public void receiver(Message<Object> message) {
        LOGGER.info("收到来自manager消息...");
        ObjectMapper objectMapper = new ObjectMapper();
        MakePaperDTO makePaperDTO = null;
        try {
            makePaperDTO = objectMapper.readValue(message.getPayload().toString(), MakePaperDTO.class);
        } catch (IOException e) {
            LOGGER.error("转化makePaperDTO数据失败...");
            e.printStackTrace();
        }
        //向数据库插入试卷信息
        List<String> userIds = makePaperDTO.getUserIds();
        if (userIds == null || userIds.size() == 0) {
            LOGGER.info("该manager下没有用户可用...");
            return;
        }
        Paper paper = makePaperDTO.getPaper();
        MakePaper makePaper = makePaperDTO.getMakePaper();
        String managerId = makePaperDTO.getManagerId();
        List<UserPaperWithBLOBs> userPapers = new ArrayList<>();
        userIds.forEach(id -> {
            UserPaperWithBLOBs userPaper = new UserPaperWithBLOBs();
            userPaper.setId(UUID.randomUUID().toString());
            userPaper.setTotalTime(makePaper.getTotalTime());
            userPaper.setContent(paper.getContent());
            userPaper.setName(paper.getName());
            userPaper.setPaperId(paper.getPaperId());
            userPaper.setStartTime(makePaper.getStartTime());
            userPaper.setStatus(UserPaperStatus.NOTBEGIN.value());
            userPaper.setTitle(paper.getTitle());
            userPaper.setScore(-1);
            userPaper.setUserId(id);
            userPaper.setManagerId(managerId);
            userPapers.add(userPaper);
        });
        if (!userPaperService.insertUserPapersByBatch(userPapers)) {
            //插入失败可以重试.
            LOGGER.error("插入userpaper数据失败...");
        }
    }
}  

