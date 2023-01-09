package com.codegym.controller;

import com.codegym.dto.QuestionContentDto;
import com.codegym.model.QuestionContent;
import com.codegym.model.QuestionType;
import com.codegym.service.IQuestionService;
import com.codegym.service.IQuestionTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IQuestionTypeService questionTypeService;

    @GetMapping("/list")
    public String showList(@PageableDefault(size = 3) Pageable pageable,
                           @RequestParam(name = "title", defaultValue = "") String title,
                           @RequestParam(name = "questionType", defaultValue = "") String questionType, Model model) {
        Page<QuestionContent> questionContents = questionService.showList(title, questionType, pageable);
        model.addAttribute("questionTypeList", questionTypeService.findAll());
        model.addAttribute("questionContents", questionContents);
        return "list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        List<QuestionType> questionTypeList = questionTypeService.findAll();
        model.addAttribute("questionTypeList", questionTypeList);
        model.addAttribute("questionContents", new QuestionContent());
        model.addAttribute("questionContentDto", new QuestionContentDto());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("questionContentDto") QuestionContentDto questionContentDto,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        QuestionContent questionContent = new QuestionContent();
        if (bindingResult.hasErrors()) {
            List<QuestionType> questionTypeList = questionTypeService.findAll();
            model.addAttribute("questionTypeList", questionTypeList);
            return "create";
        }

        BeanUtils.copyProperties(questionContentDto, questionContent);
        questionService.save(questionContent);
        redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
        return "redirect:/question/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        questionService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
        return "redirect:/question/list";
    }
}
