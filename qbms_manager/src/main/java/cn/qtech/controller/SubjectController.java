package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.SubjectCategory;
import cn.qtech.domain.SubjectDifficulty;
import cn.qtech.domain.SubjectWithBLOBs;
import cn.qtech.domain.User;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.domain.dto.SubjectDTO;
import cn.qtech.service.SubjectCategoryService;
import cn.qtech.service.SubjectDifficultyService;
import cn.qtech.service.SubjectService;
import cn.qtech.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author wangruyu
 * @since 2017/3/23-16:45
 */
@RestController
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private LocalMessageSource localMessageSource;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SubjectCategoryService subjectCategoryService;
    @Autowired
    private SubjectDifficultyService subjectDifficultyService;

    @RequestMapping(value = "subjects", method = RequestMethod.GET)
//    @Cacheable(key = "'subjects'",value = "subject")
    public List<SubjectDTO> queruAll() {
        List<SubjectDTO> rtnData = new ArrayList<>();
        List<SubjectWithBLOBs> subjects = subjectService.queryAll();
        List<String> categoryIds = subjects.stream().map(SubjectWithBLOBs::getSubjectCategoryId).collect(Collectors.toList());
        List<String> difficultyIds = subjects.stream().map(SubjectWithBLOBs::getSubjectDifficultyId).collect(Collectors.toList());
        List<SubjectCategory> categories = subjectCategoryService.queryCategoriesByBatchIds(categoryIds);
        List<SubjectDifficulty> difficulties = subjectDifficultyService.queryDifficultiesByBatchIds(difficultyIds);
        Map<String, String> categoryNames = categories.stream().collect(Collectors.toMap(SubjectCategory::getSubjectCategoryId, SubjectCategory::getName));
        Map<String, String> difficultyNames = difficulties.stream().collect(Collectors.toMap(SubjectDifficulty::getSubjectId, SubjectDifficulty::getName));
        subjects.forEach(s -> {
            SubjectDTO subject = new SubjectDTO();
            subject.setSubjectId(s.getSubjectId());
            subject.setSubjectCategoryId(s.getSubjectCategoryId());
            subject.setCategoryId(s.getSubjectCategoryId());
            subject.setCategoryName(categoryNames.get(s.getSubjectCategoryId()));
            subject.setDifficultyId(s.getSubjectDifficultyId());
            subject.setSubjectDifficultyId(s.getSubjectDifficultyId());
            subject.setDifficultyName(difficultyNames.get(s.getSubjectDifficultyId()));
            subject.setContent(s.getContent());
            subject.setAnswer(s.getAnswer());
            subject.setName(s.getName());
            subject.setUserId(s.getUserId());
            rtnData.add(subject);
        });
        return rtnData;
    }

    @RequestMapping(value = "subjects/user={userId}", method = RequestMethod.GET)
    public List<SubjectWithBLOBs> queruAllByUser(@PathVariable("userId") String userId) {
        return subjectService.queryByUser(userId);
    }

    @RequestMapping(value = "subjects/category={categoryId}", method = RequestMethod.GET)
    public List<SubjectWithBLOBs> queruAllByCategory(@PathVariable("categoryId") String categoryId) {
        return subjectService.queryByCategory(categoryId);
    }

    @RequestMapping(value = "subjects/difficulty={difficultyId}", method = RequestMethod.GET)
    public List<SubjectWithBLOBs> queruAllByDifficulty(@PathVariable("difficultyId") String difficultyId) {
        return subjectService.queryByDifficulty(difficultyId);
    }

    @RequestMapping(value = "subject", method = RequestMethod.POST)
    public BaseMessage addSubject(@RequestBody SubjectWithBLOBs subjectWithBLOBs) {
        User user = LoginUtil.getLoginUser();
        subjectWithBLOBs.setSubjectId(UUID.randomUUID().toString());
        subjectWithBLOBs.setUserId(user.getId());
        if (subjectService.insert(subjectWithBLOBs)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.add.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjec.add.failed"));
    }

    @RequestMapping(value = "subject/{id}", method = RequestMethod.PUT)
    public BaseMessage modifySubject(@PathVariable("id") String id, @RequestBody SubjectWithBLOBs subjectWithBLOBs) {
//        User user = LoginUtil.getLoginUser();
        if (subjectService.update(subjectWithBLOBs)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjec.modify.failed"));
    }

    @RequestMapping(value = "subject/{id}/basic", method = RequestMethod.PUT)
    public BaseMessage modifyBasicForSubject(@PathVariable("id") String id, @RequestBody SubjectWithBLOBs subjectWithBLOBs) {
        if (subjectService.updateBasic(subjectWithBLOBs.getName(), subjectWithBLOBs.getSubjectCategoryId(), subjectWithBLOBs.getSubjectDifficultyId(), subjectWithBLOBs.getSubjectId())) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjec.modify.failed"));
    }

    @RequestMapping(value = "subject/{id}/content", method = RequestMethod.PUT)
    public BaseMessage modifyContentForSubject(@PathVariable("id") String id, @RequestBody SubjectWithBLOBs subjectWithBLOBs) {
        if (subjectService.updateContent(subjectWithBLOBs.getContent(), subjectWithBLOBs.getSubjectId())) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjec.modify.failed"));
    }

    @RequestMapping(value = "subject/{id}/answer", method = RequestMethod.PUT)
    public BaseMessage modifyAnswerForSubject(@PathVariable("id") String id, @RequestBody SubjectWithBLOBs subjectWithBLOBs) {
        if (subjectService.updateAnswer(subjectWithBLOBs.getAnswer(), subjectWithBLOBs.getSubjectId())) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjec.modify.failed"));
    }

    @RequestMapping(value = "subject/{id}", method = RequestMethod.DELETE)
    public BaseMessage deleteSubject(@PathVariable("id") String id) {
        if (subjectService.delete(id)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.delete.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjec.delete.failed"));
    }
}
