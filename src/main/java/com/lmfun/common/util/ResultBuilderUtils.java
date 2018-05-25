package com.lmfun.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lmfun.common.constant.Constants;
import com.lmfun.common.constant.enumeration.BaseCode;
import com.lmfun.pojo.vo.BaseResponseDTO;

public class ResultBuilderUtils {
    public static String buildSuccess(String message) {
        JSONObject result = new JSONObject();
        result.put(Constants.MESSAGE_FLG, message);
        return buildSuccess(result);
    }

    public static String buildSuccess(Object data) {
        BaseResponseDTO result = new BaseResponseDTO(data);
        return FastJsonUtils.toJSONString(result);
    }

    public static String buildError(String message) {
        BaseResponseDTO result = new BaseResponseDTO(message);
        return FastJsonUtils.toJSONString(result);
    }

    public static String buildError(BaseCode errorCode) {
        BaseResponseDTO result = new BaseResponseDTO(errorCode.getCode(), errorCode.getMessage());
        return FastJsonUtils.toJSONString(result);
    }
    
    public static String buildError(BaseCode errorCode, String errorMsg) {
        BaseResponseDTO result = new BaseResponseDTO(errorCode.getCode(), errorMsg);
        return FastJsonUtils.toJSONString(result);
    }

    public static String buildErrorWithData(BaseCode errorCode, Object data) {
        BaseResponseDTO result = new BaseResponseDTO(errorCode.getCode(), errorCode.getMessage(), data);
        return FastJsonUtils.toJSONString(result);
    }

    public static String buildResponse(Object data, String code, String message) {
        JSONObject result = new JSONObject();
        result.put(Constants.CODE_FLAG, code);
        result.put(Constants.MESSAGE_FLG, message);
        result.put(Constants.DATA_FLAG, data);

        return JSON.toJSONString(result);
    }

}
