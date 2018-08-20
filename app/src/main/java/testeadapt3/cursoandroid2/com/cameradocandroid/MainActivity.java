package testeadapt3.cursoandroid2.com.cameradocandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.security.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private static final int COD_CAMERA = 100;

    @BindView( R.id.imagemCamera )
    ImageView imagemCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.CAMERA} ,0 );
        }
    }

    public void abrirCamera(View view) {
        tirarFotografia();
    }

    private void tirarFotografia() {
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        startActivityForResult( intent,COD_CAMERA );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COD_CAMERA && resultCode == RESULT_OK){
            //RECUPERAR A IMAGEM CAPTURADA USANDO BUNDLE:

            Bundle extras = data.getExtras();
            Bitmap imagemBit = (Bitmap) extras.get( "data" );// imagem chega do tipo bitMap
            imagemCamera.setImageBitmap( imagemBit ); //passa a imagem para o imagemView para ser mostrada
        }
        super.onActivityResult( requestCode, resultCode, data );
    }
}
