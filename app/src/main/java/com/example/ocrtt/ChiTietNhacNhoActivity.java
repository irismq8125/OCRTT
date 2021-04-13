package com.example.ocrtt;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static com.example.ocrtt.splash.SplashActivity.mData;


public class ChiTietNhacNhoActivity extends AppCompatActivity {

    Switch sang, trua, chieu, toi;
    TimePicker gsang, gtrua, gchieu, gtoi;
    TextView ngaybd, ngaykt;
    ImageButton luu, back;
    String str_gio_sang = "--", str_phut_sang = "--";
    String str_gio_trua = "--", str_phut_trua = "--";
    String str_gio_chieu = "--", str_phut_chieu = "--";
    String str_gio_toi = "--", str_phut_toi = "--";
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nhac_nho);
        AnhXa();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        gsang.setIs24HourView(true);
        gtrua.setIs24HourView(true);
        gchieu.setIs24HourView(true);
        gtoi.setIs24HourView(true);

        sang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sang.isChecked()){
                    gsang.setVisibility(View.VISIBLE);
                    gsang.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                        @Override
                        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                            int gio_sang = gsang.getHour();
                            int phut_sang = gsang.getMinute();
                            str_gio_sang = String.valueOf(gio_sang);
                            if(phut_sang < 10)
                                str_phut_sang = "0" + String.valueOf(phut_sang);
                            else
                                str_phut_sang = String.valueOf(phut_sang);
                        }
                    });
                }
                else {
                    gsang.setVisibility(View.GONE);
                }
            }
        });

        trua.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(trua.isChecked()){
                    gtrua.setVisibility(View.VISIBLE);
                    gtrua.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                        @Override
                        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                            int gio_trua = gtrua.getHour();
                            int phut_trua = gtrua.getMinute();

                            if(gio_trua > 12)
                                gio_trua -= 12;

                            str_gio_trua = String.valueOf(gio_trua);
                            if(phut_trua < 10)
                                str_phut_trua = "0" + String.valueOf(phut_trua);
                            else
                                str_phut_trua = String.valueOf(phut_trua);
                        }
                    });
                }
                else {
                    gtrua.setVisibility(View.GONE);
                }
            }
        });

        chieu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chieu.isChecked()){
                    gchieu.setVisibility(View.VISIBLE);
                    gchieu.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                        @Override
                        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                            int gio_chieu = gchieu.getHour();
                            int phut_chieu = gchieu.getMinute();

                            if(gio_chieu > 12)
                                gio_chieu -= 12;

                            str_gio_chieu = String.valueOf(gio_chieu);
                            if(phut_chieu < 10)
                                str_phut_chieu = "0" + String.valueOf(phut_chieu);
                            else
                                str_phut_chieu = String.valueOf(phut_chieu);
                        }
                    });
                }
                else {
                    gchieu.setVisibility(View.GONE);
                }
            }
        });

        toi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(toi.isChecked()){
                    gtoi.setVisibility(View.VISIBLE);
                    gtoi.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                        @Override
                        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                            int gio_toi = hourOfDay;
                            int phut_toi = minute;

                            if(gio_toi > 12)
                                gio_toi -= 12;

                            str_gio_toi = String.valueOf(gio_toi);

                            if(phut_toi < 10)
                                str_phut_toi = "0" + String.valueOf(phut_toi);
                            else
                                str_phut_toi = String.valueOf(phut_toi);
                            }
                    });
                }
                else {
                    gtoi.setVisibility(View.GONE);
                }
            }
        });

        ngaybd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(v.getContext());
                d.setContentView(R.layout.ngaysinh);
                final DatePicker date = (DatePicker) d.findViewById(R.id.ngaysinh);

                Button xacnhan = (Button) d.findViewById(R.id.ngaysinh_xacnhan);
                xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int ngay = date.getDayOfMonth();
                        int thang = date.getMonth() + 1;
                        int nam = date.getYear();
                        ngaybd.setText(ngay + "/" + thang + "/" + nam);
                        d.dismiss();
                    }
                });
                d.show();
            }
        });

        ngaykt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(v.getContext());
                d.setContentView(R.layout.ngaysinh);
                final DatePicker date = (DatePicker) d.findViewById(R.id.ngaysinh);

                Button xacnhan = (Button) d.findViewById(R.id.ngaysinh_xacnhan);
                xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int ngay = date.getDayOfMonth();
                        int thang = date.getMonth() +1;
                        int nam = date.getYear();
                        ngaykt.setText(ngay + "/" + thang + "/" + nam);
                        d.dismiss();
                    }
                });
                d.show();
            }
        });

        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                final String ma = intent1.getStringExtra("MATHUOC");
                String nd = intent1.getStringExtra("NOIDUNG_QUACHITIET");

                if(str_gio_sang.contains("--") == false){
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(str_gio_sang));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(str_phut_sang));
                    Intent actionIntent = new Intent(ChiTietNhacNhoActivity.this,AlarmSang.class);
                    actionIntent.putExtra("DON_THUOC",ma);
                    actionIntent.putExtra("THOI_GIAN_BD",ngaybd.getText().toString().trim());
                    actionIntent.putExtra("THOI_GIAN_KT",ngaykt.getText().toString().trim());
                    actionIntent.putExtra("TIME_SANG",str_gio_sang + ":" + str_phut_sang);
                    actionIntent.putExtra("TIME_TRUA",str_gio_trua + ":" + str_phut_trua);
                    actionIntent.putExtra("TIME_CHIEU",str_gio_chieu + ":" + str_phut_chieu);
                    actionIntent.putExtra("TIME_TOI",str_gio_toi + ":" + str_phut_toi);
                    pendingIntent = PendingIntent.getBroadcast(ChiTietNhacNhoActivity.this,0,actionIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                }
                if(str_gio_trua.contains("--") == false){
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(str_gio_trua));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(str_phut_trua));
                    Intent actionIntent = new Intent(ChiTietNhacNhoActivity.this,AlarmTrua.class);
                    actionIntent.putExtra("DON_THUOC",ma);
                    actionIntent.putExtra("THOI_GIAN_BD",ngaybd.getText().toString().trim());
                    actionIntent.putExtra("THOI_GIAN_KT",ngaykt.getText().toString().trim());
                    actionIntent.putExtra("TIME_SANG",str_gio_sang + ":" + str_phut_sang);
                    actionIntent.putExtra("TIME_TRUA",str_gio_trua + ":" + str_phut_trua);
                    actionIntent.putExtra("TIME_CHIEU",str_gio_chieu + ":" + str_phut_chieu);
                    actionIntent.putExtra("TIME_TOI",str_gio_toi + ":" + str_phut_toi);
                    pendingIntent = PendingIntent.getBroadcast(ChiTietNhacNhoActivity.this,0,actionIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                }
                if(str_gio_chieu.contains("--") == false){
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(str_gio_chieu));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(str_phut_chieu));
                    Intent actionIntent = new Intent(ChiTietNhacNhoActivity.this,AlarmChieu.class);
                    actionIntent.putExtra("DON_THUOC",ma);
                    actionIntent.putExtra("THOI_GIAN_BD",ngaybd.getText().toString().trim());
                    actionIntent.putExtra("THOI_GIAN_KT",ngaykt.getText().toString().trim());
                    actionIntent.putExtra("TIME_SANG",str_gio_sang + ":" + str_phut_sang);
                    actionIntent.putExtra("TIME_TRUA",str_gio_trua + ":" + str_phut_trua);
                    actionIntent.putExtra("TIME_CHIEU",str_gio_chieu + ":" + str_phut_chieu);
                    actionIntent.putExtra("TIME_TOI",str_gio_toi + ":" + str_phut_toi);
                    pendingIntent = PendingIntent.getBroadcast(ChiTietNhacNhoActivity.this,0,actionIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                }
                if(str_gio_toi.contains("--") == false){
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(str_gio_toi));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(str_phut_toi));
                    Intent actionIntent = new Intent(ChiTietNhacNhoActivity.this,AlarmToi.class);
                    actionIntent.putExtra("DON_THUOC",ma);
                    actionIntent.putExtra("THOI_GIAN_BD",ngaybd.getText().toString().trim());
                    actionIntent.putExtra("THOI_GIAN_KT",ngaykt.getText().toString().trim());
                    actionIntent.putExtra("TIME_SANG",str_gio_sang + ":" + str_phut_sang);
                    actionIntent.putExtra("TIME_TRUA",str_gio_trua + ":" + str_phut_trua);
                    actionIntent.putExtra("TIME_CHIEU",str_gio_chieu + ":" + str_phut_chieu);
                    actionIntent.putExtra("TIME_TOI",str_gio_toi + ":" + str_phut_toi);
                    pendingIntent = PendingIntent.getBroadcast(ChiTietNhacNhoActivity.this,0,actionIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                }

                NhacNho nn = new NhacNho(ma,
                        ngaybd.getText().toString().trim(),
                        ngaykt.getText().toString().trim(),
                        str_gio_sang + ":" + str_phut_sang,
                        str_gio_trua + ":" + str_phut_trua,
                        str_gio_chieu + ":" + str_phut_chieu,
                        str_gio_toi + ":" + str_phut_toi);
                mData.child("users").child("NhacNho").child(ma).setValue(nn);

                intent1 = new Intent(v.getContext(), NhacNhoActivity.class);
                startActivity(intent1);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DanhSachActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        sang = (Switch) findViewById(R.id.buoisang);
        gsang = (TimePicker) findViewById(R.id.giosang);

        trua = (Switch) findViewById(R.id.buoitrua);
        gtrua = (TimePicker) findViewById(R.id.giotrua);

        chieu = (Switch) findViewById(R.id.buoichieu);
        gchieu = (TimePicker) findViewById(R.id.giochieu);

        toi = (Switch) findViewById(R.id.buoitoi);
        gtoi = (TimePicker) findViewById(R.id.giotoi);

        ngaybd = (TextView) findViewById(R.id.ngaybatdau);
        ngaykt = (TextView) findViewById(R.id.ngayketthuc);

        luu = (ImageButton) findViewById(R.id.luu_nhacnho);
        back = (ImageButton) findViewById(R.id.img_back_chitietnhacnho);
    }
}