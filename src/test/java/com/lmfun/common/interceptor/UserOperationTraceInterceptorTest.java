package com.lmfun.common.interceptor;


import com.lmfun.common.constant.enumeration.UserOperationTypeEnum;
import com.lmfun.common.interceptor.support.useroperationtrace.annotation.UserOperationRecord;
import com.lmfun.pojo.dto.UserOperationTraceDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserOperationTraceInterceptorTest {

    @Autowired
    private UserOperationTraceInterceptor userOperationTraceInterceptor;

    private String DEMO_DESCRIPTION = "哥创建了商品‘This is a demo’，这句是直观显示用的";

    @Before
    public void setUp() {
    }

    @Test
    @UserOperationRecord(name = "message", descTemplateName = "createProductDemo")
    public void testgetBusinessUidsEmptyArgs() throws NoSuchMethodException {
        String args[] = {};
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsEmptyArgs").getAnnotation(UserOperationRecord.class);
        UserOperationTraceDTO userOperationTraceDTO = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTraceDTO);
        Assert.assertNull(userOperationTraceDTO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO.getDescription());
        Assert.assertEquals("", userOperationTraceDTO.getBusinessType());

    }

    @Test
    @UserOperationRecord(name = "message" , userOperationType = UserOperationTypeEnum.TYP_TEST, parameterLocation = -1, descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithInvalidLocation() throws NoSuchMethodException {
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithInvalidLocation").getAnnotation(UserOperationRecord.class);
        String args[] = {"{\"hello\":\"world\"}"};
        UserOperationTraceDTO userOperationTraceDTO = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTraceDTO);
        Assert.assertNull(userOperationTraceDTO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO.getDescription());
        Assert.assertEquals("TYP_TEST", userOperationTraceDTO.getBusinessType());
    }

    @Test
    @UserOperationRecord(name = "message" ,  parameterLocation = 0, customizedType = "customized", descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithEmptykeyNameAndValidlocation() throws NoSuchMethodException{
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithEmptykeyNameAndValidlocation").getAnnotation(UserOperationRecord.class);
        String args[] = {"{\"hello\":\"world\"}"};
        UserOperationTraceDTO userOperationTraceDTO = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTraceDTO);
        Assert.assertEquals("{\"hello\":\"world\"}",userOperationTraceDTO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO.getDescription());
        Assert.assertEquals("customized", userOperationTraceDTO.getBusinessType());
    }

    //单个参数简单路径
    @Test
    @UserOperationRecord(name = "message" ,userOperationType = UserOperationTypeEnum.TYP_TEST, parameterLocation = 0, descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithSimpleKeyAndLocation() throws NoSuchMethodException{
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithSimpleKeyAndLocation").getAnnotation(UserOperationRecord.class);
        String argsHasNoKey[] = {"{\"hello\":\"world\"}"};
        UserOperationTraceDTO userOperationTraceDTO = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(argsHasNoKey, userOperationRecord, userOperationTraceDTO);
        Assert.assertNull(userOperationTraceDTO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO.getDescription());
        Assert.assertEquals("TYP_TEST", userOperationTraceDTO.getBusinessType());


        String argsContainsKey[] = {"{\"test_uid\":\"world\"}"};
        UserOperationTraceDTO userOperationTraceDTO1 = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(argsContainsKey, userOperationRecord, userOperationTraceDTO1);
        Assert.assertEquals("world",userOperationTraceDTO1.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO1.getDescription());
        Assert.assertEquals("TYP_TEST", userOperationTraceDTO1.getBusinessType());
    }

    //单个参数多路径
    @Test
    @UserOperationRecord(name = "message" , userOperationType = UserOperationTypeEnum.TYP_TEST_MULTI_PATH, parameterLocation = 0, descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithPathAndLocation() throws NoSuchMethodException{
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithPathAndLocation").getAnnotation(UserOperationRecord.class);
        String argsHasNoKey[] = {"{\"hello\":\"world\"}"};
        UserOperationTraceDTO userOperationTraceDTO = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(argsHasNoKey, userOperationRecord, userOperationTraceDTO);
        Assert.assertNull(userOperationTraceDTO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO.getDescription());
        Assert.assertEquals("TYP_TEST_MULTI_PATH", userOperationTraceDTO.getBusinessType());


        String argsContainsKey[] = {"{\"hello\":\"world\",\"test\":{\"test_uid\":\"111\"}}"};
        UserOperationTraceDTO userOperationTraceDTO1 = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(argsContainsKey, userOperationRecord, userOperationTraceDTO1);
        Assert.assertEquals("111",userOperationTraceDTO1.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO1.getDescription());
        Assert.assertEquals("TYP_TEST_MULTI_PATH", userOperationTraceDTO1.getBusinessType());
    }

    /**
     * {
     "hello":"world",
     "parent":[
     {
     "test_uid":"111",
     "test_name":"name1"
     },
     {
     "test_uid":"222",
     "test_name":"name2"
     }]
     }
     * @throws NoSuchMethodException
     */
    //参数数组 多路径
    @Test
    @UserOperationRecord(name = "message" , parameterLocation = 0, isArray = 1, path = "parent.test_uid", customizedType = "customized", descTemplateName = "createProductDemo")
    public void testgetBusinessUidsSingleArgs4() throws NoSuchMethodException{
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsSingleArgs4").getAnnotation(UserOperationRecord.class);
        String args[] = {"{\"hello\":\"world\",\"parent\":[{\"test_uid\":\"111\",\"test_name\":\"name1\"},{\"test_uid\":\"222\",\"test_name\":\"name2\"}]}"};
        UserOperationTraceDTO userOperationTraceDTO = new UserOperationTraceDTO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTraceDTO);
        Assert.assertEquals("111,222",userOperationTraceDTO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTraceDTO.getDescription());
        Assert.assertEquals("customized", userOperationTraceDTO.getBusinessType());
    }

}
