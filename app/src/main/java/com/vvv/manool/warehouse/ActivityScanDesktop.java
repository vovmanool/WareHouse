package com.vvv.manool.warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import com.vvv.manool.warehouse.ui.camera.CameraSource;
import com.vvv.manool.warehouse.ui.camera.CameraSourcePreview;

import com.vvv.manool.warehouse.ui.camera.GraphicOverlay;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class ActivityScanDesktop extends AppCompatActivity {

    private TextView tvScanBarcode,tvScanArt,tvScanName,tvScanPrice;
    private Switch swScanFlash;
    private Button btnScanScan;

    private static final int RC_BARCODE_CAPTURE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_desktop);

        tvScanBarcode=findViewById(R.id.tv_asd_barcode);
        tvScanArt=findViewById(R.id.tv_asd_art);
        tvScanName=findViewById(R.id.tv_as_name);
        tvScanPrice=findViewById(R.id.tv_asd_price);
        swScanFlash=findViewById(R.id.sw_asd_flash);
        btnScanScan=findViewById(R.id.btn_asd_scan);

        btnScanScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), BarcodeCaptureActivity.class);
                intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                intent.putExtra(BarcodeCaptureActivity.UseFlash, swScanFlash.isChecked());
                startActivityForResult(intent, RC_BARCODE_CAPTURE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    //statusMessage.setText(R.string.barcode_success);
                    tvScanBarcode.setText(barcode.displayValue);
                    //Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    tvScanBarcode.setText(R.string.barcode_failure);
                    //Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                tvScanBarcode.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
