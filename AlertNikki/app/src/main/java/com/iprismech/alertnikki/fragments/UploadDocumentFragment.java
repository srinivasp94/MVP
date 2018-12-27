package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismech.alertnikki.R;

public class UploadDocumentFragment extends BaseAbstractFragment<Class> implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private LinearLayout ll_aadhar, ll_pan, ll_voterId;
    private ImageView iv_uploaded_doc, iv_aadhaar, iv_pan, iv_voter_id;
    //  private int GALLERY_DOC = 1, CAMERA_DOC = 2;
    //private String base64profile;
    private TextView btn_next_upload_doc;
    private String service_id, name, mobile_number, address, document_id = "";
    private EditText et_doc_id_num;
    private boolean image_selected_status = false;


    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.addservice_upload_doc, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        ll_aadhar = view.findViewById(R.id.ll_aadhar);
        ll_pan = view.findViewById(R.id.ll_pan);
        ll_voterId = view.findViewById(R.id.ll_voterid);
        iv_aadhaar = view.findViewById(R.id.iv_aadhar);
        iv_pan = view.findViewById(R.id.iv_pan);
        iv_voter_id = view.findViewById(R.id.iv_voterid);
        //iv_uploaded_doc = view.findViewById(R.id.iv_uploaded_image);
        btn_next_upload_doc = view.findViewById(R.id.btn_next_doc);
        et_doc_id_num = view.findViewById(R.id.et_doc_id_num);
        fragmentManager = getFragmentManager();

        Bundle bundle = getArguments();
        if (bundle != null) {
            service_id = bundle.getString("service_id", "");
            name = bundle.getString("name", "");
            address = bundle.getString("address", "");
            mobile_number = bundle.getString("mobile_number", "");
        }

        ll_aadhar.setOnClickListener(this);
        ll_pan.setOnClickListener(this);
        ll_voterId.setOnClickListener(this);
        btn_next_upload_doc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_aadhar:

                et_doc_id_num.setText("");
//                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//                StrictMode.setVmPolicy(builder.build());
//                //     permissionsRequest();
//                showPictureDialog("");


//                et_doc_id_num.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
//                et_doc_id_num.setInputType(InputType.TYPE_CLASS_NUMBER );
                document_id = "1";

                iv_aadhaar.setImageResource(R.mipmap.dummy_selected);
                iv_voter_id.setImageResource(R.drawable.dummy);
                iv_pan.setImageResource(R.drawable.dummy);

                break;
            case R.id.ll_pan:

                et_doc_id_num.setText("");
                document_id = "2";
                iv_pan.setImageResource(R.mipmap.dummy_selected);
                iv_voter_id.setImageResource(R.drawable.dummy);
                iv_aadhaar.setImageResource(R.drawable.dummy);

                break;
            case R.id.ll_voterid:
                et_doc_id_num.setText("");
                document_id = "3";
                iv_voter_id.setImageResource(R.mipmap.dummy_selected);
                iv_pan.setImageResource(R.drawable.dummy);
                iv_aadhaar.setImageResource(R.drawable.dummy);
                break;
            case R.id.btn_next_doc:
                if (document_id.isEmpty() || document_id == "") {
                    Toast.makeText(getActivity(), "Please Select document type", Toast.LENGTH_SHORT).show();
                } else if (et_doc_id_num.getText().toString().isEmpty() || et_doc_id_num.getText().toString() == "") {
                    Toast.makeText(getActivity(), "Please Enter Document ID number", Toast.LENGTH_SHORT).show();
                } else if (document_id.equalsIgnoreCase("1")) {
                    if(et_doc_id_num.getText().length()<12)
                    Toast.makeText(getActivity(), "Please enter valid Aadhar Card Number", Toast.LENGTH_SHORT).show();
                } else {
                    UploadPhotoFragment fragment = new UploadPhotoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("service_id", service_id);
                    bundle.putString("mobile_number", mobile_number);
                    bundle.putString("name", name);
                    bundle.putString("address", address);
                    bundle.putString("id_proof_type", document_id);
                    bundle.putString("id_proof_number", et_doc_id_num.getText().toString());
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.fm_container, fragment, "").addToBackStack("").commit();
                }
                break;
        }
    }

//    private void showPictureDialog(final String base64) {
//        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
//        pictureDialog.setTitle("Select Action");
//        String[] pictureDialogItems = {
//                "Select photo from gallery",
//                "Capture photo from camera"};
//        pictureDialog.setItems(pictureDialogItems,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                choosePhotoFromGallary(base64);
//                                break;
//                            case 1:
//                                takePhotoFromCamera(base64);
//                                break;
//                        }
//                    }
//                });
//        pictureDialog.show();
//    }
//
//    public void choosePhotoFromGallary(String base64) {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
//                    && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
//                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                Log.d("hhhh", "Permissions not granted");
//                // ask for permission
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
//            }
//        }
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_DOC);
//
//
//    }
//
//    private void takePhotoFromCamera(String base64) {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
//                    && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
//                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                Log.d("hhhh", "Permissions not granted");
//                // ask for permission
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
//            }
//        }
//        try {
//            //   FileName = System.currentTimeMillis() + ".jpg";
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//            startActivityForResult(intent, CAMERA_DOC);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_CANCELED) {
//            return;
//        } else if (requestCode == GALLERY_DOC) {
//            if (data != null) {
//                Uri choosenImage = data.getData();
//                if (choosenImage != null) {
//
//                    Bitmap bp = decodeUri(choosenImage, 200);
//                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                    bp.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
//                    iv_uploaded_doc.setImageBitmap(bp);
//
//                    byte[] byte_arr = bytes.toByteArray();
//                    base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
//                }
//            }
//        } else if (requestCode == CAMERA_DOC) {
//
//            // Variable.img_banner = profile;
//            Bitmap profilebit = (Bitmap) data.getExtras().get("data");
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            profilebit.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//            iv_uploaded_doc.setImageBitmap(profilebit);
//            byte[] byte_arr = stream.toByteArray();
//            base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
//        }
//    }
//
//    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
//        try {
//            // Decode image size
//            BitmapFactory.Options o = new BitmapFactory.Options();
//            o.inJustDecodeBounds = true;
//            BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o);
//            // The new size we want to scale to
//            // final int REQUIRED_SIZE =  size;
//            // Find the correct scale value. It should be the power of 2.
//            int width_tmp = o.outWidth, height_tmp = o.outHeight;
//            int scale = 1;
//            while (true) {
//                if (width_tmp / 2 < REQUIRED_SIZE
//                        || height_tmp / 2 < REQUIRED_SIZE) {
//                    break;
//                }
//                width_tmp /= 2;
//                height_tmp /= 2;
//                scale *= 2;
//            }
//            // Decode with inSampleSize
//            BitmapFactory.Options o2 = new BitmapFactory.Options();
//            o2.inSampleSize = scale;
//            return BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
