package tsingpro.com.zxingnetdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * @创建者 94941
 * @创建时间 2018/1/30
 * @描述 ${TODO}
 */
public class QRCodeActivity extends AppCompatActivity {

    private EditText  mEt;
    private Button    mBtnLogo;
    private Button    mBtnNoLogo;
    private ImageView mIv;

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        mEt = findViewById(R.id.et);
        mBtnLogo = findViewById(R.id.btn_logo);
        mBtnNoLogo = findViewById(R.id.btn_no);
        mIv = findViewById(R.id.iv);

        /**
         * 生成二维码图片
         */
        mBtnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textContent = mEt.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(QRCodeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mEt.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                mIv.setImageBitmap(mBitmap);
            }
        });

        /**
         * 生成不带logo的二维码图片
         */
        mBtnNoLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textContent = mEt.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(QRCodeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mEt.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
                mIv.setImageBitmap(mBitmap);
            }
        });
    }
}
