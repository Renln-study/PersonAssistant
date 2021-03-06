package com.example.fragment.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.fragment.R;
import com.example.fragment.activity.IncomeItemActivity;
import com.example.fragment.activity.MainActivity;
import com.example.fragment.activity.NoteItemActivity;
import com.example.fragment.activity.UpdatePassword;

import cn.bmob.v3.BmobUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    private Button logoutButton,updatePasswordButton;

    private String userName;
    public MineFragment() {
        // Required empty public constructor
    }

//    对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入；
//     类似于FindById()
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog();
            }
        });
        updatePasswordButton=view.findViewById(R.id.updatePasswordButton);
        userName=(String)getArguments().get("username");

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(), UpdatePassword.class);
                intent.putExtra("username",userName);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setIcon(R.drawable.delete);
        normalDialog.setTitle("退出登陆确认");
        normalDialog.setMessage("确定退出?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BmobUser.logOut();
                        getActivity().finish();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }

}
