package com.u6f6o.graveyard.server

import com.google.gson.GsonBuilder
import com.u6f6o.graveyard.Response
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE
import io.netty.handler.codec.http.HttpHeaders.Names.TRANSFER_ENCODING
import io.netty.handler.codec.http.HttpResponseStatus.OK
import io.netty.handler.codec.http.HttpVersion.HTTP_1_1
import io.netty.util.AsciiString
import io.netty.util.CharsetUtil

class JsonEncoder : MessageToMessageEncoder<Response>() {

    // TODO("yeah, this is ugly")
    override fun encode(ctx: ChannelHandlerContext?, msg: Response?, out: MutableList<Any>?) {
        val gson = GsonBuilder().disableHtmlEscaping().create()
        val json = gson.toJson(msg)
        val response = DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(json.toByteArray(CharsetUtil.UTF_8)))
        response.headers().set(CONTENT_TYPE, AsciiString("application/json; charset=utf-8"))
        response.headers().set(TRANSFER_ENCODING, HttpHeaderValues.CHUNKED)
        out?.add(response)
    }
}