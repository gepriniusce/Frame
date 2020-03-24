package pr.tongson.component_http;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pr.tongson.component_http.utils.HttpLoggingInterceptor2;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private String baseUrl = "https://api.github.com/";

    @Test
    public void addition_isCorrect() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.github.com/").
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();

        Api api = retrofit.create(Api.class);
        api.getRepos("gepriniusce").
                subscribeOn(Schedulers.io()).
                //                observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new SingleObserver<List<Repos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("正在请求");
                    }

                    @Override
                    public void onSuccess(List<Repos> repos) {
                        System.out.println("onSuccess");
                        System.out.println(repos.get(0).getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                        System.out.println("onError");
                        System.out.println(e.getMessage());
                    }
                });
    }

    public static final long DEFAULT_READ_TIMEOUT_MILLIS = 15 * 1000;
    public static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000;
    public static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000;

    @Test
    public void cache() {
//        HttpLoggingInterceptor2 loggingInterceptor = new HttpLoggingInterceptor2("OkGo");
//        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor2.Level.BODY);
//        loggingInterceptor.setColorLevel(Level.INFO);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        OkHttpClient client = builder.
                retryOnConnectionFailure(true).
                connectTimeout(15, TimeUnit.SECONDS).
                readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).
                writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).
                connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).
                addInterceptor(loggingInterceptor).
                build();


        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).
                client(client).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);
        api.getRepos("gepriniusce").
                //subscribeOn(Schedulers.io()).
                //                observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new SingleObserver<List<Repos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("正在请求");
                    }

                    @Override
                    public void onSuccess(List<Repos> repos) {
                        System.out.println("onSuccess");
                        System.out.println(repos.get(0).getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                        System.out.println(e.getMessage());
                    }
                });

    }
}