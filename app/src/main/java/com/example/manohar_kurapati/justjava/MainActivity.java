package com.example.manohar_kurapati.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


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
        CheckBox checkBoxWhipCream = (CheckBox) findViewById(R.id.whipping_cream_checkbox);
        if (checkBoxWhipCream.isChecked())
            creamAmount = 5;

        int totalAmount = (quantity * 5 ) + creamAmount;

        String summaryMessage = "Name : Manohar Kurapati";
        summaryMessage = summaryMessage + "\nQuantity : " + quantity;
        summaryMessage = summaryMessage + "\nTotal : £ " + totalAmount;
        summaryMessage = summaryMessage + "\n Thank you!";

        displayMessage(summaryMessage);
    }

    public void displayMessage(String message) {
        TextView summaryTextView = (TextView) findViewById(R.id.summary_text_view);
        summaryTextView.setText(message);
    }


    /**
     * This method is called when the + button is clicked.
     */
    public void incrementQuantity(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrementQuantity(View view) {
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


}
