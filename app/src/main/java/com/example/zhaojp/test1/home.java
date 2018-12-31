package com.example.zhaojp.test1;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    //AndroidImageSlider实现轮播图
    private SliderLayout sliderShow;

    private View view;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public home() {
        // Required empty public constructor
    }


    private void imageSlider() {

        TextSliderView textSliderView1 = new TextSliderView(this.getActivity());
        textSliderView1
                .description("女生")
                .image(R.drawable.sport1);

        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2
                .description("男生")
                .image(R.drawable.sport2);

        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3
                .description("孩子")
                .image(R.drawable.sport3);


        sliderShow.addSlider(textSliderView1);
        sliderShow.addSlider(textSliderView2);
        sliderShow.addSlider(textSliderView3);

        //图片点击监听
        textSliderView1.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                //Toast.makeText(MainActivity.this,"女生",Toast.LENGTH_SHORT).show();
            }
        });

        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                //Toast.makeText(MainActivity.this,"男生",Toast.LENGTH_SHORT).show();
            }
        });

        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                //Toast.makeText(MainActivity.this,"孩子",Toast.LENGTH_SHORT).show();
            }
        });


        //其他设置
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//使用默认指示器，在底部显示
        sliderShow.setDuration(2000);//停留时间

        //设置AndroidImageslider监听
        sliderShow.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);


        //图片轮播
        sliderShow = (SliderLayout)view.findViewById(R.id.slider);
        imageSlider();


        //图标
        Button button1=(Button)view.findViewById(R.id.tennis);
        Drawable drawable1=getResources().getDrawable(R.drawable.item1);
        drawable1.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button1.setCompoundDrawables(null,drawable1,null,null);
        button1.setCompoundDrawablePadding(10);

        Button button2=(Button)view.findViewById(R.id.ping);
        Drawable drawable2=getResources().getDrawable(R.drawable.item2);
        drawable2.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button2.setCompoundDrawables(null,drawable2,null,null);

        Button button3=(Button)view.findViewById(R.id.basketball);
        Drawable drawable3=getResources().getDrawable(R.drawable.item3);
        drawable3.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button3.setCompoundDrawables(null,drawable3,null,null);

        Button button4=(Button)view.findViewById(R.id.billiards);
        Drawable drawable4=getResources().getDrawable(R.drawable.item4);
        drawable4.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button4.setCompoundDrawables(null,drawable4,null,null);

        Button button5=(Button)view.findViewById(R.id.bowling);
        Drawable drawable5=getResources().getDrawable(R.drawable.item5);
        drawable5.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button5.setCompoundDrawables(null,drawable5,null,null);

        Button button6=(Button)view.findViewById(R.id.skating);
        Drawable drawable6=getResources().getDrawable(R.drawable.item6);
        drawable6.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button6.setCompoundDrawables(null,drawable6,null,null);

        Button button7=(Button)view.findViewById(R.id.dumbbell);
        Drawable drawable7=getResources().getDrawable(R.drawable.item7);
        drawable7.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button7.setCompoundDrawables(null,drawable7,null,null);

        Button button8=(Button)view.findViewById(R.id.badminton);
        Drawable drawable8=getResources().getDrawable(R.drawable.item8);
        drawable8.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button8.setCompoundDrawables(null,drawable8,null,null);

        Button button9=(Button)view.findViewById(R.id.running);
        Drawable drawable9=getResources().getDrawable(R.drawable.item9);
        drawable9.setBounds(0,0,150,150);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        button9.setCompoundDrawables(null,drawable9,null,null);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
