package com.lmfun.common.interceptor;


import com.lmfun.common.constant.enumeration.UserOperationTypeEnum;
import com.lmfun.common.interceptor.support.useroperationtrace.annotation.UserOperationRecord;
import com.lmfun.pojo.po.trace.UserOperationTracePO;
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
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTracePO);
        Assert.assertNull(userOperationTracePO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO.getDescription());
        Assert.assertEquals("", userOperationTracePO.getBusinessType());

    }

    @Test
    @UserOperationRecord(name = "message" , userOperationType = UserOperationTypeEnum.TYP_TEST, parameterLocation = -1, descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithInvalidLocation() throws NoSuchMethodException {
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithInvalidLocation").getAnnotation(UserOperationRecord.class);
        String args[] = {"{\"hello\":\"world\"}"};
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTracePO);
        Assert.assertNull(userOperationTracePO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO.getDescription());
        Assert.assertEquals("TYP_TEST", userOperationTracePO.getBusinessType());
    }

    @Test
    @UserOperationRecord(name = "message" ,  parameterLocation = 0, customizedType = "customized", descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithEmptykeyNameAndValidlocation() throws NoSuchMethodException{
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithEmptykeyNameAndValidlocation").getAnnotation(UserOperationRecord.class);
        String args[] = {"{\"hello\":\"world\"}"};
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTracePO);
        Assert.assertEquals("{\"hello\":\"world\"}",userOperationTracePO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO.getDescription());
        Assert.assertEquals("customized", userOperationTracePO.getBusinessType());
    }

    //单个参数简单路径
    @Test
    @UserOperationRecord(name = "message" ,userOperationType = UserOperationTypeEnum.TYP_TEST, parameterLocation = 0, descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithSimpleKeyAndLocation() throws NoSuchMethodException{
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithSimpleKeyAndLocation").getAnnotation(UserOperationRecord.class);
        String argsHasNoKey[] = {"{\"hello\":\"world\"}"};
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(argsHasNoKey, userOperationRecord, userOperationTracePO);
        Assert.assertNull(userOperationTracePO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO.getDescription());
        Assert.assertEquals("TYP_TEST", userOperationTracePO.getBusinessType());


        String argsContainsKey[] = {"{\"test_uid\":\"world\"}"};
        UserOperationTracePO userOperationTracePO1 = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(argsContainsKey, userOperationRecord, userOperationTracePO1);
        Assert.assertEquals("world",userOperationTracePO1.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO1.getDescription());
        Assert.assertEquals("TYP_TEST", userOperationTracePO1.getBusinessType());
    }

    //单个参数多路径
    @Test
    @UserOperationRecord(name = "message" , userOperationType = UserOperationTypeEnum.TYP_TEST_MULTI_PATH, parameterLocation = 0, descTemplateName = "createProductDemo")
    public void testgetBusinessUidsFromArgsWithPathAndLocation() throws NoSuchMethodException{
        UserOperationRecord userOperationRecord = UserOperationTraceInterceptorTest.class.getMethod("testgetBusinessUidsFromArgsWithPathAndLocation").getAnnotation(UserOperationRecord.class);
        String argsHasNoKey[] = {"{\"hello\":\"world\"}"};
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(argsHasNoKey, userOperationRecord, userOperationTracePO);
        Assert.assertNull(userOperationTracePO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO.getDescription());
        Assert.assertEquals("TYP_TEST_MULTI_PATH", userOperationTracePO.getBusinessType());


        String argsContainsKey[] = {"{\"hello\":\"world\",\"test\":{\"test_uid\":\"111\"}}"};
        UserOperationTracePO userOperationTracePO1 = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(argsContainsKey, userOperationRecord, userOperationTracePO1);
        Assert.assertEquals("111",userOperationTracePO1.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO1.getDescription());
        Assert.assertEquals("TYP_TEST_MULTI_PATH", userOperationTracePO1.getBusinessType());
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
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();

        userOperationTraceInterceptor.setBusinessParameters(args, userOperationRecord, userOperationTracePO);
        Assert.assertEquals("111,222",userOperationTracePO.getBusinessUids());
        Assert.assertEquals(DEMO_DESCRIPTION, userOperationTracePO.getDescription());
        Assert.assertEquals("customized", userOperationTracePO.getBusinessType());
    }

}
