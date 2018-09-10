package com.fq.entify;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;

import java.io.Serializable;

//指定要操作的表名
//@TableName("tbl_employee")
public class Employee extends Model<Employee> {
    //指定主键策略
   // @TableId(type = IdType.AUTO)
    private Integer id;
    private String last_name;
    private String email;
    private Integer gender;
    private Integer age;

    @Version
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Employee() {

    }

    public void test(){
        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
    }
    public Employee(String last_name, String email, Integer gender, Integer age) {
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", last_name='" + last_name
                + '\'' + ", email='" + email + '\'' + ", gender=" + gender + ", age=" + age + '}';
    }

    /**
     *指定主键
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
