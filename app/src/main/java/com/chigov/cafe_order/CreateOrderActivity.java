package com.chigov.cafe_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; //Намерение (Intent) - это механизм для описания одной операции -
// выбрать фотографию, отправить письмо, сделать звонок, запустить браузер и перейти по указанному адресу.
import android.os.Bundle; //Bundle – это класс, реализующий ассоциативный массив, т.е. хранящий пары ключ-значение.
import android.view.View; // строительные блоками Android UI
import android.widget.CheckBox; // виджет чекбокс
import android.widget.RadioButton; //виджет радио баттон
import android.widget.Spinner; //виджет спинера аналог выпадающего списка комбо-бокс
import android.widget.TextView; //подключение компонентов к проекту

public class CreateOrderActivity extends AppCompatActivity { //создается класс публичный orderactivity
    private TextView tvHello; //создаем переменные
    private TextView tvAdditions; //добавки к напитку
    private CheckBox cbLemon; //выбор лимона
    private CheckBox cbSugar; //выбор сахара
    private CheckBox cbMilk; //молока
    private Spinner spinnerTea; //чай
    private Spinner spinnerCoffee;//кофе в выпадающем списке
    private String drinkType; //тип напитка выберите пж
    private String name; //строка ввода имени
    private String password;// строка ввода пароля
    private StringBuilder builderAdditions;
//public: публичный, общедоступный класс или член класса. Поля и методы, объявленные с модификатором public, видны другим классам из текущего пакета и из внешних пакетов.



    @Override //Переопределение метода
 //Переопределение позволяет взять какой-то метод родительского класса и написать в каждом классе-наследнике
    // свою реализацию этого метода. Новая реализация «заменит» родительскую в дочернем классе.
    protected void onCreate(Bundle savedInstanceState) { //в классе обязательно должен быть метод onCreate(), которая задаёт начальную установку параметров при инициализации активности
        super.onCreate(savedInstanceState); //Это метод, который вызывается при создании деятельности. Т. е. он говорит программе что этот код нужно обрабатывать.
        setContentView(R.layout.activity_create_order); //Всякий раз, когда вы хотите изменить текущий внешний вид действия или при переходе
        // от одного действия к другому, новое действие должно иметь дизайн для отображения. Мы вызываем setContentView в onCreate с желаемым дизайном в качестве аргумента.
        Intent intent = getIntent(); //создаем интент
        if (intent.hasExtra("name") && intent.hasExtra("password")) { //условие
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }else{
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        drinkType = getString(R.string.tea); //переменная передает значение типа напитка метод для получения строки
        tvHello = findViewById(R.id.tvHello); //findViewById. Он по ID возвращает View. текст приветствия
        tvAdditions = findViewById(R.id.tvAdditions); // по ID возвращает View текст добавок на экране приветствия
        cbLemon = findViewById(R.id.checkboxLemon); //лимон
        cbMilk = findViewById(R.id.checkboxMilk); //молоко
        cbSugar = findViewById(R.id.checkboxSugar); //сахар
        spinnerCoffee = findViewById(R.id.spinnerCoffee); //кофе выбор в списке
        spinnerTea = findViewById(R.id.spinnerTea); //чай выбор
        String additions = String.format(getString(R.string.what_add_to_your_drink),drinkType); //переменная строкового типа передает информацию которую пользователь вводит в поля с выбором
        tvAdditions.setText(additions); // передача данных с добавками

        builderAdditions = new StringBuilder(); //передача добавок в напиток
      //  возникает необходимость сделать много изменений в строке символов.
        //Класс StringBuilder обладает большей производительностью.
        // Однако он не синхронизирован, поэтому его не нужно использовать в тех случаях, когда к изменяемой строке обращаются несколько потоков.

        String helloText = String.format(getString(R.string.hello_client_you_choose),name);
        tvHello.setText(helloText);

    }

    public void onClickChangeDrink(View view) {//в качестве параметра передается данная кнопка
        RadioButton button = (RadioButton) view;
        int id = button.getId();//какя кнопка нажата

        if (id == R.id.rbTea){
            drinkType = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            cbLemon.setVisibility(View.VISIBLE);
        }else if (id == R.id.rb_Coffee){
            drinkType = getString(R.string.coffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            cbLemon.setVisibility(View.INVISIBLE);
        }
        String additions = String.format(getString(R.string.what_add_to_your_drink),drinkType);
        tvAdditions.setText(additions);
    }

    public void onClickSendOrder(View view) {
        //очистить builderAdditions
        builderAdditions.setLength(0);
        //пройтись по всем checkbox
        if (cbMilk.isChecked()){
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if (cbSugar.isChecked()){
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
        if (cbLemon.isChecked() && drinkType.equals(getString(R.string.tea))){
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        String optionOfDrink = "";
        if (drinkType.equals(getString(R.string.tea))){
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        }else{
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }

        String order = String.format(getString(R.string.order),name,password,drinkType,optionOfDrink);
        String additions;
        //добавляем строку с добавками
        if (builderAdditions.length() > 0){
            additions = "\n" + getString(R.string.necessary_adds) + builderAdditions.toString();
        }
        else{additions = "";
        }
        String fullOrder = order + additions;

        Intent intent = new Intent(this,OrderDetailActivity.class);
        intent.putExtra("order",fullOrder);
        startActivity(intent);


    }
}