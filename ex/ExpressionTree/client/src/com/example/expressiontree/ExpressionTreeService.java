package com.example.expressiontree;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * A Retrofit interface for the server hosting our ExpressionTree
 * code.
 * 
 * @author Mitchell
 *
 */
public interface ExpressionTreeService {
    @GET("/ExpressionTreeServer")
    ServerResponse execute(@Query("input") String input);
}
