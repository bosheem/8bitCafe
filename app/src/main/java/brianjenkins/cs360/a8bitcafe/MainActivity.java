package brianjenkins.cs360.a8bitcafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    private  ImageButton toMapBtn;                         //create toMapBtn variable
    private Button launchInsta;                           //create launchInsta variable
    private ImageButton toMenuBtn;                        //create toMenuBtn variable
    private  ImageButton toHomeBtn;                        //create toHomeBtn variable



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        launchInsta = (Button) findViewById(R.id.launchInsta);          //get the XML of the button
        launchInsta.setOnClickListener(new View.OnClickListener(){      //set click to run the launchInsta method
            @Override
            public void onClick(View view){
                Intent instaIntent = launchInsta(MainActivity.this);
                startActivity(instaIntent);
            }
        });
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

    public static Intent launchInsta(Context context){                      //start the launchInsta class

        try{                                                                //check to see if instagram app is installed and either launch the app or open the browser
            context.getPackageManager()
                    .getPackageInfo("cominstagram.android", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/_u/snhu/"));
        }
        catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/snhu"));
        }
    }

}


