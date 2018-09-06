/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;


/**
 *
 * @author 'Kasun Chamara'
 */

public class TFingerPrint implements Serializable {
    private Integer id; 
    private String clkdate; 
    private String clktime; 
    private String emp_id; 
    private String checktime;

    public TFingerPrint() {
    }

    public TFingerPrint(Integer id, String clkdate, String clktime, String emp_id, String checktime) {
        this.id = id;
        this.clkdate = clkdate;
        this.clktime = clktime;
        this.emp_id = emp_id;
        this.checktime = checktime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClkdate() {
        return clkdate;
    }

    public void setClkdate(String clkdate) {
        this.clkdate = clkdate;
    }

    public String getClktime() {
        return clktime;
    }

    public void setClktime(String clktime) {
        this.clktime = clktime;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }
    
    
   
    
}
