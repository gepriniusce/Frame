package pr.tongson.base.utils;

import android.os.Looper;

import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import pr.tongson.library.utils.L;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/20
 * @Version
 * @Since
 * @Description 管理应用内所有的MutableLiveDatas
 */
public class LiveDataUtils {
    private static LiveDataUtils ourInstance = new LiveDataUtils();

    /**
     * 应用内所有的数据持有的集合
     */
    private Map<String, MyMutableLiveData<Object>> mMap;

    public static LiveDataUtils getInstance() {
        return ourInstance;
    }

    private LiveDataUtils() {
        mMap = new HashMap<>();
    }

    /**
     * 创建/得到MutableLiveData
     *
     * @param key
     * @param calzz
     * @param <T>
     * @return key所对应的MutableLiveData
     */
    public <T> MyMutableLiveData<T> with(String key, Class<T> calzz) {
        if (!mMap.containsKey(key)) {
            mMap.put(key, new MyMutableLiveData<>());
        }

        return (MyMutableLiveData<T>) mMap.get(key);
    }

    public MyMutableLiveData<Object> with(String target) {
        return with(target, Object.class);
    }

    public void remove(String key) {
        if (mMap.containsKey(key)) {
            mMap.remove(key);
        }
    }

    public <T> void post(String key, T t) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            with(key).setValue(t);
        } else {
            with(key).postValue(t);
        }
    }

    public static class MyMutableLiveData<T> extends MutableLiveData<T> {

        /**
         * 目的：使得在observe被调用的一刻，能够保证if(observer.mLastVersion>=mVersion)条件成立
         */
        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 处理粘性事件
         */
        public void observeStick(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);
        }

        /**
         * 通过 hook将LastVersion==mVersion
         *
         * @param observer
         */
        private void hook(@NonNull Observer<? super T> observer) throws Exception {
            Class<LiveData> classLiveData = LiveData.class;
            Field fieldObservers = classLiveData.getDeclaredField("mObservers");
            fieldObservers.setAccessible(true);
            Object mObservers = fieldObservers.get(this);
            Class<?> classObservers = mObservers.getClass();

            Method methodGet = classObservers.getDeclaredMethod("get", Object.class);
            methodGet.setAccessible(true);
            Object entry = methodGet.invoke(mObservers, observer);
            Object objectWrapper = ((Map.Entry) entry).getValue();
            //observer
            Class<?> mObserver = objectWrapper.getClass().getSuperclass();
            Field mLastVersion = mObserver.getDeclaredField("mLastVersion");
            mLastVersion.setAccessible(true);
            Field mVersion = classLiveData.getDeclaredField("mVersion");
            mVersion.setAccessible(true);
            Object mVersionValue = mVersion.get(this);
            mLastVersion.set(objectWrapper, mVersionValue);
            L.i("Tongson hook:" + mVersionValue);
        }
    }


}
