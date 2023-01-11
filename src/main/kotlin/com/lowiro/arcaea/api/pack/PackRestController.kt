package com.lowiro.arcaea.api.pack

import com.alibaba.fastjson.JSONObject
import com.lowiro.arcaea.api.pack.mapper.PackMapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pack/")
class PackRestController constructor(val packMapper: PackMapper) {

    @PostMapping(value = ["/info"])
    fun info(@RequestBody requestBody: JSONObject): String {
        val packDataSelect = packMapper.packDataSelect(requestBody.getInteger("id") ?: return omicron("required: 'id'"))
        return try {
            content(packDataSelect)
        } catch (exception: Exception) {
            omicron("select failure")
        }
    }

    @PostMapping(value = ["/admin/insert"])
    fun insert(@RequestBody requestBody: JSONObject): String {
        val data = requestBody.getJSONArray("data")
        var message = "required: '平台'"
        try {
            data[0]
            message = "required: '曲包封面'"
            data[1]
            message = "required: '曲包名称'"
            data[2]
            message = "required: '曲包类型'"
            data[3]
            message = "required: '内含曲目数量'"
            data[4]
            message = "required: '上线时间'"
            data[5]
            message = "required: '花费'"
            data[6]
        } catch (exception: Exception) {
            return omicron(message)
        }
        return if (packMapper.packInsert(packMapper.packIdSelect() + 1, data.toString()) == 0) {
            omicron("insert failure")
        } else {
            omicron("insert success")
        }
    }

    val server = "\"server\": {\"version\": {\"omicron\": \"15\"}}"
    val copyright = "\"copyright\": \"2023\""

    fun result(sigma: String, upsilon: String): String {
        return "{\"contents\": {\"answers\": {\"results\": {\"sigma\": \"$sigma\",\"upsilon\": $upsilon},$server},$copyright}}"
    }

    fun omicron(omicron: String): String {
        return result("204", "\"$omicron\"")
    }

    fun content(answer: String): String {
        return result("200", answer)
    }

}
