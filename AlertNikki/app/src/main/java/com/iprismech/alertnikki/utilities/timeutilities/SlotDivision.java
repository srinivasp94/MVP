package com.iprismech.alertnikki.utilities.timeutilities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

public class SlotDivision {

    private Context context;


    public static final String DATE_FORMAT = "EEE, MMM d, yyyy";
    int callerId = -1;

    public static ArrayList<String> createSlots(Calendar startCal, Calendar endCal, int intervalInMinutes) {

        ArrayList<String> calenderSlots = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");

        Calendar temp = (Calendar) startCal.clone();
        temp.add(Calendar.MINUTE, intervalInMinutes);

        if (temp.getTimeInMillis() > endCal.getTimeInMillis()) {
            return null;
        }

        boolean sameDay = startCal.get(Calendar.DAY_OF_YEAR) == startCal.get(Calendar.DAY_OF_YEAR) &&
                endCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR);

        if (!sameDay) {
            return null;
        }

        while (temp.getTimeInMillis() < endCal.getTimeInMillis()) {
            String slot = format.format(startCal.getTime()) + " - " + format.format(temp.getTime());
            calenderSlots.add(slot);
            temp.add(Calendar.MINUTE, intervalInMinutes);
            startCal.add(Calendar.MINUTE, intervalInMinutes);
        }

        return calenderSlots;
    }


    public static void showDatePickerDialog(Context context, String dateText, final EditText datetext) {

        Date date = null;
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            if (dateText.equals(""))
                date = new Date();
            else
                date = dateFormatter.parse(dateText);
        } catch (Exception exp) {
            // In case of expense initializa date with new Date
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // calendar month 0-11
        int day = calendar.get(Calendar.DATE);
        // date picker initialization
        DatePickerDialog datePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                handleOnDateSet(datetext, dateFormatter, year, month, day);
            }
        }, year, month, day);
        datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePicker.setTitle("My date picker");
        datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", datePicker);
        datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Cancel button clicked
            }
        });
        datePicker.show();
    }

    /**
     * Method called when user select a date on date picker dialog
     *
     * @param year
     * @param month
     * @param day
     */
    public static void handleOnDateSet(EditText editText, SimpleDateFormat dateFormatter, int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        String formatedDate = dateFormatter.format(date);

        editText.setText(formatedDate);

    }

    public static String timeConvert(Context context, final String time) {
        String formattedDate = "";
        Date dateObj = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:SS a");
            dateObj = sdf.parse(time);
            System.out.println(dateObj);
            System.out.println(new SimpleDateFormat("K:mm").format(dateObj));
            formattedDate = new SimpleDateFormat("K:mm").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static String differenceTime(String inTime) {
        long diffinTime = 0;
        String differ = "";
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);
//        currentTime = DateFormat.getDateTimeInstance().format(new Date());


        Date d1 = null;
        Date d2 = null;

//        String diff = hours + ":" + mins;
        try {
            d1 = format.parse(formattedDate);
            d2 = format.parse(inTime);

            //in milliseconds
//            long diff = d2.getTime() - d1.getTime();
            long diff = d1.getTime() - d2.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
//            long diffDays = diff / (24 * 60 * 60 * 1000);
            differ= diffHours +"Hrs "+ ":" + diffMinutes +" Min";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return differ;
    }
}

