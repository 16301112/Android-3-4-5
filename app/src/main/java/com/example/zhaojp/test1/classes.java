package com.example.zhaojp.test1;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link classes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link classes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class classes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private OnFragmentInteractionListener mListener;


    private Button buttonClass,buttonVideo;
    //BottomNavigationBar mBottomNavigationBar;
    private allClass classPage;
    private video videoPage;


    private View view;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public classes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment classes.
     */
    // TODO: Rename and change types and number of parameters
    public static classes newInstance(String param1, String param2) {
        classes fragment = new classes();
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



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_classes, container, false);

        buttonClass=view.findViewById(R.id.classes);
        buttonVideo=view.findViewById(R.id.videos);


        //菜单导航栏
        /*mBottomNavigationBar =view.findViewById(R.id.bottom_navigation_bar);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //mBottomNavigationBar.setBarBackgroundColor(R.color.colorBlue);
        mBottomNavigationBar.setActiveColor(R.color.colorGreen);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.color.colorGreen,"Classes"))
                .addItem(new BottomNavigationItem(R.color.colorGreen, "Videos"))
                .initialise();

        if (classPage == null) {
            classPage = new allClass();
        }
        getChildFragmentManager().beginTransaction().add(R.id.fragment_content, classPage).commitAllowingStateLoss();

        //菜单导航栏监听（跳转）
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //未选中->选中
                if (position == 0) {
                    if (classPage == null) {
                        classPage = new allClass();
                    }
                    getChildFragmentManager().beginTransaction().replace(R.id.fragment_content, classPage).commitAllowingStateLoss();
                } else if (position == 1) {
                    if (videoPage == null) {
                        videoPage = new video();
                    }
                    getChildFragmentManager().beginTransaction().replace(R.id.fragment_content, videoPage).commitAllowingStateLoss();
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });*/
        if (classPage == null) {
            classPage = new allClass();
        }
        getChildFragmentManager().beginTransaction().add(R.id.fragment_content, classPage).commitAllowingStateLoss();

        buttonClass.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                if (classPage == null) {
                    classPage = new allClass();
                }
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_content, classPage).commitAllowingStateLoss();

            }
        });

        buttonVideo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                if (videoPage == null) {
                    videoPage = new video();
                }
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_content, videoPage).commitAllowingStateLoss();
            }
        });


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
