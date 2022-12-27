package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Order;
import com.codegym.service.IBookService;
import com.codegym.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/list")
    public String showList(@PageableDefault(size = 3) Pageable pageable, Model model) {
        Page<Book> books = bookService.findAll(pageable);
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/detail")
    public String showDetail(@RequestParam("id") int id, Model model) throws Exception {
        Book book = bookService.findById(id);
        if (book.getQuantity() == 0) {
            throw new Exception();
        }
        model.addAttribute("book", book);
        return "detail";
    }

    @PostMapping("/detail")
    public String order(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        Order order = new Order();
        int code = (int) (Math.random() * (99999 - 10000) + 10000);
        order.setCode(code);
        book.setQuantity(book.getQuantity() - 1);
        orderService.save(order);
        bookService.save(book);
        redirectAttributes.addFlashAttribute("msg", "Mã sách: " + order.getCode());
        return "redirect:/book/code";
    }

    @GetMapping("/code")
    public String code() {
        return "code";
    }

    @GetMapping("/pay")
    public String showPay(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "pay";
    }

    @PostMapping("/pay")
    public String pay(@RequestParam("id") Integer id, @RequestParam("code") Integer code, RedirectAttributes redirectAttributes) throws Exception {
        Book book = bookService.findById(id);
        Set<Order> orderBooks = book.getOrders();
        List<Order> orderBookList = new ArrayList<>();
        for (Order x : orderBooks) {
            orderBookList.add(x);
        }
        Integer borrowCode2 = orderBookList.get(0).getCode();
        if (borrowCode2.equals(code)) {
            Order orderBook = orderBookList.get(0);
            orderBook.setCode(0);
            orderService.save(orderBook);
            book.setQuantity(book.getQuantity() + 1);
            bookService.save(book);
            redirectAttributes.addFlashAttribute("mess", "Ban Da Tra Sach Thanh Cong");
            return "redirect:/list";
        }
        return "error";
    }
}
