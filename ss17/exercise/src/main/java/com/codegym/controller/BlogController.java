package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public String listBlog(@PageableDefault(page = 0, size = 3) Pageable pageable, @RequestParam(name = "title", defaultValue = "") String title, Model model) {
        Page<Blog> blogs = blogService.findByTitleContaining(pageable, title);
        model.addAttribute("title", title);
//        Page<Blog> blogs = blogService.findAll(pageable);
        model.addAttribute("blogs", blogs);
        return "list";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("blogs", new Blog());
        model.addAttribute("categories", categories);
        return "create";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute("blogs") Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
        return "redirect:/blog/list";
    }

    @GetMapping("/edit")
    public String formEdit(@RequestParam("id") int id, Model model) {
        List<Category> categories = categoryService.findAll();
        Blog blog = blogService.findById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("blogs", blog);
        return "edit";
    }

    @PostMapping("/edit")
    public String editBlog(@ModelAttribute("blogs") Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("msg", "Chỉnh sửa thành công");
        return "redirect:/blog/list";
    }

    @PostMapping("/delete")
    public String deleteBolog(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        blogService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
        return "redirect:/blog/list";
    }

    @PostMapping("/view")
    public String viewBlog(@RequestParam("id") int id, Model model) {
        model.addAttribute("blogs", blogService.findById(id));
        return "list";
    }
}
