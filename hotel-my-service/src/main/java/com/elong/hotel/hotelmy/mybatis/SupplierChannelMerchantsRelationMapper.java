package com.elong.hotel.hotelmy.mybatis;

import com.elong.hotel.hotelmy.entity.SupplierChannelMerchantsRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface SupplierChannelMerchantsRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplierChannelMerchantsRelation record);

    int insertSelective(SupplierChannelMerchantsRelation record);

    List<SupplierChannelMerchantsRelation> selectByPrimaryKey(@Param("idList") List<Long> idList);

    int updateByPrimaryKeySelective(SupplierChannelMerchantsRelation record);

    int updateByPrimaryKey(SupplierChannelMerchantsRelation record);
}