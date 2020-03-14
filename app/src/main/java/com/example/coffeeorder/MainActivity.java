package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextClassificationSessionFactory;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int nm=0;
    String priceMessage;
    boolean hasChecked;
    boolean hasChocalate;
    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void submitOrder(View view) {

        CheckBox whippedCream=(CheckBox)findViewById(R.id.whipped_cream_text);
         hasChecked=whippedCream.isChecked();

        CheckBox chocalateCream=(CheckBox)findViewById(R.id.chocolate_checkbox);
         hasChocalate=chocalateCream.isChecked();

         EditText text= (EditText) findViewById(R.id.Name_field);
          name= text.getText().toString();


        priceMessage= createOrderSummary();
         displayMessage(priceMessage);
         //displayPrice(nm*5);


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "ordering coffee for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }


    }


    public void display(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }



    /**
     * This method displays the given price on the screen.
     */
   /* private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Total: $"+number);
    }*/

    private void displayMessage(String message)
    {
        TextView messageText=(TextView) findViewById(R.id.messagetext);
        messageText.setText(""+message);
    }
    
   /* private void displayName(String prince_raj)
    {
        TextView messageName=(TextView) findViewById(R.id.messageName);

        messageName.setText(prince_raj);
    }*/
    
    



    public void increment(View view)
    {

        nm=nm+1;

  display(nm);

    }


    public void decrement(View view)
    {
        nm=nm-1;
        display(nm);
    }

    public String createOrderSummary()
    {
        String priceMessage=getString(R.string.name)+name+"\n";
        int price=nm*5;
        if(hasChecked)
        {
            price+=1;
        }
         if(hasChocalate)
         {
             price+=2;
         }

        priceMessage=priceMessage+getString(R.string.quantity_value)+nm+"\n";
        priceMessage+=getString(R.string.add_whipped_cream)+hasChecked+"\n";
        priceMessage+=getString(R.string.added_chocolate)+hasChocalate+"\n";
        priceMessage=priceMessage+getString(R.string.total)+price+"\n";
        priceMessage=priceMessage+getString(R.string.thank_you);
        return priceMessage;

    }



    private void calculatePrice() {
    }


}

