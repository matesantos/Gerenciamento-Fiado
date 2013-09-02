package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBD;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BuscarClienteExcluir extends Activity {
	EditText 	  buscarCliente = null;
	ImageButton   buscarButton = null;
	ListView 	  listarCliente = null;
	Cursor		  clienteLista = null;
	
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	AdapterCliente adapter = null;
			
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_cliente_excluir);
		
		buscarCliente = (EditText)findViewById(R.id.editTextBuscarClienteTelaBuscarExcluirCliente);
		
		listarCliente = (ListView)findViewById(R.id.listPesquisarClienteTelaBuscarExcluirCliente);
		
		buscarButton  = (ImageButton)findViewById(R.id.imageButtonPesquisarClientetelaBuscarExcluirCliente);
		
		//listar todos os clientes 
		listarTodosCLientes();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscar_cliente, menu);
		return true;
	}
	
	private void listarTodosCLientes(){
		
		ClienteBD clienteDao = ClienteBDFactory.getClienteBD(this); 
		
		clienteLista = clienteDao.buscarTodosOsClientes();
		
		if(clienteLista.isBeforeFirst()){
			clienteLista.moveToFirst();
		}
		
		startManagingCursor(clienteLista);
		
		for(int i = 0; i < clienteLista.getCount(); i++){
			
			Endereco endereco = new Endereco(clienteDao.getRua(clienteLista),clienteDao.getNumero(clienteLista),
									clienteDao.getCEP(clienteLista), clienteDao.getEstado(clienteLista),
									clienteDao.getCidade(clienteLista), clienteDao.getBairro(clienteLista));
			
			Cliente cli = new Cliente(clienteDao.getID(clienteLista),clienteDao.getNome(clienteLista), clienteDao.getTelefone(clienteLista),
						  clienteDao.getRG(clienteLista), clienteDao.getCPF(clienteLista), endereco);
			
			clientes.add(cli);
			
		}
		
		adapter = new AdapterCliente(this, clienteLista,clientes,clienteDao);
		
		listarCliente.setAdapter(adapter);
		listarCliente.setOnItemClickListener(onListClick);
	}
	
	private OnItemClickListener onListClick = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			
			mudarTela(position);
			
		}
	};
		
	private void mudarTela(int position){
		clienteLista.moveToPosition(position);
		Utils.goToActivityCliente(this, ExcluirClienteActivity.class,clientes.get(position));
	}

}
