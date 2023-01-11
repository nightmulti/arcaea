package com.lowiro.arcaea.api.pack.mapper

import org.apache.ibatis.annotations.*

@Mapper
interface PackMapper {

    @Insert("INSERT INTO `new` (`id`, `data`) VALUES (#{id}, #{data})")
    fun packInsert(@Param(value = "id") id: Int, @Param(value = "data") data: String): Int

    @Select("SELECT `id` FROM `new` ORDER BY `id` desc LIMIT 1")
    fun packIdSelect(): Int

    @Select("SELECT `data` FROM `new` WHERE `id` = #{id}")
    fun packDataSelect(@Param(value = "id") id: Int): String

}
