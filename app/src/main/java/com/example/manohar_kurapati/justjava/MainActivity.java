package com.example.manohar_kurapati.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int creamAmount = 0;
        int chocAmount = 0;
        String name;
        String orderSummary;

        CheckBox checkBoxWhipCream = (CheckBox) findViewById(R.id.whipping_cream_checkbox);
        if (checkBoxWhipCream.isChecked())  creamAmount = 5;

        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.add_chocolate_checkbox);
        if (checkBoxChocolate.isChecked())  chocAmount = 2;

        EditText nameField = (EditText) findViewById(R.id.name_field);
        name = nameField.getText().toString();

        if (name.length() == 0){
            Toast.makeText(this,"Enter name to continue",Toast.LENGTH_SHORT).show();
            return;
        }

        orderSummary=calculatePrice(quantity,creamAmount,chocAmount,name);
//        displayMessage(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:sheetalkurapati@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just JAVA Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

//    /**
//     * This method will display message on the Order summary text view
//     * @param message final message to be displayed
//     */
//    public void displayMessage(String message) {
//        TextView summaryTextView = (TextView) findViewById(R.id.summary_text_view);
//        summaryTextView.setText(message);
//    }


    /**
     * This method is called when the + button is clicked.
     */
    public void incrementQuantity(View view) {
        if (quantity == 100) {
            Toast.makeText(this,"You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrementQuantity(View view) {
        if (quantity == 1) {
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method calculates the Final price and prepares the message to display
     * @param quantity of coffee ordered
     * @param creamAmount Whipping cream amount
     * @param chocAmount Chocolate amount if selected by user
     * @return String
     */
    public String calculatePrice(int quantity, int creamAmount, int chocAmount, String name){

        String summaryMessage = "Name : " + name;

        int totalAmount = (quantity * 5 ) + creamAmount + chocAmount;
        summaryMessage = summaryMessage + "\nQuantity : " + quantity;
        summaryMessage = summaryMessage + "\nTotal : £ " + totalAmount;
        summaryMessage = summaryMessage + "\n";

        if (creamAmount > 0) summaryMessage = summaryMessage + "\nAdded Whipped Cream : £5";
        if (chocAmount > 0) summaryMessage = summaryMessage + "\nAdded Chocolate : £2";

        summaryMessage = summaryMessage + "\nThank you!";

        return summaryMessage;

    }


}
