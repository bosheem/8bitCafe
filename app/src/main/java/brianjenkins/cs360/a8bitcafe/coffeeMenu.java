package brianjenkins.cs360.a8bitcafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class coffeeMenu extends AppCompatActivity {
    private ImageButton toMapBtn;                         //create toMapBtn variable
    private ImageButton toMenuBtn;                        //create toMenuBtn variable
    private ImageButton toHomeBtn;                        //create toHomeBtn variable

    private Button toYourOrder;

    private Button addToOrder1;
    private Button addToOrder2;
    private Button addToOrder3;
    private Button addToOrder4;

    public String coffee1 = "Coffee 1";
    public String coffee2 = "Coffee 2";
    public String coffee3 = "Coffee 3";
    public String coffee4 = "Coffee 4";

    public String coffeeHot = "Hot";
    public String coffeeIced = "Iced";
    public String coffeeBlend = "Blended";

    public double hotCoffee = 3.99;
    public double icedCoffee = 4.99;
    public double blendedCoffee = 5.99;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_menu);

        toMapBtn = (ImageButton) findViewById(R.id.toMapScreen);         //get the XML of the button
        toMapBtn.setOnClickListener(new View.OnClickListener() {         //set click to run the openMapActivity method
            @Override
            public void onClick(View view) {
                openMapActivity();
            }
        });

        toMenuBtn = (ImageButton) findViewById(R.id.MenuScreen);              //get the XML of the button from
        toMenuBtn.setOnClickListener(new View.OnClickListener() {        //set click to run the openCoffeeMenu method
            @Override
            public void onClick(View view) {
                openCoffeeMenu();
            }
        });

        toHomeBtn = (ImageButton) findViewById(R.id.HomeScreen);
        toHomeBtn.setOnClickListener(new View.OnClickListener() {        //set click to run the openCoffeeMenu method
            @Override
            public void onClick(View view) {
                openHomeScreen();
            }
        });

        toYourOrder = (Button) findViewById(R.id.yourOrder);         //get the XML of the button

        myDb = new DatabaseHelper(this);


        addToOrder1 = (Button) findViewById(R.id.addToOrder1);
        AddData1();
        addToOrder2 = (Button) findViewById(R.id.addToOrder2);
        AddData2();
        addToOrder3 = (Button) findViewById(R.id.addToOrder3);
        AddData3();
        addToOrder4 = (Button) findViewById(R.id.addToOrder4);
        AddData4();
        yourCart();

    }

    public void openMapActivity(){                                            //method to start the MapScreen class

        Intent intent = new Intent(this, MapScreen.class);
        startActivity(intent);
    }

    public void openCoffeeMenu(){                                            //method to start the coffeeMenu class

        Intent intent = new Intent(this, coffeeMenu.class);
        startActivity(intent);
    }

    public void openHomeScreen(){

        Intent intent = new Intent(this, MainActivity.class);   //method to start the mainActivity class
        getApplicationContext().startActivity(intent);

    }
    public void AddData1(){
        addToOrder1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isInserted = myDb.insertData(coffee1, coffeeHot, hotCoffee);
                        if(isInserted = true) {
                            Toast.makeText(coffeeMenu.this, "Added to order", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(coffeeMenu.this, "Error", Toast.LENGTH_LONG).show();
                    }


                }
        );
    }

    public void AddData2(){
        addToOrder1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isInserted = myDb.insertData(coffee2, coffeeHot, hotCoffee);
                        if(isInserted = true){
                            Toast.makeText(coffeeMenu.this, "Added to order", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(coffeeMenu.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData3(){
        addToOrder1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isInserted = myDb.insertData(coffee3, coffeeIced, icedCoffee);
                        if(isInserted = true){
                            Toast.makeText(coffeeMenu.this, "Added to order", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(coffeeMenu.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData4(){
        addToOrder1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isInserted = myDb.insertData(coffee4, coffeeBlend, blendedCoffee);
                        if(isInserted = true){
                            Toast.makeText(coffeeMenu.this, "Added to order", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(coffeeMenu.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void yourCart() {
        toYourOrder.setOnClickListener(new View.OnClickListener() {         //set click to view your order
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0){
                    //show message
                    showMessage("Error", "No order found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append(res.getString(0) + "\n");
                    buffer.append(res.getString(0) + "\n");
                    buffer.append(res.getString(0) + "\n");
                    buffer.append(res.getString(0) + "\n");
                }
                // show all data
                showMessage("Your Order", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
