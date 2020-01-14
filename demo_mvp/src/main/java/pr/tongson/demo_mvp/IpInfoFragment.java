package pr.tongson.demo_mvp;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import pr.tongson.component_http.utils.GsonUtils;
import pr.tongson.demo_mvp.model.IpInfo;
import pr.tongson.demo_mvp.presenter.IpInfoContract;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IpInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IpInfoFragment extends Fragment implements IpInfoContract.View {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mText;


    public IpInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IpInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IpInfoFragment newInstance(String param1, String param2) {
        IpInfoFragment fragment = new IpInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View rootView;

    private Dialog mDialog;
    private IpInfoContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_ip_info, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        mText = (TextView) rootView.findViewById(R.id.text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog = new ProgressDialog(getActivity());
        mDialog.setTitle("获取数据中");
    }


    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.getIpInfo("103.27.25.105");
    }

    @Override
    public void setIpInfo(IpInfo ipInfo) {
        if (ipInfo != null) {
            //            IpInfo.ResultBean ipData = ipInfo.getResult();
            //            mText.setText(ipData.getAd_info().getCity());
            mText.setText(GsonUtils.GsonString(ipInfo));
        }
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "网络出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


}
