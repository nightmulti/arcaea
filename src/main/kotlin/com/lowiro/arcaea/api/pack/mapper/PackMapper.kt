package com.lowiro.arcaea.api.pack.mapper

import com.alibaba.fastjson.JSONObject
import org.apache.ibatis.annotations.*

@Mapper
interface PackMapper {

    @Insert("INSERT INTO `pack` (`id`, `platform`, `cover`, `title`, `type`, `count`, `date`, `cost`) VALUES (#{id}, #{platform}, #{cover}, #{title}, #{type}, #{count}, #{date}, #{cost})")
    fun packInsert(
        @Param(value = "id") id: Int,
        @Param(value = "platform") platform: String,
        @Param(value = "cover") cover: String,
        @Param(value = "title") title: String,
        @Param(value = "type") type: String,
        @Param(value = "count") count: String,
        @Param(value = "date") date: String,
        @Param(value = "cost") cost: String
    ): Int

    @Select("SELECT * FROM `pack` WHERE id = #{id}")
    fun packSelect(@Param(value = "id") id: Int): JSONObject

}
