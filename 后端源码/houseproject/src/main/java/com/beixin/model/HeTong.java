package com.beixin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data

public class HeTong {
    private Integer id;
    private String czf;//出租房
    private String zlf;//租赁方
    private String address;//房子地址
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date qzsj;//起租时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date jzsj;//截至时间
    private Double price;//房租

}
