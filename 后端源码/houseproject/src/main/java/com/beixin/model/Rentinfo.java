package com.beixin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 租赁信息表
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rentinfo对象", description="租赁信息表")
public class Rentinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer housesId;

    private Integer customerId;

    private Integer contractId;

    private String address;

    private String remarks;

    private Date createTime;

    private String djr;

    private Date updateTime;

    private String xgr;

    private Customer customer;

    private Houses houses;

    private Contract contract;
    @ApiModelProperty(value = "0未退租 1 退租 ")
    private Integer status;



}
