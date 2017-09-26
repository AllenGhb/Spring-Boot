package com.allen.dao;

import com.allen.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Discription:
 * @Author: allen
 * @Date: 2017/9/26
 */
public interface ReaderRepository extends JpaRepository<Reader,String> {
}
