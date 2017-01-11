package com.example.monic.cvgenerator.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.monic.cvgenerator.HomeActivity;
import com.example.monic.cvgenerator.R;

public class ProgressView extends View {

    public ProgressView(Context context){
        super(context);
        initializeTools();
    }

    Paint paint;
    RectF rect;

    private void initializeTools() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        rect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        rect.set(0, 0, width,width);
        paint.setColor(getContext().getResources().getColor((R.color.emptyPieChart)));
        canvas.drawArc(rect, -90, 360, true, paint);
        int currentAngle = -90;
        if (HomeActivity.profile != null) {
            paint.setColor(getContext().getResources().getColor((R.color.personalInfoPieChart)));
            canvas.drawArc(rect, currentAngle, 45, true, paint);
            currentAngle += 45;
            paint.setColor(getContext().getResources().getColor((R.color.personalInfoPieChart)));
            canvas.drawRect(0, width, 100, width + 100, paint);
            paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
            canvas.drawText("Personal Information",150,width+60,paint);
            width+=150;
            if (HomeActivity.profile.getSocialNetworksMap().isEmpty() == false) {
                paint.setColor(getContext().getResources().getColor((R.color.socialNetworksPieChart)));
                canvas.drawArc(rect, currentAngle, 45, true, paint);
                currentAngle += 45;
                paint.setColor(getContext().getResources().getColor((R.color.socialNetworksPieChart)));
                canvas.drawRect(0, width, 100, width + 100, paint);
                paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
                canvas.drawText("Social Networks",150,width+60,paint);
                width+=150;
            }
            if (HomeActivity.profile.getEducationArrayList().isEmpty() == false) {
                paint.setColor(getContext().getResources().getColor((R.color.educationPieChart)));
                canvas.drawArc(rect, currentAngle, 45, true, paint);
                currentAngle += 45;
                paint.setColor(getContext().getResources().getColor((R.color.educationPieChart)));
                canvas.drawRect(0, width, 100, width + 100, paint);
                paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
                canvas.drawText("Education",150,width+60,paint);
                width+=150;
            }
            if (HomeActivity.profile.getWorkExperienceArrayList().isEmpty() == false) {
                paint.setColor(getContext().getResources().getColor((R.color.workPieChart)));
                canvas.drawArc(rect, currentAngle, 45, true, paint);
                currentAngle += 45;
                paint.setColor(getContext().getResources().getColor((R.color.workPieChart)));
                canvas.drawRect(0, width, 100, width + 100, paint);
                paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
                canvas.drawText("Work Experience",150,width+60,paint);
                width+=150;
            }
            if (HomeActivity.profile.getLanguagesArrayList().isEmpty() == false) {
                paint.setColor(getContext().getResources().getColor((R.color.languagesPieChart)));
                canvas.drawArc(rect, currentAngle, 45, true, paint);
                currentAngle += 45;
                paint.setColor(getContext().getResources().getColor((R.color.languagesPieChart)));
                canvas.drawRect(0, width, 100, width + 100, paint);
                paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
                canvas.drawText("Languages",150,width+60,paint);
                width+=150;
            }
            if (HomeActivity.profile.getItSkillsArrayList().isEmpty() == false) {
                paint.setColor(getContext().getResources().getColor((R.color.ITPieChart)));
                canvas.drawArc(rect, currentAngle, 45, true, paint);
                currentAngle += 45;
                paint.setColor(getContext().getResources().getColor((R.color.ITPieChart)));
                canvas.drawRect(0, width, 100, width + 100, paint);
                paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
                canvas.drawText("IT Skills",150,width+60,paint);
                width+=150;
            }
            if (HomeActivity.profile.getOtherSkillsArrayList().isEmpty() == false) {
                paint.setColor(getContext().getResources().getColor((R.color.skillsPieChart)));
                canvas.drawArc(rect, currentAngle, 45, true, paint);
                currentAngle += 45;
                paint.setColor(getContext().getResources().getColor((R.color.skillsPieChart)));
                canvas.drawRect(0, width, 100, width + 100, paint);
                paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
                canvas.drawText("Other skills",150,width+60,paint);
                width+=150;
            }
            if (HomeActivity.profile.getCertificatesArrayList().isEmpty() == false) {
                paint.setColor(getContext().getResources().getColor((R.color.certificatesPieChart)));
                canvas.drawArc(rect, currentAngle, 45, true, paint);
                currentAngle += 45;
                paint.setColor(getContext().getResources().getColor((R.color.certificatesPieChart)));
                canvas.drawRect(0, width, 100, width + 100, paint);
                paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
                canvas.drawText("Certificates",150,width+60,paint);
                width+=150;
            }
        }
        if(currentAngle!=270) {
            paint.setColor(getContext().getResources().getColor((R.color.emptyPieChart)));
            canvas.drawRect(0, width, 100, width + 100, paint);
            paint.setColor(getContext().getResources().getColor((R.color.colorPrimaryDark)));
            canvas.drawText("Incomplete", 150, width + 60, paint);
            width += 150;
        }
    }
}
