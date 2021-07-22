package com.beixin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 收租信息表
 * </p>
 *
 * @author maqh
 * @since 2020-08-21
 */
@Data
public class RentalsVo implements Serializable {

    private Integer id;
    private Integer customerId;
    private String custname;//租户名称
    private String numbers;//房号
    private String phone;
    private String status;//交付的状态
    private Integer money;
    private String address;
    private Date ksrq;
    private Date jzrq;
    private String jfr;
    private String remarks;
    private Date date;


    public Date getKsrq() {
        return ksrq;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getNumbers() {
        return numbers;
    }

    public Date getJzrq() {
        return jzrq;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }
}
