package com.terry.wheelview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

/***
 * 名称：	  TimeWheel
 * 作者：      Terry Tan
 * 创建时间：	  2016/5/18 8:19
 * 说明：
 **/

public class TimeWheel {

    private Context context;
    private AlertDialog.Builder time_Builder;
    private View dialogView = null;
    private WheelView stHourView;
    private WheelView stMinView;
    private WheelView endHourView;
    private WheelView endMinView;
    private int[] TimeDuan = {6, 0, 18, 0};
    private TextView time_alert;
    private OnWheelScrollListener time_Listener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {
            // TODO Auto-generated method stub
            //wheel.setCurrentItem(15, false);
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            // TODO Auto-generated method stub
            TimeDuan[0] = stHourView.getCurrentItem();
            TimeDuan[1] = stMinView.getCurrentItem();
            TimeDuan[2] = endHourView.getCurrentItem();
            TimeDuan[3] = endMinView.getCurrentItem();
        }
    };

    private AlertDialog alertDialog;

    public TimeWheel(Context context, final TimeListener listener) {
        this.context = context;
        time_Builder = new AlertDialog.Builder(context);
        alertDialog = time_Builder.setTitle("设置时间段").setView(pickTime())
                .setPositiveButton("确定", new AlertDialog.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        if (judgeTime()) {
                            time_alert.setVisibility(View.GONE);
                            dismissDialog();
                            listener.getTime(TimeDuan);
                        } else {
                            preventDismissDialog();
                            time_alert.setVisibility(View.VISIBLE);
                        }
                    }
                }).setNegativeButton("取消", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dismissDialog();
                    }
                }).show();
    }


    private View pickTime() {
        // TODO Auto-generated method stub
        dialogView = LayoutInflater.from(context).inflate(R.layout.wheel_time_duan, null);
        time_alert = (TextView) dialogView.findViewById(R.id.timefor_alert);
        stHourView = (WheelView) dialogView.findViewById(R.id.wheel_st_hour);
        NumericWheelAdapter stHourViewAdapter = new NumericWheelAdapter(
                context, 0, 23, "%02d");
        stHourViewAdapter.setLabel(" 时 ");
        // 开始 时
        stHourView.setCyclic(true);
        stHourView.setViewAdapter(stHourViewAdapter);
        stHourView.setVisibleItems(7);
        stHourView.setCurrentItem(TimeDuan[0]);
        stHourView.addScrollingListener(time_Listener);

        stMinView = (WheelView) dialogView.findViewById(R.id.wheel_st_min);
        NumericWheelAdapter stMinViewAdapter = new NumericWheelAdapter(
                context, 0, 59, "%02d");
        stMinViewAdapter.setLabel(" 分 ");
        // 开始 分
        stMinView.setCyclic(true);
        stMinView.setViewAdapter(stMinViewAdapter);
        stMinView.setVisibleItems(7);
        stMinView.setCurrentItem(TimeDuan[1]);
        stMinView.addScrollingListener(time_Listener);

        endHourView = (WheelView) dialogView.findViewById(R.id.wheel_end_hour);
        NumericWheelAdapter endHourViewAdapter = new NumericWheelAdapter(
                context, 0, 23, "%02d");
        endHourViewAdapter.setLabel(" 时 ");
        // 结束 时
        endHourView.setCyclic(true);
        endHourView.setViewAdapter(endHourViewAdapter);
        endHourView.setVisibleItems(7);
        endHourView.setCurrentItem(TimeDuan[2]);
        endHourView.addScrollingListener(time_Listener);

        endMinView = (WheelView) dialogView.findViewById(R.id.wheel_end_min);
        NumericWheelAdapter endMinViewAdapter = new NumericWheelAdapter(
                context, 0, 59, "%02d");
        endMinViewAdapter.setLabel(" 分 ");
        // 结束 分
        endMinView.setCyclic(true);
        endMinView.setViewAdapter(endMinViewAdapter);
        endMinView.setVisibleItems(7);
        endMinView.setCurrentItem(TimeDuan[3]);
        endMinView.addScrollingListener(time_Listener);

        return dialogView;
    }

    private void dismissDialog() {
        try {
            Field field = alertDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(alertDialog, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertDialog.dismiss();
    }

    private void preventDismissDialog() {
        try {
            Field field = alertDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(alertDialog, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Boolean judgeTime() {
        if (TimeDuan[0] > TimeDuan[2]) {
            return false;
        } else if ((TimeDuan[0] == TimeDuan[2]) && (TimeDuan[1] >= TimeDuan[3])) {
            return false;
        } else {
            return true;
        }
    }


}
