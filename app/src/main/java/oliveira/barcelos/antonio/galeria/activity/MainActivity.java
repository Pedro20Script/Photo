package oliveira.barcelos.antonio.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import oliveira.barcelos.antonio.galeria.R;
import oliveira.barcelos.antonio.galeria.adapter.MyAdapter;
import oliveira.barcelos.antonio.galeria.model.MyItem;

public class MainActivity extends AppCompatActivity {
    MyAdapter myAdapter;

    //Número da chamada do intent
    static int NEW_ITEM_REQUEST =1;
    //Lista myItems
    List<MyItem> itens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Pegando o botão flutuante
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //Colocando evento de click
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            //Função de click
            public void onClick(View v) {
                //Conexão entre as telas
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                //Transição para a segunda tela passando esperando um resposta -> (intent,número da chamada)
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
        RecyclerView rvItens = findViewById(R.id.rvItens);
        myAdapter = new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);
        rvItens.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Número de chamada bate
        if(requestCode == NEW_ITEM_REQUEST) {
            //Retornou com sucesso
            if(resultCode == Activity.RESULT_OK) {MyItem myItem = new MyItem();
                //Adiciona o titulo da imagem ao myItem
                myItem.title = data.getStringExtra("title");
                //Adiciona o descrição da imagem ao myItem
                myItem.description = data.getStringExtra("description");
                //Adiciona o caminho da imagem ao myItem
                myItem.photo = data.getData();
                //Adiciona o myItem a lista de myItems
                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
}