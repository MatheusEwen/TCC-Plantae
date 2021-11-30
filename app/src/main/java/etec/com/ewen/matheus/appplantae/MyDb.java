package etec.com.ewen.matheus.appplantae;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.RegarPlanta;

public class MyDb extends SQLiteOpenHelper {

    private Context context;
    private  static final  String NOME_BANCO = "DBRegar.db";
    private static final int VERSAO = 2;
    private static final String NOME_TABELA = "PlantaRega";
    private static final  String ID = "id";
    private static final String NOME_PLANTA = "nome";
    private static final String SEG = "segunda";
    private static final String TER = "terca";
    private static final String QUA = "quarta";
    private static final String QUI = "quinta";
    private static final String SEX = "sexta";
    private static final String SAB = "sabado";
    private static final String DOM = "domingo";

    public MyDb(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + NOME_TABELA + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               " " + NOME_PLANTA + " TEXT, " +
                " " + SEG + " TEXT, " +
                " " + TER + " TEXT, " +
                " " + QUA + " TEXT, " +
                " " + QUI + " TEXT, " +
                " " + SEX + " TEXT, " +
                " " + SAB + " TEXT, " +
                " " + DOM + " TEXT ); " ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + NOME_TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public long addPlanta(RegarPlanta a){
        ContentValues valores = new ContentValues();
        long retornoDB;
        valores.put(NOME_PLANTA, a.getNome());
        valores.put(SEG, a.getSeg());
        valores.put(TER, a.getTer());
        valores.put(QUA, a.getQua());
        valores.put(QUI, a.getQui());
        valores.put(SEX, a.getSex());
        valores.put(SAB, a.getSab());
        valores.put(DOM, a.getDom());

        retornoDB = getWritableDatabase().insert(NOME_TABELA, null, valores);

        if (retornoDB == -1){
            Toast.makeText(context, "falhou", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
        }
        return retornoDB;
    }
   /* Cursor allRegarPlanta(){
        String sql = "SELECT * FROM " + NOME_TABELA;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    */
    public ArrayList<RegarPlanta> selectAllPlanta(){
        String[] colunas = { ID, NOME_PLANTA, SEG, TER, QUA, QUI, SEX, SAB, DOM };

        Cursor cursor = getWritableDatabase().query(NOME_TABELA, colunas, null, null, null, null, null, null);
        ArrayList<RegarPlanta> listAluno = new ArrayList<RegarPlanta>();

        while( cursor.moveToNext()){
            RegarPlanta a = new RegarPlanta();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setSeg(cursor.getString(2));
            a.setTer(cursor.getString(3));
            a.setQua(cursor.getString(4));
            a.setQui(cursor.getString(5));
            a.setSex(cursor.getString(6));
            a.setSab(cursor.getString(7));
            a.setDom(cursor.getString(8));

            listAluno.add(a);
        }
        return listAluno;
    }

    public long excluirRegarPlanta(RegarPlanta a){
        long retornoDB;
        String[] args = {String.valueOf(a.getId())};
        retornoDB = getWritableDatabase().delete(NOME_TABELA, "id=?", args);
        return retornoDB;
    }
}
