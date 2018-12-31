package com.example.zhaojp.test1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link recyclerview.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link recyclerview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recyclerview extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;

    private OnFragmentInteractionListener mListener;

    public recyclerview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment recyclerview.
     */
    // TODO: Rename and change types and number of parameters
    public static recyclerview newInstance(String param1, String param2) {
        recyclerview fragment = new recyclerview();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        view=inflater.inflate(R.layout.fragment_recyclerview, container, false);


        RecyclerView mRecyclerView;

        mRecyclerView =view.findViewById(R.id.recyclerView_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        List<String> name = new ArrayList();
        name.add("Trainer:Lionel Messi");
        name.add("Trainer;Miroslav Klose");
        name.add("Trainer:Roque Santa Cruz");
        name.add("Trainer:Eidur Gudjohnsen");
        name.add("Trainer:Alberto Gilardino");
        name.add("Trainer:David Beckham");

        List<String> item = new ArrayList();
        item.add("Sport:yoga");
        item.add("Sport:spinning");
        item.add("Sport:aerobics");
        item.add("Sport:boxing");
        item.add("Sport:fitball");
        item.add("Sport:karate");

        List<Integer> picture=new ArrayList<>();
        picture.add(R.drawable.trainer1);
        picture.add(R.drawable.trainer2);
        picture.add(R.drawable.trainer3);
        picture.add(R.drawable.trainer1);
        picture.add(R.drawable.trainer2);
        picture.add(R.drawable.trainer3);


        //添加适配器，并监听每一个项目
        MyListAdapter mAdapter;

        mAdapter = new MyListAdapter(name,item,picture, R.layout.layout_list_item,this.getActivity());

        mAdapter.setOnItemClickListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==0)
                {
                    Intent intent=new Intent(getActivity(),Trainer_introduction.class);
                    startActivity(intent);
                }
                else if(position==1)
                {
                    /*Intent intent=new Intent(getActivity(),loginApp.class);
                    startActivity(intent);*/
                }

            }
        });

        mRecyclerView.setAdapter(mAdapter);

        /*//整体监听
        mAdapter.setOnClickListener(new MyListAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(),"可以响应", Toast.LENGTH_SHORT).show();
            }
        });*/


        //分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),DividerItemDecoration.VERTICAL));

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
