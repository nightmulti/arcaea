package com.lowiro.arcaea.api.pack

import com.alibaba.fastjson.JSONObject
import com.lowiro.arcaea.api.pack.mapper.PackMapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pack/")
class PackRestController constructor(var packMapper: PackMapper) {

    @PostMapping(value = ["/info"])
    fun info(@RequestBody requestBody: JSONObject): Any {
        var id: Int = requestBody.getInteger("id") ?: return onJsonReturn("204", "\"information\": \"required: 'id'\"")

        val pack: JSONObject = packMapper.packSelect(id)

        if (pack == null) {
            return onJsonReturn("204", "\"information\": \"select failure\"")
        }

        id = pack.getInteger("id")

        val platform: JSONObject = pack.getJSONObject("platform")
        val cover: JSONObject = pack.getJSONObject("cover")
        val title: String = pack.getString("title")
        val type: JSONObject = pack.getJSONObject("type")
        val count: JSONObject = pack.getJSONObject("count")
        val date: JSONObject = pack.getJSONObject("date")
        val cost: JSONObject = pack.getJSONObject("cost")

        val upsilon =
            "\"pack\": {\"id\": $id,\"platform\": $platform,\"cover\": $cover,\"title\": \"$title\",\"type\": $type,\"count\": $count,\"date\": $date,\"cost\": $cost}"

        return onJsonReturn("200", upsilon)
    }

    @PostMapping(value = ["/admin/insert"])
    fun insert(@RequestBody requestBody: JSONObject): String {
        val id: Int = requestBody.getInteger("id") ?: return onJsonReturn("204", "\"information\": \"required: 'id'\"")
        val platform: JSONObject = requestBody.getJSONObject("platform") ?: return onJsonReturn(
            "204", "\"information\": \"required: 'platform'\""
        )
        val cover: JSONObject =
            requestBody.getJSONObject("cover") ?: return onJsonReturn("204", "\"information\": \"required: 'cover'\"")
        val title: String =
            requestBody.getString("title") ?: return onJsonReturn("204", "\"information\": \"required: 'title'\"")
        val type: JSONObject =
            requestBody.getJSONObject("type") ?: return onJsonReturn("204", "\"information\": \"required: 'type'\"")
        val count: JSONObject =
            requestBody.getJSONObject("count") ?: return onJsonReturn("204", "\"information\": \"required: 'count'\"")
        val date: JSONObject =
            requestBody.getJSONObject("date") ?: return onJsonReturn("204", "\"information\": \"required: 'date'\"")
        val cost: JSONObject =
            requestBody.getJSONObject("cost") ?: return onJsonReturn("204", "\"information\": \"required: 'cost'\"")

        val packInsert: Int = packMapper.packInsert(
            id,
            platform.toJSONString(),
            cover.toJSONString(),
            title,
            type.toJSONString(),
            count.toJSONString(),
            date.toJSONString(),
            cost.toJSONString()
        )

        return if (packInsert == 0) {
            onJsonReturn("204", "\"information\": \"insert failure\"")
        } else {
            return onJsonReturn("200", "\"information\": \"insert success\"")
        }
    }

    fun onJsonReturn(sigma: String, upsilon: String): String {
        return "{\"contents\": {\"answers\": {\"results\": {\"sigma\": \"$sigma\",\"upsilon\": {$upsilon}},\"server\": {\"version\": {\"omicron\": \"15\"}}},\"copyright\": \"2023\"}}"
    }

}
