package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendas.VendasDAOInterface;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoDAO;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteDAO;

public class VendedorBD extends SQLiteOpenHelper implements VendedorDAO {

	public VendedorBD(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + VendedorDAO.tabelaVendedor + "(_"
				+ VendedorDAO.id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ VendedorDAO.nome + " TEXT," + VendedorDAO.telefone + " TEXT,"
				+ VendedorDAO.rg + " TEXT," + VendedorDAO.cpf + " TEXT, "
				+ VendedorDAO.rua + " TEXT," + VendedorDAO.numero
				+ " INTERGER," + VendedorDAO.cep + " TEXT,"
				+ VendedorDAO.bairro + " TEXT," + VendedorDAO.cidade + " TEXT,"
				+ VendedorDAO.estado + " TEXT," + VendedorDAO.login + " TEXT,"
				+ VendedorDAO.senha + " TEXT );");
		
		//quando a aplicação começar a ser executada, ele só cria uma única vez todas as tabelas do sistema, como o DAO do vendedor
		//é a primeira a ser chamada pela aplicação, crio aqui todas as tabelas do sistema.
		createCliente(db);
		createProduto(db);
		createVendas(db);
	}
	
	private void createCliente(SQLiteDatabase db){
		db.execSQL("CREATE TABLE " + ClienteDAO.tabelaCliente +"(_"+ClienteDAO.id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				ClienteDAO.nome +" TEXT," + ClienteDAO.telefone +" TEXT,"+ClienteDAO.rg+" TEXT," +
				ClienteDAO.cpf+	" TEXT, "+ClienteDAO.rua+" TEXT," + ClienteDAO.numero +" INTERGER,"+ ClienteDAO.cep +" TEXT,"+
				ClienteDAO.bairro + " TEXT,"+ ClienteDAO.cidade +" TEXT,"+ ClienteDAO.estado +" TEXT );");
		
	}
	
	private void createProduto(SQLiteDatabase db){
		db.execSQL("CREATE TABLE " + ProdutoDAO.tabelaProduto +"(_"+ProdutoDAO.codigo+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				ProdutoDAO.nome +" TEXT," + ProdutoDAO.preco +" TEXT);");
		
	}
	
	private void createVendas(SQLiteDatabase db){
		db.execSQL("CREATE TABLE " + VendasDAOInterface.tabelaVendas+"(_"+VendasDAOInterface.id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				VendasDAOInterface.cliente+" TEXT," + VendasDAOInterface.produto +" TEXT " +VendasDAOInterface.valor +" TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public boolean salvar(Vendedor vendedor) {

		ContentValues valores = new ContentValues();

		valores.put("nome", vendedor.getNome());
		valores.put("telefone", vendedor.getTelefone());
		valores.put("rg", vendedor.getRg());
		valores.put("cpf", vendedor.getCpf());
		valores.put("rua", vendedor.getEndereco().getRua());
		valores.put("numero", vendedor.getEndereco().getNumero());
		valores.put("cep", vendedor.getEndereco().getCep());
		valores.put("bairro", vendedor.getEndereco().getBairro());
		valores.put("cidade", vendedor.getEndereco().getCidade());
		valores.put("estado", vendedor.getEndereco().getEstado());
		valores.put("login", vendedor.getLogin());
		valores.put("senha", vendedor.getSenha());

		try {

			getWritableDatabase().insert(VendedorDAO.tabelaVendedor, null,valores);
			return true;

		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Vendedor", sql.getMessage());
			Log.e("Cadastrar Vendedor",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Vendedor", e.getMessage());
			Log.e("Cadastrar Vendedor",
					Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
	}
	
	@Override
	public boolean atualizar(Vendedor vendedor) {
		
		Cursor cursor = buscarVendedorPorLoginSenha(vendedor.getLogin(), vendedor.getSenha());
		
		if(cursor.isBeforeFirst()){
			cursor.moveToFirst();
		}
		
		if(cursor.getCount() != 0){
			Log.i("ifLogin", "login");
			if(getID(cursor) != vendedor.getId()){
				return false;
			}
		}

		ContentValues valores = new ContentValues();

		valores.put("nome", vendedor.getNome());
		valores.put("telefone", vendedor.getTelefone());
		valores.put("rg", vendedor.getRg());
		valores.put("cpf", vendedor.getCpf());
		valores.put("rua", vendedor.getEndereco().getRua());
		valores.put("numero", vendedor.getEndereco().getNumero());
		valores.put("cep", vendedor.getEndereco().getCep());
		valores.put("bairro", vendedor.getEndereco().getBairro());
		valores.put("cidade", vendedor.getEndereco().getCidade());
		valores.put("estado", vendedor.getEndereco().getEstado());
		valores.put("login", vendedor.getLogin());
		valores.put("senha", vendedor.getSenha());
		
		String [] whereArgs = new String[]{Long.toString(vendedor.getId())};

		try {

			getWritableDatabase().update(VendedorDAO.tabelaVendedor, valores, "_id=?", whereArgs);
			return true;

		} catch (android.database.SQLException sql) {
			Log.e("Atualizar Vendedor", sql.getMessage());
			Log.e("Atualizar Vendedor",	Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Atualizar Vendedor", e.getMessage());
			Log.e("Atualizar Vendedor", Log.getStackTraceString(e.fillInStackTrace()));
		}

		return false;
	}

	@Override
	public boolean deletar(Vendedor vendedor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vendedor getVendedor(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendedor> listarVendedor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vendedor buscarVendedorPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor buscarVendedorPorLoginSenha(String login, String senha) {

		String query = "SELECT * FROM " + VendedorDAO.tabelaVendedor
				+ " WHERE " + VendedorDAO.login + " = '" + login + "'"
				+ " and " + VendedorDAO.senha + " = '" + senha + "';";

		try {
			return getReadableDatabase().rawQuery(query, null);
		} catch (android.database.SQLException sql) {
			Log.e("Cadastrar Vendedor", sql.getMessage());
			Log.e("Cadastrar Vendedor",
					Log.getStackTraceString(sql.fillInStackTrace()));
		} catch (Exception e) {
			Log.e("Cadastrar Vendedor", e.getMessage());
			Log.e("Cadastrar Vendedor",	Log.getStackTraceString(e.fillInStackTrace()));
		}

		return null;

	}

	@Override
	public Cursor buscarTodosVendedores() {
		String query = "SELECT * FROM " + VendedorDAO.tabelaVendedor + ";";
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

	@Override
	public String getLogin(Cursor c) {
		return c.getString(11);
	}

	@Override
	public String getSenha(Cursor c) {
		return c.getString(12);
	}


}
