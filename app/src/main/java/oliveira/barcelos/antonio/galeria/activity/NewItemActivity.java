package oliveira.barcelos.antonio.galeria.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import oliveira.barcelos.antonio.galeria.R;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        //Pegando o imagebutton
        ImageButton imgCI = findViewById(R.id.imbCl);
        //Adicionando evento de click ao botão
        imgCI.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent para abrir os arquivos fora do aplicativo
            Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            //Especificando o tipo imagem
            photoPickerIntent.setType("image/*");
            //Retornando a imagem selecionada
            startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Número da chamada igual ao original
            if(requestCode == PHOTO_PICKER_REQUEST) {
                //O resultado é um código de sucesso
                if(resultCode == Activity.RESULT_OK) {
                    //Definindo o caminho da foto
                    photoSelected = data.getData();
                    //Pegando o imageview
                    ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                    //Colocando a imagem no imageview
                    imvfotoPreview.setImageURI(photoSelected);
                }
            }
    }


}