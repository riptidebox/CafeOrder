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

public class CreateOrderActivity extends AppCompatActivity { //создается класс публичный orderactivity с расширением AppCompatActivity
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
        //savedInstanceState -
                //позволяет сохранить данные
                // это ссылка на объект Bundle, который передается в метод
                // onCreate каждого действия Android.
        super.onCreate(savedInstanceState); //Это метод, который вызывается при создании деятельности. Т. е. он говорит программе что этот код нужно обрабатывать.
        setContentView(R.layout.activity_create_order); //Всякий раз, когда вы хотите изменить текущий внешний вид действия или при переходе
        // от одного действия к другому, новое действие должно иметь дизайн для отображения. дизайн берется из аctivity_create_order
        Intent intent = getIntent(); //создаем интент
        if (intent.hasExtra("name") && intent.hasExtra("password")) { //условие если есть имя и пароль то переменные получает имя и пароль
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }else{ //если поля пустые
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

        builderAdditions = new StringBuilder(); //создание переменной типа StringBuilder. обладает большей производительностью.
        // Однако он не синхронизирован, поэтому его не нужно использовать в тех случаях, когда к изменяемой строке обращаются несколько потоков.

        String helloText = String.format(getString(R.string.hello_client_you_choose),name);
        tvHello.setText(helloText); // передает hello текст, текстовому полю переменную

    }

    public void onClickChangeDrink(View view) { //метод для выбора напитков
        RadioButton button = (RadioButton) view;
        int id = button.getId();//какая кнопка нажата

        if (id == R.id.rbTea){ //если чай то пишет -
            drinkType = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            cbLemon.setVisibility(View.VISIBLE);
        }else if (id == R.id.rb_Coffee){ //если кофе то -
            drinkType = getString(R.string.coffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            cbLemon.setVisibility(View.INVISIBLE);
        }
        String additions = String.format(getString(R.string.what_add_to_your_drink),drinkType);
        tvAdditions.setText(additions);  //передает добавки , текстовому полю переменную
    }

    public void onClickSendOrder(View view) { //метод при нажатии создает заказ и создает новое окно
        //очистить builderAdditions
        builderAdditions.setLength(0); //начальное значение добавок
        //пройтись по всем checkbox
        if (cbMilk.isChecked()){ //если молоко выбрано
            builderAdditions.append(getString(R.string.milk)).append(" "); //используется метод StringBuilder для добавления молока
        }
        if (cbSugar.isChecked()){ //если сахар выбран


            builderAdditions.append(getString(R.string.sugar)).append(" ");//используется метод StringBuilder для добавления сахара
        }
        if (cbLemon.isChecked() && drinkType.equals(getString(R.string.tea))){  //если выбран лимон и выбран чай

            builderAdditions.append(getString(R.string.lemon)).append(" "); // //используется метод StringBuilder для добавления лимона
        }
        String optionOfDrink = ""; //строка выбора напитка
        if (drinkType.equals(getString(R.string.tea))){ //если выбран чай
            optionOfDrink = spinnerTea.getSelectedItem().toString(); //строка получает выбранный элемент
        }else{ //если выбран кофе
            optionOfDrink = spinnerCoffee.getSelectedItem().toString(); // строка получает выбранный элемент
        }

        String order = String.format(getString(R.string.order),name,password,drinkType,optionOfDrink); //формирование заказа
        String additions; //строка добавок

        if (builderAdditions.length() > 0){ //если заказ заполнен
            additions = "\n" + getString(R.string.necessary_adds) + builderAdditions.toString(); //строка добавок получает добавки
        }
        else{additions = ""; // если заказ пустой
        }
        String fullOrder = order + additions; // в строку заказа добавляется добавки и напиток

        Intent intent = new Intent(this,OrderDetailActivity.class); //создание нового интента
        intent.putExtra("order",fullOrder); //в интент вносится полный заказ
        startActivity(intent); //запускается новая активность


    }
}