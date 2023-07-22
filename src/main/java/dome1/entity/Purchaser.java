package dome1.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class Purchaser {
    /*{
            id: '123',
            traderName: 'abc',
            address: 'shenzhen',
            phone: '13212345678',
            contactPerson: 'leeing',
            email: '123@gmail.com',
            homePage: 'https://www.baidu.com/',
            userId: '1'
    }*/
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String traderName;
    private String address;
    private String phone;
    private String contactPerson;
    private String email;
    private String homePage;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
