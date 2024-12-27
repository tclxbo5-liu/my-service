package com.elong.hotel.hotelmy.mybatis;


import com.elong.hotel.hotelmy.entity.SupplierThirdMapping;

/**
 * @author bobo
 */
public interface SupplierThirdMappingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplierThirdMapping record);

    int insertSelective(SupplierThirdMapping record);

    SupplierThirdMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupplierThirdMapping record);

    int updateByPrimaryKey(SupplierThirdMapping record);
}