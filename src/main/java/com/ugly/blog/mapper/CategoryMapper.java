package com.ugly.blog.mapper;

import com.ugly.blog.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 19:19
 */
public interface CategoryMapper {

    /**
     * 根据id查找分类
     *
     * @param categoryId id
     * @return category or null
     */
    @Select("SELECT * FROM category WHERE category_id=#{categoryID}")
    @Results(id = "categoryMap", value = {
            @Result(property = "categoryId", column = "category_id", id = true),
            @Result(property = "categoryName", column = "category_name")
    })
    Category getCategoryById(Integer categoryId);


    /**
     * 查找所有的分类
     *
     * @return category list or null
     */
    @Select("SELECT * FROM category")
    @ResultMap("categoryMap")
    List<Category> getAllCategoryList();

    /**
     * 查找固定数目的分类
     *
     * @param num 分类数
     * @return
     */
    @Select("SELECT c.*\n" +
            "FROM category c\n" +
            "         INNER JOIN (SELECT category_id, COUNT(*) AS nums\n" +
            "                     FROM article_category_ref\n" +
            "                     GROUP BY category_id) ac ON ac.category_id = c.category_id\n" +
            "ORDER BY ac.nums DESC, c.category_name\n" +
            "LIMIT #{num};")
    @ResultMap("categoryMap")
    List<Category> getTopNumsCategoryList(int num);

    /**
     * 更新分类名
     *
     * @param category 封装完整的category，categoryId不为空
     * @return 成功：失败：
     */
    @Update("UPDATE category SET category_name=#{categoryName} WHERE category_id=#{categoryId}")
    @ResultMap("categoryMap")
    int updateCategory(Category category);

    /**
     * 新增分类
     *
     * @param categoryName 分类名
     * @return
     */
    @Insert("INSERT INTO category(category_name) VALUES(#{categoryName})")
    @ResultMap("categoryMap")
    int insertCategory(String categoryName);

    /**
     * 根据id删除分类
     *
     * @param categoryId id
     * @return
     */
    @Delete("DELETE category WHERE category_id=#{categoryId}")
    @ResultMap("categoryMap")
    int deleteCategoryById(Integer categoryId);

}
