package cn.zhangqin56.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.zhangqin56.common.domain.SysSsoProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<String> excludeFields = List.of("id");
        Assertions.assertTrue(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(obj1, obj2, excludeFields)
        );
        Assertions.assertFalse(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(obj1, obj3, excludeFields)
        );

        excludeFields = List.of("id", "isAuto");
        Assertions.assertTrue(
                ObjectFieldsEqualUtils.isSameDataExceptSomeFields(obj1, obj3, excludeFields)
        );
    }

    @Test
    void isSameData() {
        Assertions.assertTrue(
                ObjectFieldsEqualUtils.isSameData(obj1, obj2)
        );

        Assertions.assertFalse(
                ObjectFieldsEqualUtils.isSameData(obj1, obj3)
        );

        Assertions.assertFalse(
                ObjectFieldsEqualUtils.isSameData(obj2, obj3)
        );


    }
}