package com.chigov.cafe_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView tvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //savedInstanceState -
        //позволяет сохранить данные
        // это ссылка на объект Bundle, который передается в метод
        // onCreate каждого действия Android.

        //onCreate() ожидает вызова с параметром Bundle
        //в качестве параметра, поэтому мы передаем savedInstanceState.

        super.onCreate(savedInstanceState); //метод onCreate
        setContentView(R.layout.activity_order_detail); //SetContentView используется для заполнения
        // окна с помощью
        // пользовательского интерфейса, предоставленного из файла макета с помощью setContentView
        tvOrder = findViewById(R.id.tvOrder);
        Intent intent  = getIntent(); //создаем интент
        if(intent.hasExtra("order")){
            String order = intent.getStringExtra("order");
            tvOrder.setText(order); //передача данных в текстовое поле для заказа
        }else{
            Intent backToLogin = new Intent(this,LoginActivity.class);
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