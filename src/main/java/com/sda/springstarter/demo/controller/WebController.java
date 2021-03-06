package com.sda.springstarter.demo.controller;

import com.sda.springstarter.demo.model.Book;
import com.sda.springstarter.demo.service.AuthorsServiceImpl;
import com.sda.springstarter.demo.service.BookServiceImpl;
import com.sda.springstarter.demo.service.CategoriesServiceImpl;
import com.sda.springstarter.demo.service.PublisherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class WebController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private PublisherServiceImpl publisherService;

    @Autowired
    private CategoriesServiceImpl categoriesService;

    @Autowired
    private AuthorsServiceImpl authorsService;


    @GetMapping(value = "booklist")
    public ModelAndView showBooks() {
        ModelAndView model = new ModelAndView();
        model.addObject("booklister", bookService.getAllBooks());
        model.setViewName("booklist");
        model.addObject("publisherlister", publisherService.getAllPublishers());
        model.addObject("categorieslister", categoriesService.getAllCategories());
        model.addObject("authorlister", authorsService.getAllAuthors());
        model.addObject("book", new Book());
        return model;
    }

    @PostMapping(value = "addBook")
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bookresult, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message");
        return "redirect/booklist";
    }

//    @GetMapping
//    public ModelAndView showPublishers(){
//        ModelAndView model = new ModelAndView();
//        model.addObject("publisherlister", publisherService.getAllPublishers());
//        model.setViewName("publisher");
//        return model;
//    }
}
