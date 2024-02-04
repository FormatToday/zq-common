package cn.zhangqin56.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.zhangqin56.common.domain.SysSsoProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectFieldsEqualUtilsTest {
    private SysSsoProductEntity obj1;
    private SysSsoProductEntity obj2;
    private SysSsoProductEntity obj3;

    @BeforeEach
    void init() {
        obj1 = SysSsoProductEntity.builder()
                .id(4L)
                .ssoId(2L)
                .consumerId(104728L)
                .companyId(1761L)
                .companyName("zq-kq")
                .mobile("1234")
                .isAuto(1)
                .productType(3)
                .setmealName("1234")
                .build();
        obj2 = new SysSsoProductEntity();
        BeanUtil.copyProperties(obj1, obj2);
        obj3 = new SysSsoProductEntity();
        BeanUtil.copyProperties(obj1, obj3);
        obj3.setId(2L);
        obj3.setIsAuto(0);
    }

    @Test
    void isSameDataExceptSomeFields() {
        List<String> excludeFields = ListUtil.of("id");
        assertTrue(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(obj1, obj2, excludeFields)
        );
        assertFalse(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(obj1, obj3, excludeFields)
        );

        excludeFields = ListUtil.of("id", "isAuto");
        assertTrue(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(obj1, obj3, excludeFields)
        );
    }

    @Test
    void isSameData() {
        assertTrue(
                ObjectFieldsEqualUtils.isSameData(obj1, obj2)
        );

        assertFalse(
                ObjectFieldsEqualUtils.isSameData(obj1, obj3)
        );

        assertFalse(
                ObjectFieldsEqualUtils.isSameData(obj2, obj3)
        );
    }

    @Test
    void testIsSameDataExceptSomeFields() {
        List<String> excludeFields = ListUtil.of("id");
        assertFalse(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(ListUtil.of(obj1, obj2, obj3), excludeFields)
        );
        excludeFields = ListUtil.of("id", "isAuto");
        assertTrue(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(ListUtil.of(obj1, obj2, obj3), excludeFields)
        );
    }

    @Test
    void testIsSameData() {
        assertFalse(
                ObjectFieldsEqualUtils.isSameData(ListUtil.of(obj1, obj2, obj3))
        );
        assertFalse(
                ObjectFieldsEqualUtils.isSameData(obj1, obj2, obj3)
        );
    }
}