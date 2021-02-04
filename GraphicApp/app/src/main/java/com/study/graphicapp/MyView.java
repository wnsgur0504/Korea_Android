package com.study.graphicapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    String TAG = this.getClass().getName();
    Paint paint;
    Thread gameThread;
    boolean gameFlag = false;//쓰레드를 실행할 지 여부를 결정
    int x = 300, y = 300;

    //자바에서 인스턴스 생성할거면, 아래의 생성자만 두면 되고
    public MyView(Context context) {
        super(context);
        Log.d(TAG, "MyView(Context context) 생성자 호출");
    }

    //xml에서 사용할거면, xml의 속성까지 넘겨받아야하므로, AttributeSet이 있는 생성자도
    //정의하자!!
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyView(Context context, AttributeSet attrs) 생성자 호출");
        Log.d(TAG, "attrs is " + attrs);
        paint = new Paint(Color.BLUE);
    }

    //자바스탠다드처럼, 모든 컴포넌트는 스스로를 그린다.
    //따라서 개발자가 그래픽처리를 주도하려면 onDraw() 메서드를 재정의하면 된다!
    //주의 javaSE에서는 팔레트가 Graphics가 객체이지만, 안드로이드에선 Canvas이다
    //즉 도화지가 아니다!
    @Override
    protected void onDraw(Canvas canvas) {
        //사각형 객체 생성
        RectF rect = new RectF(x, y, x + 100, y + 100);
        canvas.drawRect(rect, paint);
    }

    public void gameLoop() {
        while (gameFlag) {
            x++;
            y++;
            this.invalidate();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
