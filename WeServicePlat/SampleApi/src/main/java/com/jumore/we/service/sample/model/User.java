/**
 * Project Name:SampleApi File Name:User.java Package
 * Name:com.jumore.we.service.sample.model Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午3:18:27
 */
package com.jumore.we.service.sample.model;

import java.util.Date;

/**
 * Function: TODO (这里用一句话描述这个类的作用)
 * 
 * @author 乔广
 * @date 2017年8月8日 下午3:18:27
 * @version
 * @see
 */
public class User {
    private String   name;
    private Integer  age;
    private Date     birthday;
    private String[] accounts;

    /**
     * name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * age
     *
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * birthday
     *
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * accounts
     *
     * @return the accounts
     */
    public String[] getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(String[] accounts) {
        this.accounts = accounts;
    }
}
