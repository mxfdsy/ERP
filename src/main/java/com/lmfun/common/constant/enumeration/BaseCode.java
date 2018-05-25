package com.lmfun.common.constant.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum BaseCode {
    SUCESS(200, "Opeartion success"),
    
    ACCESS_DENIED(403, "Access denied"),

    INTERNAL_ERROR(500, "Server internal error "),

    SESSION_INVALID(1003, "session invalid"),

    UNKNOWN(-1, "Unknown error"),
    
    INVALID_ARGUMENT(11001, "请求参数错误")
;

    private int code;
    private String message;

    BaseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseCode valueOf(int code) {
        BaseCode ec = values.get(code);
        if (ec != null) {
            return ec;
        }
        return UNKNOWN;
    }

    public static BaseCode valueOfCodeStr(String codeStr) {
        try {
            int code = Integer.valueOf(codeStr);
            BaseCode ec = values.get(code);
            if (ec != null) {
                return ec;
            }
            return UNKNOWN;
        } catch (Exception e) {
            return UNKNOWN;
        }

    }

    private static final Map<Integer, BaseCode> values = new HashMap<Integer, BaseCode>();

    static {
        for (BaseCode ec : BaseCode.values()) {
            values.put(ec.code, ec);
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
