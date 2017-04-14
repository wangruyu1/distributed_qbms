package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.PaperCategory;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.service.PaperCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author wangruyu
 * @since 2017/3/21-13:47
 */
@RestController
@RequestMapping("/paper")
public class PaperCategoryController {
    @Autowired
    private PaperCategoryService paperCategoryService;
    @Autowired
    private LocalMessageSource messageSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaperCategoryController.class);

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public BaseMessage addCategory(@RequestBody PaperCategory paperCategory) {
        paperCategory.setCategoryId(UUID.randomUUID().toString());
        if (paperCategoryService.addCategory(paperCategory)) {
            return new BaseMessage(200, true, messageSource.getMessage("papercategory.add.success"));
        }
        return new BaseMessage(201, false, messageSource.getMessage("papercategory.add.failed"));
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public BaseMessage modifyCategory(@PathVariable("id") String id, @RequestBody PaperCategory paperCategory) {
        if (paperCategoryService.modifyCategory(paperCategory)) {
            return new BaseMessage(200, true, messageSource.getMessage("papercategory.modify.success"));
        }
        return new BaseMessage(201, false, messageSource.getMessage("papercategory.modify.failed"));
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public BaseMessage deleteCategory(@PathVariable("id") String id) {
        if (paperCategoryService.deleteCategory(id)) {
            return new BaseMessage(200, true, messageSource.getMessage("papercategory.delete.success"));
        }
        return new BaseMessage(201, false, messageSource.getMessage("papercategory.delete.failed"));
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<PaperCategory> queryCategories() {
        return paperCategoryService.queryCategories();
    }
}
