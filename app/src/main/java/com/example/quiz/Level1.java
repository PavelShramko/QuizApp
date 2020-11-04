package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0; // счетчик правильных ответов


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        // создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        //скругляем углы
        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);

        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);

        // Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);

        // Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);

        //делаем приложение во весь экран

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this); // создание окна
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок иалогового окна
        dialog.setContentView(R.layout.previewdialog); // путь к матеку диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  // прозрачный фон диалогового окна
        dialog.setCancelable(false); // окно нельзя закрыть кнопкой назад

        // кнопка, закрывающая диалоговое окно - начало
        TextView btnclose = (TextView) dialog.findViewById(R.id.bntclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // обрабатываем нажатие кнопки
                try {
                    // вернуться к выбору уровня
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch (Exception e){

                }
                dialog.dismiss(); // закрываем диалоговое окно
            }
        });
        // кнопка, закрывающая диалоговое окно - конец

        // кнопка продолжить - начало


        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // кнопка продолжить - конец


        dialog.show();



//Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); // создание окна
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок иалогового окна
        dialogEnd.setContentView(R.layout.dialogend); // путь к матеку диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  // прозрачный фон диалогового окна
        dialogEnd.setCancelable(false); // окно нельзя закрыть кнопкой назад

        // кнопка, закрывающая диалоговое окно - начало
        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.bntclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // обрабатываем нажатие кнопки
                try {
                    // вернуться к выбору уровня
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss(); // закрываем диалоговое окно
            }
        });
        // кнопка, закрывающая диалоговое окно - конец

        // кнопка продолжить - начало


        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent); finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
            }
        });

        // кнопка продолжить - конец


        // добавление функционала кнопке "Назад"

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch (Exception e){

                }
            }
        });

        // Массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };
        // Массив для прогресса игры - конец

        // Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
        // Подключаем анимацию - конец

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]); // достаем соответствующую картинку
        text_left.setText(array.text1[numLeft]); // достаем текст

        numRight = random.nextInt(10);
        // цикл для предотвращения одинакового числа

        while (numRight == numLeft){
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images1[numRight]); // достаем соответствующую картинку
        text_right.setText(array.text1[numRight]); // достаем текст

        // Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //Условие касания кратинки - начало
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если каснулся картинки - начало
                    img_right.setEnabled(false); // Блокируем вторую картинку
                    if(numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    } // Если каснулся картинка - конец
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил картинку - начало
                    if(numLeft>numRight){
                        if(count<20){
                            count++;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашивает - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашивает - конец

                    }else{
                        if(count>0){
                            if(count == 1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашивает - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашивает - конец
                    }
                    //Если отпустил картинку - конец
                    if(count==20){
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]); // достаем соответствующую картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.text1[numLeft]); // достаем текст

                        numRight = random.nextInt(10);
                        // цикл для предотвращения одинакового числа

                        while (numRight == numLeft){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]); // достаем соответствующую картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.text1[numRight]); // достаем текст

                        img_right.setEnabled(true); //Включаем правую картинку

                    }
                }
                //Условие касания картинки - конец
                return true;
            }
        });

        // Обрабатываем нажатие на левую картинку - конец

        // Обрабатываем нажатие на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //Условие касания кратинки - начало
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если каснулся картинки - начало
                    img_left.setEnabled(false); // Блокируем вторую картинку
                    if(numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    } // Если каснулся картинка - конец
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил картинку - начало
                    if(numLeft<numRight){
                        if(count<20){
                            count++;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашивает - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашивает - конец

                    }else{
                        if(count>0){
                            if(count == 1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашивает - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашивает - конец
                    }
                    //Если отпустил картинку - конец
                    if(count==20){
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]); // достаем соответствующую картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.text1[numLeft]); // достаем текст

                        numRight = random.nextInt(10);
                        // цикл для предотвращения одинакового числа

                        while (numRight == numLeft){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]); // достаем соответствующую картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.text1[numRight]); // достаем текст

                        img_left.setEnabled(true); //Включаем левую картинку

                    }
                }
                //Условие касания картинки - конец
                return true;
            }
        });

        // Обрабатываем нажатие на левую картинку - конец

    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent); finish();
        }catch (Exception e){

        }
    }
}