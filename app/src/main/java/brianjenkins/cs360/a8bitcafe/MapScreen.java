package brianjenkins.cs360.a8bitcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;



public class MapScreen extends AppCompatActivity {

    MainActivity mainActivity = new MainActivity();
    private ImageButton toHomeBtn;
    private  ImageButton toMapBtn;                         //create toMapBtn variable
    private ImageButton toMenuBtn;                        //create toMenuBtn variable

    private static final String TAG = "MapScreen";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        toHomeBtn = (ImageButton) findViewById(R.id.HomeScreen);
        toHomeBtn.setOnClickListener(new View.OnClickListener() {        //set click to run the HomeScreen method
            @Override
            public void onClick(View view) {
                openHomeScreen();
            }
        });

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


        if(isServicesOk()){
            init();
        }
    }

    private void init(){
        Button btnMap = (Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){
                Intent intent = new Intent(MapScreen.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean isServicesOk(){
        Log.d(TAG, "isServicesOk: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapScreen.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is okay and the user can make map requests.

            Log.d(TAG, "isServicesOk: Google play services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOk: An error occured but we can fix it");

            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapScreen.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this,"You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void openHomeScreen(){

        Intent intent = new Intent(this, MainActivity.class);   //method to start the mainActivity class
        getApplicationContext().startActivity(intent);
    }

    public void openMapActivity(){                                            //method to start the MapScreen class

        Intent intent = new Intent(this, MapScreen.class);
        startActivity(intent);
    }

    public void openCoffeeMenu(){                                            //method to start the coffeeMenu class

        Intent intent = new Intent(this, coffeeMenu.class);
        startActivity(intent);
    }

}
