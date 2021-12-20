package co.edu.usa.reto4.vista.ui.productos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import co.edu.usa.reto4.R;
import co.edu.usa.reto4.modelo.Adaptador;
import co.edu.usa.reto4.modelo.Entidad;
import co.edu.usa.reto4.modelo.MotorBaseDatosSQLite;

public class ProductosFragment extends Fragment {
    int [] imagen = {R.drawable.chaqueta1, R.drawable.chaqueta2, R.drawable.chaqueta3 };


    String tag ="ProductosFragment";
    View v;

    // CONEXION A LA BASE DE DATOS: SQLite
    MotorBaseDatosSQLite conectar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_gallery,container,false);

        /*Resources res1 = getResources();
        drawable1 = res1.getDrawable(R.drawable.chaqueta1, v.getContext().getTheme());

        chaqueta1 = (ImageView) v.findViewById(R.id.imagenItem);
        chaqueta1.setImageDrawable(drawable1);

        Resources res2 = getResources();
        drawable2 = res2.getDrawable(R.drawable.chaqueta2, v.getContext().getTheme());

        chaqueta2 = (ImageView) v.findViewById(R.id.chaqueta2);
        chaqueta2.setImageDrawable(drawable2);

        Resources res3 = getResources();
        drawable3 = res3.getDrawable(R.drawable.chaqueta3, v.getContext().getTheme());

        chaqueta3 = (ImageView) v.findViewById(R.id.chaqueta3);
        chaqueta3.setImageDrawable(drawable3);*/

        ListView listView = (ListView) v.findViewById(R.id.lista_productos);
        Adaptador adaptador = new Adaptador(getTablaProductos(), v.getContext());

        listView.setAdapter(adaptador);

        Log.v("Productos id de imagen", "Id: " + imagen[2]);

        return v;
    }

    private ArrayList<Entidad> getTablaProductos(){
        ArrayList<Entidad> listaProductos = new ArrayList<>();
        conectar = new MotorBaseDatosSQLite(getContext(),"TiendaProductos", null, 1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        conectar.onUpgrade(db_leer,1,2);
        Cursor cursor = db_leer.rawQuery("SELECT * FROM productos", null);



        while(cursor.moveToNext()){
            listaProductos.add(new Entidad(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }


        return listaProductos;
    }

    /*private ArrayList<Entidad> getListitems(){
        ArrayList<Entidad> listaItems = new ArrayList<>();
        listaItems.add(new Entidad(R.drawable.chaqueta1, "Chaquetas en Denin", "Esta es una descrpción de la chaqueta 1"));
        listaItems.add(new Entidad(R.drawable.chaqueta2, "Chaquetas impermeables", "Esta es una descrpción de la chaqueta 2"));
        listaItems.add(new Entidad(R.drawable.chaqueta3, "Chaquetas de cuero", "Esta es una descrpción de la chaqueta 2"));
        return listaItems;
    }*/


}