package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.SubjectDifficulty;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.service.SubjectDifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author wangruyu
 * @since 2017/3/22-14:25
 */
@RestController
@RequestMapping("/paper")
public class SubjectDifficultyController {
    @Autowired
    private SubjectDifficultyService subjectDifficultyService;
    @Autowired
    private LocalMessageSource localMessageSource;

    @RequestMapping(value = "/subjectdifficulties", method = RequestMethod.GET)
    public List<SubjectDifficulty> queryAll() {
        return subjectDifficultyService.queryAll();
    }

    @RequestMapping(value = "/subjectdifficulty", method = RequestMethod.POST)
    public BaseMessage addSubjectDifficulty(@RequestBody SubjectDifficulty subjectDifficulty) {
        subjectDifficulty.setSubjectId(UUID.randomUUID().toString());
        if (subjectDifficultyService.addSubjectDifficulty(subjectDifficulty)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subjectdifficulty.add.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjectdifficulty.add.failed"));
    }

    @RequestMapping(value = "/subjectdifficulty/{id}", method = RequestMethod.PUT)
    public BaseMessage modifySubjectDifficulty(@PathVariable("id") String id, @RequestBody SubjectDifficulty subjectDifficulty) {
        if (subjectDifficultyService.modifySubjectDifficulty(subjectDifficulty)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subjectdifficulty.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjectdifficulty.modify.failed"));
    }

    @RequestMapping(value = "/subjectdifficulty/{id}", method = RequestMethod.DELETE)
    public BaseMessage deleteSubjectDifficulty(@PathVariable("id") String id) {
        if (subjectDifficultyService.deleteSubjectDifficulty(id)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subjectdifficulty.delete.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subjectdifficulty.delete.failed"));
    }

}
