package com.cao.frs.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "remiburse")
@ApiModel("报销记录表")
public class Reimburse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("报销申请时间")
    private Date createTime;

    @ApiModelProperty("报销结束时间")
    private Date endTime;

    @ApiModelProperty("操作人id")
    private Integer operateId;

    @ApiModelProperty("报销金额")
    private Integer money;
}