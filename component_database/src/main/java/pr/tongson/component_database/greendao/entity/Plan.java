package pr.tongson.component_database.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <b>Create Date:</b> 2019/2/10<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
@Entity(
        //设置表名
        nameInDb = "T_PLAN_A_TABLE",
        //是否需要greenDao创建表，默认为true
        createInDb = true,
        //是否生成所有参数构造器
        generateConstructors = true,
        //如果没有提供get、set方法，是否生成，默认为true
        generateGettersSetters = true)
public class Plan {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String detial;
    /**
     * 0、1、2、3
     */
    private Integer typeA;
    /**
     * 0、1、2、3
     */
    private Integer typeB;
    /**
     * 是否重复
     */
    private boolean repeat;
    /**
     * 如果是重复
     * 哪天是重复的
     * 周 1\2\3\4\5\6\7
     */
    private String repeatDay;
    /**
     * 如果重复
     * 指定时间HHmm
     */
    private String time;
    /**
     * 如果不重复
     * 指定日期yyyyMMddHHmmss
     */
    private String date;

    /**
     * 0：没达成，空着
     * 1：达到目标
     */
    private int status;

    /**
     * 额外
     */
    private String extra;

@Generated(hash = 758456488)
public Plan(Long id, String title, String detial, Integer typeA, Integer typeB,
        boolean repeat, String repeatDay, String time, String date, int status,
        String extra) {
    this.id = id;
    this.title = title;
    this.detial = detial;
    this.typeA = typeA;
    this.typeB = typeB;
    this.repeat = repeat;
    this.repeatDay = repeatDay;
    this.time = time;
    this.date = date;
    this.status = status;
    this.extra = extra;
}

@Generated(hash = 592612124)
public Plan() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getTitle() {
    return this.title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDetial() {
    return this.detial;
}

public void setDetial(String detial) {
    this.detial = detial;
}

public Integer getTypeA() {
    return this.typeA;
}

public void setTypeA(Integer typeA) {
    this.typeA = typeA;
}

public Integer getTypeB() {
    return this.typeB;
}

public void setTypeB(Integer typeB) {
    this.typeB = typeB;
}

public boolean getRepeat() {
    return this.repeat;
}

public void setRepeat(boolean repeat) {
    this.repeat = repeat;
}

public String getRepeatDay() {
    return this.repeatDay;
}

public void setRepeatDay(String repeatDay) {
    this.repeatDay = repeatDay;
}

public String getTime() {
    return this.time;
}

public void setTime(String time) {
    this.time = time;
}

public String getDate() {
    return this.date;
}

public void setDate(String date) {
    this.date = date;
}

public int getStatus() {
    return this.status;
}

public void setStatus(int status) {
    this.status = status;
}

public String getExtra() {
    return this.extra;
}

public void setExtra(String extra) {
    this.extra = extra;
}

}
