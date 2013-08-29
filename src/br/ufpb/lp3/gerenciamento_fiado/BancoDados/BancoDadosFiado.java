package br.ufpb.lp3.gerenciamento_fiado.BancoDados;

import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorDAO;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteDAO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDadosFiado extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME = "GerenciamentoFiado";
	public static final int DATABASE_VERSION = 1;

	public BancoDadosFiado(Context context, String name, CursorFactory factory,	int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTableCliente(db);
		createTableVendedor(db);
		
	}
	
	private void createTableCliente(SQLiteDatabase db){
		
		db.execSQL("CREATE TABLE" + ClienteDAO.tabelaCliente +"(_"+ClienteDAO.id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				ClienteDAO.nome +" TEXT," + ClienteDAO.telefone +" TEXT,"+ClienteDAO.rg+" TEXT," +
				ClienteDAO.cpf+	" TEXT, "+ClienteDAO.rua+" TEXT," + ClienteDAO.numero +" INTERGER,"+ ClienteDAO.cep +" TEXT,"+
				ClienteDAO.bairro + " TEXT,"+ ClienteDAO.cidade +"TEXT,"+ ClienteDAO.estado +" TEXT );");
		
	}
	
	private void createTableVendedor(SQLiteDatabase db){
		
		db.execSQL("CREATE TABLE" + VendedorDAO.tabelaVendedor +"(_"+VendedorDAO.id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				VendedorDAO.nome +" TEXT," + VendedorDAO.telefone +" TEXT,"+VendedorDAO.rg+" TEXT," +
				VendedorDAO.cpf +" TEXT, "+VendedorDAO.rua+" TEXT," + VendedorDAO.numero +" INTERGER,"+ VendedorDAO.cep +" TEXT,"+
				VendedorDAO.bairro + " TEXT,"+ VendedorDAO.cidade +"TEXT,"+ VendedorDAO.estado +" TEXT,"
				+ VendedorDAO.login + "TEXT," + VendedorDAO.senha +"TEXT );");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
