package com.apps.franco.appcafeteria; /**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.franco.appcafeteria.R;

import java.text.NumberFormat;

import static android.R.attr.borderlessButtonStyle;
import static android.R.attr.name;
import static android.os.Build.VERSION_CODES.M;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

     int Price=5;
     int Quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String createOrderSummary(String nameEditText,int price, boolean whippedCream, boolean hasChocolate){
        String message= " Name:"+nameEditText;
        message += "\n Order with Whipped Cream?"+whippedCream;
        message += "\n Order with Chocolate?"+hasChocolate;
        message += "\n Quantity:"+Quantity;
        message +="\n Price:$"+price;
        message +="\n Thank You!";
        return message;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void  submitOrder(View view) {

        EditText editname=(EditText)findViewById(R.id.Name);
        String nameEditText= editname.getText().toString();

        CheckBox whippedCreamCheckbox= (CheckBox) findViewById(R.id.checkbox_for_whipped_cream);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked();

        CheckBox hasChocolateCheckbox = (CheckBox) findViewById (R.id.checkbox_chocolate);
        boolean hasChocolate = hasChocolateCheckbox.isChecked();
        int price=calculatePrice(hasWhippedCream,hasChocolate);


        String message=createOrderSummary(nameEditText,price, hasWhippedCream,hasChocolate);

        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT," Order for:"+nameEditText);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }

    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate) {
        int basePrice=5;

        if(hasWhippedCream) {
            basePrice+=+1;

        }if(hasChocolate){
            basePrice+=+2;
        }

        return basePrice *Quantity;

    }
     public void increment(View view){
         if(Quantity==100){

             Toast.makeText(this,"You cannot order more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
             return;
         }

         Quantity= Quantity+1;
         displayQuantity(Quantity);

     }
    public void decrement(View view) {
        if(Quantity==1){

            Toast.makeText(this,"You cannot order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        Quantity = Quantity - 1;
        displayQuantity(Quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int Quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + Quantity);
    }




}