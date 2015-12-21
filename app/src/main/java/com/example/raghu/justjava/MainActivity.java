package com.example.raghu.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int numberOfCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = createOrderSummary(numberOfCoffees, numberOfCoffees * 5);
        //displayPrice(numberOfCoffees * 5);
//        displayOrderMessage(priceMessage);
//        displayToast("Your order is ready!");
        composeEmail(priceMessage);

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        } else {
////            Log.d("MainActivity", "No Apps available to handle the intent");
//            displayToast("No Apps available to handle the intent");
//        }

        }

    /**
     * This method will open the email app to send the order summary through email
     * @param addresses
     * @param subject
     * @param attachment
     */
    public void composeEmail(/*String[] addresses,*/
                             String message
                             /*, Uri attachment*/) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*"); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void displayToast(String message){
        Context context = getApplicationContext();
        //CharSequence text = "Your order is ready!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    /**
     *
     * @param view
     */
    public void increment(View view) {
        numberOfCoffees++;
        if (numberOfCoffees > 10) {
            numberOfCoffees = 10;
            displayToast("We serve only 10 coffees at a time");
        }
        display(numberOfCoffees);
    }

    public void decrement(View view) {
        numberOfCoffees--;
        if (numberOfCoffees < 1) {
            numberOfCoffees = 1;
            displayToast("A minimum order quantity is 1");
        }
        display(numberOfCoffees);
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
     * This method checks if the Whipped Cream checkbox is checked or not
     */
    //public boolean

    /**
     * This method creates a summary of the order and passes it back to be displayed on the screen
     */
    private String createOrderSummary(int quantity, int totalPrice){
        String orderSummary;
        //orderSummary = "Name: Raghu\n";
        EditText personName = (EditText) findViewById(R.id.name_field);
        orderSummary = personName.getText().toString() + "\n";
        if (personName.getText().toString().isEmpty()) {
            displayToast("Please enter your Name!");
            return("Anonymous orders not accepted");
        }
        orderSummary += "numberOfCoffees: " + quantity;
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        orderSummary += "\nWhipped Cream? " + hasWhippedCream.isChecked();
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        orderSummary += "\nChocolate? " + hasChocolate.isChecked();
        orderSummary += "\nTotal: " +  totalPrice;
        orderSummary += "\nThank You!";
        /*if(hasWhippedCream.isChecked()){
            Log.d("Main Activity","Whipped Cream is checked" + hasWhippedCream.isChecked());
        }
        else {
            Log.d("Main Activity","Whipped Cream is not checked" + hasWhippedCream.isChecked());
        } */
        return orderSummary;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        //return NumberFormat.getCurrencyInstance().format(number);
        TextView orderSummaryView = (TextView) findViewById(R.id.order_summary_view);
        orderSummaryView.setText("Amount Due: " + NumberFormat.getCurrencyInstance().format(number) + "\nThank You");
    }

    private void displayOrderMessage(String message){
        TextView orderSummaryView = (TextView) findViewById(R.id.order_summary_view);
        orderSummaryView.setText(message);
    }
}