package com.chigov.cafe_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity { //публичный класс авторизации с расширением AppCompatActivity
    private EditText etName; //поле создается переменная для имени
    private EditText etPassword; //создается поле переменная для пароля

    @Override //Переопределение метода
    //Переопределение позволяет взять какой-то метод родительского класса и написать в каждом классе-наследнике
    // свою реализацию этого метода. Новая реализация «заменит» родительскую в дочернем классе.
    protected void onCreate(Bundle savedInstanceState) { //в классе обязательно должен быть метод onCreate(), которая задаёт начальную установку параметров при инициализации активности
        super.onCreate(savedInstanceState); //Это метод, который вызывается при создании деятельности. Т. е. он говорит программе что этот код нужно обрабатывать.
        setContentView(R.layout.activity_login); //Всякий раз, когда вы хотите изменить текущий внешний вид действия или при переходе
        // от одного действия к другому, новое действие должно иметь дизайн для отображения. дизайн берется из аctivity_login
        etName = findViewById(R.id.etPersonName); //переменная находится по ее индентификатору
        etPassword = findViewById(R.id.etPassword);//переменная находится по ее индентификатору
    }

    public void onClickCreateOrder(View view) { // метод создания заказа при нажатии кнопки
        String name = etName.getText().toString().trim(); // создается строка имени по переменной имя
        String password = etPassword.getText().toString().trim(); // создается строка пароля по переменной пароль
        // Метод trim() — возвращает копию строки с пропущенными начальными и конечными пробелами, другими словами метод позволяет в Java удалить пробелы в начале и конце строки.
        if (!name.isEmpty() && !password.isEmpty()){ //если поля заполнены имя и пароль
        Intent intent = new Intent(this,CreateOrderActivity.class); //то создается новый интент
        intent.putExtra("name",name);// переменной имя передеается имя
        intent.putExtra("password",password);// переменной пароль передеается пароль
        startActivity(intent); //запуск новой актитвности
        }else{ //если поля не заполнены то выход всплывающее уведомление что необходимо заполнить все поля
            Toast toast = Toast.makeText(this, R.string.toast_messege_warning, Toast.LENGTH_SHORT);
            //Toast - класс для всплывающих уведомлений
            toast.setGravity(Gravity.CENTER,0,0); //параметры расположения уведомлений
            toast.show(); //метод вывода на активность
        }
    }
}