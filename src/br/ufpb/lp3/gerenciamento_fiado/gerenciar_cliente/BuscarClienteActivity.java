package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBD;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;

public class BuscarClienteActivity extends Activity {

	EditText 	  buscarCliente = null;
	ImageButton   buscarButton = null;
	ListView 	  listarCliente = null;
	Cursor		  clienteLista = null;
	PopupWindow   popupOpcoes =  null;
	int positionItemSelected = -1;
	
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	AdapterCliente adapter = null;
			
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_cliente);
		
		buscarCliente = (EditText)findViewById(R.id.editTextBuscarClienteTelaBuscarCliente);
		
		listarCliente = (ListView)findViewById(R.id.listPesquisarClienteTelaBuscarCliente);
		
		buscarButton  = (ImageButton)findViewById(R.id.imageButtonPesquisarClientetelaBuscarCliente);
		
		//listar todos os clientes 
		listarTodosCLientes();
		
		buscarButton.setOnClickListener(new BuscarButton());
		
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
		
//		for(int i = 0; i < clienteLista.getCount(); i++){
//			
//			Endereco endereco = new Endereco(clienteDao.getRua(clienteLista),clienteDao.getNumero(clienteLista),
//									clienteDao.getCEP(clienteLista), clienteDao.getEstado(clienteLista),
//									clienteDao.getCidade(clienteLista), clienteDao.getBairro(clienteLista));
//			
//			Cliente cli = new Cliente(clienteDao.getID(clienteLista),clienteDao.getNome(clienteLista), clienteDao.getTelefone(clienteLista),
//						  clienteDao.getRG(clienteLista), clienteDao.getCPF(clienteLista), endereco);
//			
//			Log.i("cli",cli.getCpf());
//			
//			clientes.add(cli);
//		}
		if(clienteLista.getCount() > 0){
			do{
				
				Endereco endereco = new Endereco(clienteDao.getRua(clienteLista),clienteDao.getNumero(clienteLista),
										clienteDao.getCEP(clienteLista), clienteDao.getEstado(clienteLista),
										clienteDao.getCidade(clienteLista), clienteDao.getBairro(clienteLista));
				
				Cliente cli = new Cliente(clienteDao.getID(clienteLista),clienteDao.getNome(clienteLista), clienteDao.getTelefone(clienteLista),
							  clienteDao.getRG(clienteLista), clienteDao.getCPF(clienteLista), endereco);
				
				clientes.add(cli);
			}while(clienteLista.moveToNext());
		}else{
			Utils.mostrarMensagens(this, "Não existe nenhum cliente cadastrado.");
		}
		
		adapter = new AdapterCliente(this, clienteLista,clientes);
		
		listarCliente.setAdapter(adapter);
		listarCliente.setOnItemClickListener(onListClick);
	}
	
	private class BuscarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			String cli =  buscarCliente.getText().toString();
			findCli(cli);
		}
		
	}
	
	private void findCli(String nome){
		ClienteBD clienteDao = ClienteBDFactory.getClienteBD(this); 
		
		clienteLista = (Cursor) clienteDao.buscarClientePorNome(nome);
		
		if(clienteLista.isBeforeFirst()){
			clienteLista.moveToFirst();
		}
		
		startManagingCursor(clienteLista);
		
		if(clienteLista.getCount() > 0){
			do{
				
				Endereco endereco = new Endereco(clienteDao.getRua(clienteLista),clienteDao.getNumero(clienteLista),
										clienteDao.getCEP(clienteLista), clienteDao.getEstado(clienteLista),
										clienteDao.getCidade(clienteLista), clienteDao.getBairro(clienteLista));
				
				Cliente cli = new Cliente(clienteDao.getID(clienteLista),clienteDao.getNome(clienteLista), clienteDao.getTelefone(clienteLista),
							  clienteDao.getRG(clienteLista), clienteDao.getCPF(clienteLista), endereco);
				
				Log.i("cli",cli.getCpf());
				
				clientes.add(cli);
			}while(clienteLista.moveToNext());
		}else{
			Utils.mostrarMensagens(this, "Cliente não encontrado.");
		}
	}
	
	private OnItemClickListener onListClick = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			positionItemSelected = position;
			registerForContextMenu(listarCliente);
		}
	};
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId()==R.id.listPesquisarClienteTelaBuscarCliente) {
			getMenuInflater().inflate(R.menu.contextual_layout_buscar_cliente, menu);
		}
	}
	
	@Override
	 
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		 
			case R.id.atualiazarCliBuscar:
				mudarTela(positionItemSelected);
			return true;
			 
			case R.id.SearchMap:
			return true;
			 
			default:
				return super.onContextItemSelected(item);
		}	
	}
	 
	private void mudarTela(int position){
		clienteLista.moveToPosition(position);
		Utils.goToActivityCliente(this, AtualizarClienteActivity.class,clientes.get(position));
	}
	
}
