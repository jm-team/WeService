/**
 * Project Name:SampleApi File Name:Hello.java Package
 * Name:com.jumore.we.service.sample.model Copyright (c) 2017, JUMORE Co.,Ltd.
 * All Rights Reserved.
 *
 * @author 乔广
 * @date 2017年8月8日 下午3:17:42
 */
package com.jumore.we.service.sample.model;

import java.util.Date;

/**
 * Function: TODO (这里用一句话描述这个类的作用)
 * 
 * @author 乔广
 * @date 2017年8月8日 下午3:17:42
 * @version
 * @see
 */
public class Hello {
    private String[] messages;
    private Date   date;
    private User   user;
    /**
     * message
     *
     * @return the message
     */
    public String[] getMessages() {
        return messages;
    }
    /**
     * @param message the message to set
     */
    public void setMessages(String[] messages) {
        this.messages = messages;
    }
    /**
     * date
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * user
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
