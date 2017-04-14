package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.SubjectCategory;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.service.SubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author wangruyu
 * @since 2017/3/23-15:14
 */
@RestController
@RequestMapping("/subject")
public class SubjectCategoryController {
    @Autowired
    private SubjectCategoryService subjectCategoryService;
    @Autowired
    private LocalMessageSource localMessageSource;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<SubjectCategory> queryAll() {
        return subjectCategoryService.queryAll();
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public BaseMessage addSubjectCategory(@RequestBody SubjectCategory subjectCategory) {
        subjectCategory.setSubjectCategoryId(UUID.randomUUID().toString());
        if (subjectCategoryService.addSubjectCategory(subjectCategory)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.category.add.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subject.category.add.failed"));
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public BaseMessage modifySubjectCategory(@PathVariable("id") String id, @RequestBody SubjectCategory subjectCategory) {
        if (subjectCategoryService.modifySubjectCategory(subjectCategory)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.category.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subject.category.modify.failed"));
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public BaseMessage deleteSubjectCategory(@PathVariable("id") String id) {
        if (subjectCategoryService.deleteSubjectCategory(id)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("subject.category.delete.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("subject.category.delete.failed"));
    }

}
