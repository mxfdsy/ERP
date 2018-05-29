package com.lmfun.common.constant.enumeration;

public enum UserOperationTypeEnum {

	TYP_DEFAULT(""),

	TYP_TEST_MULTI_PATH("test.test_uid"),

	TYP_TEST("test_uid");


    
    String indexKey;
	
	private UserOperationTypeEnum(String indexKey) {
		this.indexKey = indexKey;
	}

	public String getIndexKey(){
		return this.indexKey;
	}
	
	public String toString() {
		return  this.indexKey;
	}
	
}
