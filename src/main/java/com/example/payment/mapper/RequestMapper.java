package com.example.payment.mapper;

import com.example.payment.entity.Payment;
import com.example.payment.entity.Request;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequestMapper {

    @Insert("INSERT INTO requests (requester_id, recipient_id, recipient_email_or_phone, amount, total_amount, memo, status) VALUES (#{requesterId}, #{recipientId}, #{recipientEmailOrPhone}, #{amount}, #{totalAmount}, #{memo}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertRequest(Request request);


    @Select("SELECT * FROM requests WHERE requester_id = #{requesterId}")
    List<Request> getRequestsOfRequester(Integer requesterId);

    @Select("SELECT * FROM requests WHERE recipient_id = #{recipientId}")
    List<Request> getRequestsOfRecipient(Integer recipientId);

    @Select("SELECT * FROM requests WHERE id = #{id}")
    Request getRequestOfId(Integer id);

    @Update("UPDATE requests SET status = 'completed', completed_at = CURRENT_TIMESTAMP WHERE id = #{id} AND requester_id = #{requesterId} AND recipient_id = #{recipientId}")
    void completeRequest(Integer id, Integer requesterId, Integer recipientId);

}
