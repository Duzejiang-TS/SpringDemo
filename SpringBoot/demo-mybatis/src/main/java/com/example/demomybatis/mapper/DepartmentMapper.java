package com.example.demomybatis.mapper;

import com.example.demomybatis.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author Devin13 on 2018/8/10.
 * @version 1.0
 */
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")//告诉自增的主键名字
    @Insert("insert into department (departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);

}
