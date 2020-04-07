package pr.tongson.base.skin.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.app.AppCompatViewInflater;
import pr.tongson.base.skin.view.SkinnableButton;
import pr.tongson.base.skin.view.SkinnableImageView;
import pr.tongson.base.skin.view.SkinnableLinearLayout;
import pr.tongson.base.skin.view.SkinnableRelativeLayout;
import pr.tongson.base.skin.view.SkinnableTextView;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/3/29
 * @Version
 * @Since
 * @Description
 */
public class CustomAppCompatViewInflater extends AppCompatViewInflater {
    /**
     * 上下文
     */
    private Context context;

    /**
     * 控件名
     */
    private String name;

    /**
     * 某控件对应所有属性
     */
    private AttributeSet attrs;

    public CustomAppCompatViewInflater(Context context) {
        this.context = context;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttrs(AttributeSet attrs) {
        this.attrs = attrs;
    }

    /**
     * @return 自动匹配控件名，并初始化控件对象
     */
    public View autoMatch() {
        View view;
        switch (name) {
            case "LinearLayout":
                // view = super.createTextView(context, attrs); // 源码写法
                view = new SkinnableLinearLayout(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "RelativeLayout":
                view = new SkinnableRelativeLayout(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "TextView":
                view = new SkinnableTextView(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "ImageView":
                view = new SkinnableImageView(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "Button":
                view = new SkinnableButton(context, attrs);
                this.verifyNotNull(view, name);
                break;
            default:
                view = createView(context, name, attrs);
                break;
        }
        return view;
    }

    /**
     * 校验控件不为空（源码方法，由于private修饰，只能复制过来了。为了代码健壮，可有可无）
     *
     * @param view 被校验控件，如：AppCompatTextView extends TextView（v7兼容包，兼容是重点！！！）
     * @param name 控件名，如："ImageView"
     */
    private void verifyNotNull(View view, String name) {
        if (view == null) {
            throw new IllegalStateException(this.getClass().getName() + " asked to inflate view for <" + name + ">, but returned null");
        }
    }
}
