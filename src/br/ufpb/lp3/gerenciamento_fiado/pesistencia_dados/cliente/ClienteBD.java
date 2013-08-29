package br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
		return false;
	}

	@Override
	public boolean deletar(Cliente cliente) {
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
	public Cliente buscarClientePorNome(String nome) {
		return null;
	}

}
