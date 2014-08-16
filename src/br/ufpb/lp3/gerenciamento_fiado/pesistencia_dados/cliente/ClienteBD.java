package br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ClienteBD extends SQLiteOpenHelper implements ClienteDAO{

	public ClienteBD(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE" + ClienteDAO.tabelaCliente +"(_"+ClienteDAO.id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				ClienteDAO.nome +" TEXT," + ClienteDAO.telefone +" TEXT,"+ClienteDAO.rg+" TEXT," +
				ClienteDAO.cpf+	" TEXT, "+ClienteDAO.rua+" TEXT," + ClienteDAO.numero +" INTERGER,"+ ClienteDAO.cep +" TEXT,"+
				ClienteDAO.bairro + " TEXT,"+ ClienteDAO.cidade +"TEXT,"+ ClienteDAO.estado +" TEXT );");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean salvar(Cliente cliente) {
		ContentValues valores = new ContentValues();

		valores.put("nome", cliente.getNome());
		valores.put("telefone", cliente.getTelefone());
		valores.put("rg", cliente.getRg());
		valores.put("cpf", cliente.getCpf());
		valores.put("rua", cliente.getEndereco().getRua());
		valores.put("numero", cliente.getEndereco().getNumero());
		valores.put("cep", cliente.getEndereco().getCep());
		valores.put("bairro", cliente.getEndereco().getBairro());
		valores.put("cidade", cliente.getEndereco().getCidade());
		valores.put("estado", cliente.getEndereco().getEstado());

		try {

			getWritableDatabase().insert(ClienteDAO.tabelaCliente, null,valores);
			return true;

		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Cliente", sql.getMessage());
			Log.e("Cadastrar Cliente",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Cliente", e.getMessage());
			Log.e("Cadastrar Cliente", Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
	}
	
	@Override
	public boolean atualizar(Cliente cliente) {
		
		/*
		Cursor cursor = buscarVendedorPorLoginSenha(vendedor.getLogin(), vendedor.getSenha());
		
		if(cursor.isBeforeFirst()){
			cursor.moveToFirst();
		}
		
		
		if(cursor.getCount() != 0){
			if(getID(cursor) != vendedor.getId()){
				return false;
			}
		}
		
		*/

		ContentValues valores = new ContentValues();

		valores.put("nome", cliente.getNome());
		valores.put("telefone", cliente.getTelefone());
		valores.put("rg", cliente.getRg());
		valores.put("cpf", cliente.getCpf());
		valores.put("rua", cliente.getEndereco().getRua());
		valores.put("numero", cliente.getEndereco().getNumero());
		valores.put("cep", cliente.getEndereco().getCep());
		valores.put("bairro", cliente.getEndereco().getBairro());
		valores.put("cidade", cliente.getEndereco().getCidade());
		valores.put("estado", cliente.getEndereco().getEstado());
		
		String [] whereArgs = new String[]{Long.toString(cliente.getId())};

		try {

			getWritableDatabase().update(ClienteDAO.tabelaCliente, valores, "_id=?", whereArgs);
			return true;

		} catch (android.database.SQLException sql) {
			Log.e("Atualizar Cliente", sql.getMessage());
			Log.e("Atualizar Cliente",	Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Atualizar Cliente", e.getMessage());
			Log.e("Atualizar Cliente", Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
	}

	@Override
	public boolean deletar(Cliente cliente) {
		try{
			
			String [] whereArgs = new String[]{Long.toString(cliente.getId())};

			getReadableDatabase().delete(ClienteDAO.tabelaCliente, "_id=?", whereArgs);
			return true;
		}catch (SQLException sql) {
			Log.e("Atualizar Cliente", sql.getMessage());
			Log.e("Atualizar Cliente",	Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Atualizar Cliente", e.getMessage());
			Log.e("Atualizar Cliente", Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
	}

	@Override
	public Cliente getCliente(Long id) {
		return null;
	}

	@Override
	public List<Cliente> listarCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor buscarClientePorNome(String nome) {
		String query = "SELECT * FROM " + ClienteDAO.tabelaCliente
				+ " WHERE " + ClienteDAO.nome+ " = '" + nome + "';";

		try {
			return getReadableDatabase().rawQuery(query, null);
		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Cliente", sql.getMessage());
			Log.e("Cadastrar Cliente",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Cliente", e.getMessage());
			Log.e("Cadastrar Cliente",	Log.getStackTraceString(e.fillInStackTrace()));
		}
		
		return null;
	}
	
	@Override
	public Cursor buscarClientePorCPF(String cpf) {
		
		String query = "SELECT * FROM " + ClienteDAO.tabelaCliente
				+ " WHERE " + ClienteDAO.cpf+ " = '" + cpf + "';";

		try {
			return getReadableDatabase().rawQuery(query, null);
		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Cliente", sql.getMessage());
			Log.e("Cadastrar Cliente",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Cliente", e.getMessage());
			Log.e("Cadastrar Cliente",	Log.getStackTraceString(e.fillInStackTrace()));
		}

		return null;
	}

	@Override
	public Cursor buscarTodosOsClientes() {
		String query = "SELECT * FROM " + ClienteDAO.tabelaCliente + ";";
		return getReadableDatabase().rawQuery(query, null);
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
	public String getTelefone(Cursor c) {
		return c.getString(2);
	}

	@Override
	public String getRG(Cursor c) {
		return c.getString(3);
	}

	@Override
	public String getCPF(Cursor c) {
		return c.getString(4);
	}

	@Override
	public String getRua(Cursor c) {
		return c.getString(5);
	}

	@Override
	public String getNumero(Cursor c) {
		return c.getString(6);
	}

	@Override
	public String getCEP(Cursor c) {
		return c.getString(7);
	}

	@Override
	public String getEstado(Cursor c) {
		return c.getString(8);
	}

	@Override
	public String getCidade(Cursor c) {
		return c.getString(9);
	}

	@Override
	public String getBairro(Cursor c) {
		return c.getString(10);
	}


}
