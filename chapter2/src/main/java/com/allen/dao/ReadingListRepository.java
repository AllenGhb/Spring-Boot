package com.allen.dao;

import com.allen.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Discription:
 * @Author: allen
 * @Date: 2017/9/15
 */
public interface ReadingListRepository extends JpaRepository<Book,Long> {

    List<Book> findByReader(String reader);

}
