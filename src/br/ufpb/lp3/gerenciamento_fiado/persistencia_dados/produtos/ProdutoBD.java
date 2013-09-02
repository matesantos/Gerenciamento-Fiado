package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.ufpb.lp3.gerenciamento_fiado.models.Produto;

public class ProdutoBD extends SQLiteOpenHelper implements ProdutoDAO{

	public ProdutoBD(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}	

	@Override
	public boolean salvar(Produto produto) {

		ContentValues valores = new ContentValues();

		valores.put("nome", produto.getNome());
		valores.put("preco", produto.getPreco());

		try {

			getWritableDatabase().insert(ProdutoDAO.tabelaProduto, null,valores);
			return true;

		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Produto", sql.getMessage());
			Log.e("Cadastrar Produto",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Produto", e.getMessage());
			Log.e("Cadastrar Produto",
					Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
	}

	@Override
	public boolean atualizar(Produto produto) {
		Cursor cursor = (Cursor) getProduto(produto.getCodigo());
		
		if(cursor.isBeforeFirst()){
			cursor.moveToFirst();
		}
		
		if(cursor.getCount() != 0){
			if(getID(cursor) != produto.getCodigo()){
				return false;
			}
		}

		ContentValues valores = new ContentValues();

		valores.put("nome", produto.getNome());
		valores.put("preco", produto.getPreco());

		String [] whereArgs = new String[]{Long.toString(produto.getCodigo())};

		try {

			getWritableDatabase().update(ProdutoDAO.tabelaProduto, valores, "_id=?", whereArgs);
			return true;

		} catch (android.database.SQLException sql) {
			Log.e("Atualizar Produto", sql.getMessage());
			Log.e("Atualizar Produto",	Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Atualizar Produto", e.getMessage());
			Log.e("Atualizar Produto", Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
	}

	@Override
	public boolean deletar(Produto produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Produto getProduto(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> listarproduto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor buscarprodutoPorNome(String nome) {
		String query = "SELECT * FROM " + ProdutoDAO.tabelaProduto
				+ " WHERE " + ProdutoDAO.nome + " = '" + nome + "'; " ;

		try {
			return getReadableDatabase().rawQuery(query, null);
		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Produto", sql.getMessage());
			Log.e("Cadastrar Produto",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Produto", e.getMessage());
			Log.e("Cadastrar Produto",	Log.getStackTraceString(e.fillInStackTrace()));
		}

		return null;	}

	@Override
	public Cursor buscarTodosProdutos() {
		String query = "SELECT * FROM " + ProdutoDAO.tabelaProduto +";";

		try {
			return getReadableDatabase().rawQuery(query, null);
		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Produto", sql.getMessage());
			Log.e("Cadastrar Produto",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Produto", e.getMessage());
			Log.e("Cadastrar Produto",	Log.getStackTraceString(e.fillInStackTrace()));
		}

		return null;	
	}

	@Override
	public Long getID(Cursor c) {
		return c.getLong(0);
	}

	@Override
	public String getNome(Cursor c) {
		return c.getString(1);
	}

	@Override
	public String getPreco(Cursor c) {
		return c.getString(2);
	}


}
