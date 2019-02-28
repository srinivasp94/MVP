package com.iprismech.alertnikkiresidence.activity.profile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.ViewInviteGuestActivity;
import com.iprismech.alertnikkiresidence.adapters.ConrtactSingleAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.ContactModel;
import com.iprismech.alertnikkiresidence.utilities.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ContactSingleActivity extends BaseAbstractActivity implements View.OnClickListener {
    private ImageView imgClose;
    private TextView txtitle;

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bundle bundle = new Bundle();
        /*bundle.putString("Key_Name", contactList.get(position).getContactName());
        bundle.putString("Key_Number", contactList.get(position).getContactNumber());*/
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_FAMILY_SCREEN, bundle);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private RecyclerView rv_contacts;
    private ArrayList<ContactModel> contactList = new ArrayList<>();
    private ConrtactSingleAdapter adapter;
    RelativeLayout llInvite;


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.contact_pick, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        llInvite.setOnClickListener(this);
        imgClose.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        llInvite = findViewById(R.id.llInvite);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Contacts");
        llInvite.setVisibility(View.GONE);
        rv_contacts = findViewById(R.id.rv_contacts);
        rv_contacts.setLayoutManager(manager);

        new ContactSingleActivity.LoadContacts().execute();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.llInvite:
                ArrayList<ContactModel> contactSelected = new ArrayList<>();
                for (int i = 0; i < contactList.size(); i++) {
                    if (contactList.get(i).isContactChecked()) {
                        contactSelected.add(contactList.get(i));
                    }
                }
                if (contactSelected.size() > 0) {
                    Bundle bundle = new Bundle();
//                    bundle.putParcelableArrayList("Key_Contacts", contactSelected);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIEW_INVITE_GUEST_SCREEN,bundle);
//                    Intent intent = new Intent(ContactSingleActivity.this, ViewInviteGuestActivity.class);
//                    intent.putParcelableArrayListExtra("Key_Contacts", contactSelected);
//                    startActivity(intent);
//                    finish();
                } else {
                    Common.showToast(ContactSingleActivity.this, "Please Select Contacts");
                }

                break;
        }
    }

/*
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
*/

    private class LoadContacts extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;

        @Override
        protected Void doInBackground(Void... voids) {
            contactList = readContacts();// Get contacts array list from this
            // method
            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(ContactSingleActivity.this, "Loading Contacts",
                    "Please Wait...");

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // If array list is not null and is contains value
            if (contactList != null && contactList.size() > 0) {

                // then set total contacts to subtitle
/*
                getSupportActionBar().setSubtitle(
                        contactList.size() + " Contacts");
*/
                adapter = null;
                if (adapter == null) {
                    adapter = new ConrtactSingleAdapter(ContactSingleActivity.this, contactList);
                    rv_contacts.setAdapter(adapter);// set adapter
                    adapter.setOnItemClickListener(new ConrtactSingleAdapter.OnitemClickListener() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {

                                case R.id.rlContact:
                                    //set date to add family activity(only name and phone)
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Key_Name", contactList.get(position).getContactName());
                                    bundle.putString("Key_Number", contactList.get(position).getContactNumber().replace(" ", "").trim());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_FAMILY_SCREEN, bundle);
                                    finish();
                                    break;
                            }

                        }
                    });
                }
                adapter.notifyDataSetChanged();
            } else {

                // If adapter is null then show toast
                Toast.makeText(ContactSingleActivity.this, "There are no contacts.",
                        Toast.LENGTH_LONG).show();
            }

            // Hide dialog if showing
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }

    private ArrayList<ContactModel> readContacts() {
        ArrayList<ContactModel> contactList = new ArrayList<ContactModel>();

        Uri uri = ContactsContract.Contacts.CONTENT_URI; // Contact URI
        Cursor contactsCursor = getContentResolver().query(uri, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC "); // Return
        // all
        // contacts
        // name
        // containing
        // in
        // URI
        // in
        // ascending
        // order
        // Move cursor at starting
        if (contactsCursor.moveToFirst()) {
            do {
                long contctId = contactsCursor.getLong(contactsCursor
                        .getColumnIndex("_ID")); // Get contact ID
                Uri dataUri = ContactsContract.Data.CONTENT_URI; // URI to get
                // data of
                // contacts
                Cursor dataCursor = getContentResolver().query(dataUri, null,
                        ContactsContract.Data.CONTACT_ID + " = " + contctId,
                        null, null);// Retrun data cusror represntative to
                // contact ID

                // Strings to get all details
                String displayName = "";
                String homePhone = "";
                String mobilePhone = "";
                String photoPath = "" + R.drawable.dummy; // Photo path
                byte[] photoByte = null;// Byte to get photo since it will come
                // in BLOB
                String homeEmail = "";
                String workEmail = "";
                String companyName = "";
                String title = "";

                // This strings stores all contact numbers, email and other
                // details like nick name, company etc.
                String contactNumbers = "";
                String contactEmailAddresses = "";
                String contactOtherDetails = "";

                // Now start the cusrsor
                if (dataCursor.moveToFirst()) {
                    displayName = dataCursor
                            .getString(dataCursor
                                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));// get
                    // the
                    // contact
                    // name
                    do {
                        /*if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {
                            nickName = dataCursor.getString(dataCursor
                                    .getColumnIndex("data1")); // Get Nick Name
                            contactOtherDetails += "NickName : " + nickName
                                    + "n";// Add the nick name to string

                        }*/

                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {

                            // In this get All contact numbers like home,
                            // mobile, work, etc and add them to numbers string
                            switch (dataCursor.getInt(dataCursor
                                    .getColumnIndex("data2"))) {
                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    homePhone = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactNumbers += "Home Phone : " + homePhone
                                            + "\n";
                                    break;

                               /* case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                    workPhone = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactNumbers += "Work Phone : " + workPhone
                                            + "n";
                                    break;*/

                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    mobilePhone = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactNumbers += ""
                                            + mobilePhone + "";
                                    break;

                            }
                        }
                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {

                            // In this get all Emails like home, work etc and
                            // add them to email string
                            switch (dataCursor.getInt(dataCursor
                                    .getColumnIndex("data2"))) {
                                case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
                                    homeEmail = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactEmailAddresses += "Home Email : "
                                            + homeEmail + "n";
                                    break;
                                case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
                                    workEmail = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactEmailAddresses += "Work Email : "
                                            + workEmail + "n";
                                    break;

                            }
                        }

                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
                            companyName = dataCursor.getString(dataCursor
                                    .getColumnIndex("data1"));// get company
                            // name
                            contactOtherDetails += "Coompany Name : "
                                    + companyName + "n";
                            title = dataCursor.getString(dataCursor
                                    .getColumnIndex("data4"));// get Company
                            // title
                            contactOtherDetails += "Title : " + title + "n";

                        }

                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)) {
                            photoByte = dataCursor.getBlob(dataCursor
                                    .getColumnIndex("data15")); // get photo in
                            // byte

                            if (photoByte != null) {

                                // Now make a cache folder in file manager to
                                // make cache of contacts images and save them
                                // in .png
                                Bitmap bitmap = BitmapFactory.decodeByteArray(
                                        photoByte, 0, photoByte.length);
                                File cacheDirectory = getBaseContext()
                                        .getCacheDir();
                                File tmp = new File(cacheDirectory.getPath()
                                        + "/_androhub" + contctId + ".png");
                                try {
                                    FileOutputStream fileOutputStream = new FileOutputStream(
                                            tmp);
                                    bitmap.compress(Bitmap.CompressFormat.PNG,
                                            100, fileOutputStream);
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                } catch (Exception e) {
                                    // TODO: handle exception
                                    e.printStackTrace();
                                }
                                photoPath = tmp.getPath();// finally get the
                                // saved path of
                                // image
                            }

                        }

                    } while (dataCursor.moveToNext()); // Now move to next
                    // cursor

                    contactList.add(new ContactModel(Long.toString(contctId), displayName, mobilePhone, false));// Finally add
                    // items to
                    // array list
                }

            } while (contactsCursor.moveToNext());
        }
        return contactList;
    }
}
