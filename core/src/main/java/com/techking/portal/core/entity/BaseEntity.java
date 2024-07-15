package com.techking.portal.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
public class BaseEntity {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 是否删除
     */
    private Boolean isDeleted;
    /**
     * 唯一性锁
     */
    private Long uniqueLock;
    /**
     * 乐观锁
     */
    private Integer revision;
    /**
     * 创建人
     */

    private Long createdBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private ZonedDateTime createdTime;
    /**
     * 更新人
     */
    private Long updatedBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private ZonedDateTime updatedTime;
}
