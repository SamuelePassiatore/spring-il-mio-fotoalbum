package org.java.fotoalbum.repo;

import org.java.fotoalbum.pojo.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}