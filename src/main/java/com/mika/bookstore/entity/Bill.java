package dome1.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class Bill {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private int billState;
    private String clientName;
    private String phone;
    private String address;
    private String Email;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long bookId;
    private String bookName;
    private int num;
    private Double totalPrice;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
