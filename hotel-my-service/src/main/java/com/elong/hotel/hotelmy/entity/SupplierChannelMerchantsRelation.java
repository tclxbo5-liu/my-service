package com.elong.hotel.hotelmy.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SupplierChannelMerchantsRelation {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 供应商Id
     */
    private Integer supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 通道商Id
     */
    private Integer channelMerchantsId;

    /**
     * 通道商名称
     */
    private String channelMerchantsName;

    /**
     * 佣金类型1比例2定额
     */
    private Integer commissionType;

    /**
     * 佣金值
     */
    private BigDecimal commissionValue;

    /**
     * 状态0无效1有效-1删除
     */
    private Integer status;

    /**
     * 审核状态0审核驳回1审核通过2审核中
     */
    private Integer auditType;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作人ip
     */
    private String opIp;

    /**
     * 时间戳
     */
    private Date timestamp;

    List<SupplierThirdMapping> supplierThirdMappings;

    SupplierThirdMapping supplierThirdMapping;
}