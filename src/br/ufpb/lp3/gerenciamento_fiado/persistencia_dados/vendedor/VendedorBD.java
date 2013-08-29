package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;

public class VendedorBD extends SQLiteOpenHelper implements VendedorDAO {

	public VendedorBD(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE" + VendedorDAO.tabelaVendedor +"(_"+VendedorDAO.id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				VendedorDAO.nome +" TEXT," + VendedorDAO.telefone +" TEXT,"+VendedorDAO.rg+" TEXT," +
				VendedorDAO.cpf +" TEXT, "+VendedorDAO.rua+" TEXT," + VendedorDAO.numero +" INTERGER,"+ VendedorDAO.cep +" TEXT,"+
				VendedorDAO.bairro + " TEXT,"+ VendedorDAO.cidade +"TEXT,"+ VendedorDAO.estado +" TEXT,"
				+ VendedorDAO.login + "TEXT," + VendedorDAO.senha +"TEXT );");
		
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
		

		getWritableDatabase().insert(VendedorDAO.tabelaVendedor, "nome", valores);
		
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

}
