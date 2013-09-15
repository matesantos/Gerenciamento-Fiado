package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendas;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Venda;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class VendasBD extends SQLiteOpenHelper implements VendasDAOInterface{

	public VendasBD(Context context, String name, CursorFactory factory,
			int version) {
			super(context, name, factory, version)	;
	}

	@Override
	public boolean salvar(Venda venda) {
		
		ContentValues valores = new ContentValues();

		valores.put("cliente", venda.getCliente().getNome());
//		valores.put("produto", venda.ge);

		try {

			getWritableDatabase().insert(VendasDAOInterface.tabelaVendas, null,valores);
			return true;

		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Venda", sql.getMessage());
			Log.e("Cadastrar Venda",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Venda", e.getMessage());
			Log.e("Cadastrar Venda",
					Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
		
	}

	@Override
	public boolean atualizar(Venda venda) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletar(Venda venda) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Venda getVenda(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venda> listarVenda() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor buscarTodosVendas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getID(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCliente(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProduto(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValor(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
