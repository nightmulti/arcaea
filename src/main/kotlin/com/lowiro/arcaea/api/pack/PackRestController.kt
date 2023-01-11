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
        data[0] ?: return omicron("required: '0'")
        data[1] ?: return omicron("required: '1'")
        data[2] ?: return omicron("required: '2'")
        data[3] ?: return omicron("required: '3'")
        data[4] ?: return omicron("required: '4'")
        data[5] ?: return omicron("required: '5'")
        data[6] ?: return omicron("required: '6'")
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
