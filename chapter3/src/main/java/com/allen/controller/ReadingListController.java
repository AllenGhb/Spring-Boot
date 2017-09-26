package com.allen.controller;

import com.allen.AmazonProperties;
import com.allen.dao.ReadingListRepository;
import com.allen.entity.Book;
import com.allen.entity.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Discription:
 * @Author: allen
 * @Date: 2017/9/15
 */

@Controller
@RequestMapping("/")
@ConfigurationProperties("amazon")
public class ReadingListController {

    private ReadingListRepository readingListRepository;
    private AmazonProperties amazonConfig;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonConfig) {
        this.readingListRepository = readingListRepository;
        this.amazonConfig = amazonConfig;
    }

    @RequestMapping(method=RequestMethod.GET,value = "/fail")
    public void fail(){
        throw new RuntimeException();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value= HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error(){
        return "error";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Reader reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books",readingList);
            model.addAttribute("reader",reader);
            model.addAttribute("amazonID",amazonConfig.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Reader reader,Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }


}
