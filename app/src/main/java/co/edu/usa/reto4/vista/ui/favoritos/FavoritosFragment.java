package co.edu.usa.reto4.vista.ui.favoritos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import co.edu.usa.reto4.R;
import co.edu.usa.reto4.modelo.Adaptador;
import co.edu.usa.reto4.modelo.Entidad;
import co.edu.usa.reto4.modelo.MotorBaseDatosSQLite;

public class FavoritosFragment extends Fragment {

    View v;
    String tag = "FavoritosFragment";
    MotorBaseDatosSQLite conectar = new MotorBaseDatosSQLite(getContext(), "favoritos", null, 1);
    int [] imagen = {R.drawable.chaqueta1, R.drawable.chaqueta2, R.drawable.chaqueta3 };
    public FavoritosFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_favoritos,container,false);

        ListView listView = (ListView) v.findViewById(R.id.lista_favoritos);
        Adaptador adaptador = new Adaptador(getTablaFavoritos(), v.getContext());


        listView.setAdapter(adaptador);

        return v;
    }

    private ArrayList<Entidad> getTablaFavoritos(){
        ArrayList<Entidad> listaFavoritos = new ArrayList<>();
        conectar = new MotorBaseDatosSQLite(getContext(),"TiendaProductos", null, 1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        //conectar.onUpgrade(db_leer,1,2);
        Cursor cursor = db_leer.rawQuery("SELECT * FROM favoritos", null);

        while(cursor.moveToNext()){
            Log.v(tag, "dentro de while");
            listaFavoritos.add(new Entidad(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            Log.v(tag, "despues del while");
        }


        return listaFavoritos;
    }

    /*private ArrayList<Entidad> getListitems(){
        ArrayList<Entidad> listaItems = new ArrayList<>();
        listaItems.add(new Entidad(R.drawable.chaqueta1, "Chaquetas en Denin", "Esta es una descrpción de la chaqueta 1"));
        listaItems.add(new Entidad(R.drawable.chaqueta2, "Chaquetas impermeables", "Esta es una descrpción de la chaqueta 2"));
        listaItems.add(new Entidad(R.drawable.chaqueta3, "Chaquetas de cuero", "Esta es una descrpción de la chaqueta 2"));
        return listaItems;
    }*/
}
