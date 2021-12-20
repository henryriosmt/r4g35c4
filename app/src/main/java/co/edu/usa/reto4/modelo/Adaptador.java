package co.edu.usa.reto4.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.usa.reto4.R;

public class Adaptador extends BaseAdapter {

    ArrayList<Entidad> itemLista;
    Context context;

    // CONEXION A LA BASE DE DATOS: SQLite
    MotorBaseDatosSQLite conectar;

    public Adaptador(ArrayList<Entidad> itemLista, Context context) {
        this.itemLista = itemLista;
        this.context = context;
    }

    /**
     * Devuelve el tamaño de la lista o número de objetos.
     * @return
     */
    @Override
    public int getCount() {
        return itemLista.size();
    }

    /**
     * Devuelve la posicion del  item que se está trabajando
     * @param posicion
     * @return
     */
    @Override
    public Object getItem(int posicion) {
        return itemLista.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return 0;
    }

    /**
     * Se ponen los valores de cada item.
     * Lo configuramos de manera similar a los fragments
     * @param posicion
     * @param v
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int posicion, View v, ViewGroup viewGroup) {


        Entidad datosItem = (Entidad) getItem(posicion);

        v = LayoutInflater.from(context).inflate(R.layout.item, null);

        ImageView imageView = (ImageView) v.findViewById(R.id.imagenItem);
        Button botonItem = (Button) v.findViewById(R.id.botonItem);
        TextView titulo = (TextView) v.findViewById(R.id.tituloItem);
        TextView descripcion = (TextView) v.findViewById(R.id.descripcionItem);
        CheckBox meGusta = (CheckBox) v.findViewById(R.id.meGustaItem);

        imageView.setImageResource(datosItem.getImagen());
        // imagen = datosItem.getImagen();
        //Bitmap bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
        titulo.setText(datosItem.getTitulo());
        descripcion.setText(datosItem.getDescripcion());
        //imageView.setImageBitmap(bitmap);


        conectar = new MotorBaseDatosSQLite(context,"TiendaProductos", null, 1);
        SQLiteDatabase db_escribir = conectar.getWritableDatabase();
        conectar.onUpgrade(db_escribir,1,2);

        meGusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(meGusta.isChecked()){
                        //int imgID = convertToId(datosItem.imagen);
                        Log.v("Adaptador", "id:"+ itemLista.get(posicion));
                        Toast.makeText(context, datosItem.imagen, Toast.LENGTH_LONG).show();
                        db_escribir.execSQL("INSERT INTO favoritos VALUES ('"+ (Integer) datosItem.imagen +"', '"+ datosItem.getTitulo() +"', '"+ datosItem.getDescripcion() +"') ");
                    }

            }
        });




        return v;
    }

    public int convertToId(String img){

        if(img == "res/drawable/chaqueta1.png"){
            return 2131165282;
        };
        if(img == "res/drawable/chaqueta2.png"){
            return 2131165283;
        };
        if(img == "res/drawable/chaqueta3.png"){
            return 2131165284;
        };
        return 0;
    };


}
