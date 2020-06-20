package com.zk.activiti6.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

/**
 * Created by zhukai on 2020/6/20
 */
public class AssertUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNotBlank() {
        thrown.expect(RuntimeException.class);
        // 表示下面程序抛出的异常信息将包含"errMsg"
        thrown.expectMessage("errMsg");
        AssertUtil.notBlank("", "errMsg");
    }

    @Test
    public void testNotBlank2() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("errMsg");
        AssertUtil.notBlank(null, "errMsg");
    }

    @Test
    public void testNotNull() throws Exception {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("errMsg");
        AssertUtil.notNull(null, "errMsg");
    }

    @Test
    public void testNotEmpty() throws Exception {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("errMsg");
        AssertUtil.notEmpty(Arrays.asList(), "errMsg");
    }

    @Test
    public void testNotEmpty2() throws Exception {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("errMsg");
        AssertUtil.notEmpty(null, "errMsg");
    }

    @Test
    public void testAssertTrue() throws Exception {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("errMsg");
        AssertUtil.assertTrue(false, "errMsg");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
