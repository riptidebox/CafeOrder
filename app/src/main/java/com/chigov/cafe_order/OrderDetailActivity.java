package com.chigov.cafe_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity { //публичный класс информации о заказе с расширением AppCompatActivity
    private TextView tvOrder; //поле создается переменная для заказа

    @Override//Переопределение метода
    //Переопределение позволяет взять какой-то метод родительского класса и написать в каждом классе-наследнике
    // свою реализацию этого метода. Новая реализация «заменит» родительскую в дочернем классе.
    protected void onCreate(Bundle savedInstanceState) { //savedInstanceState -
        //позволяет сохранить данные
        // это ссылка на объект Bundle, который передается в метод
        // onCreate каждого действия Android.

        //onCreate() ожидает вызова с параметром Bundle
        //в качестве параметра, поэтому мы передаем savedInstanceState.

        super.onCreate(savedInstanceState); //метод onCreate
        setContentView(R.layout.activity_order_detail); //Всякий раз, когда вы хотите изменить текущий внешний вид действия или при переходе
        // от одного действия к другому, новое действие должно иметь дизайн для отображения. дизайн берется из order_detail_activity

        tvOrder = findViewById(R.id.tvOrder); //переменная находится по ее индентификатору
        Intent intent  = getIntent(); //получает интент из _create_order_аctivity
        if(intent.hasExtra("order")){ //если заказ существует
            String order = intent.getStringExtra("order"); // строка заказ получает значение заказа из order_detail_activity
            tvOrder.setText(order); //переменной tvOrder передается заказ
        }else{ // если заказ не создан то
            Intent backToLogin = new Intent(this,LoginActivity.class); // создание нового интента для перехода на login_activity
            startActivity(backToLogin); //команда для запуска активности для перехода в другое окно
        }
    }
}

//При вращении экрана или при запуске другого действия вызывается метод protected
// void onSaveInstanceState(Bundle outState) , и действие уничтожается.
// Позже создается другой экземпляр действия и вызывается public void onCreate(Bundle
// savedInstanceState) .
// Когда создается первый экземпляр действия, bundle-это null; и если bundle не является null,
// действие продолжает какое-то дело, начатое его предшественником.