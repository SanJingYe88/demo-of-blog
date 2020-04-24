package it.com.demo.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Comment implements Serializable {
    @Id        //标注主键
    private String _id; // ID
    private String articleid; // 所属文章ID
    private String userid; // 评论人ID
    private String nickname; // 评论人的昵称
    private Date publishdate; // 评论日期
}
