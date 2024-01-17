package cn.zhangqin56.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysSsoProductEntity {

    private Long id;

    private Long ssoId;

    private Long companyId;

    private Long consumerId;

    private String companyName;

    private String setmealId;

    private String mobile;

    private Integer productType;

    private Integer isAuto;

    private String setmealName;

    private BigDecimal setmealPrice;

    private Date loginTime;

    private String personnelName;

    private Integer personnelId;

    private String random;

    private String logoImg;

    private String licenseName;
}