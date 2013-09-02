package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteDAO;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterCliente extends BaseAdapter{
	
	List<Cliente> clientes = null;
	
	ClienteDAO cli = null;
	
	Context context = null;
	
	public AdapterCliente(Context context, Cursor c, List<Cliente>clientes, ClienteDAO cli) {
		this.clientes = clientes;
		this.cli = cli;
		this.context = context;
		
	}
	

	@Override
	public int getCount() {
		return clientes.size();
	}


	@Override
	public Object getItem(int posicao) {
		return clientes.get(posicao);
	}


	@Override
	public long getItemId(int posicao) {
		return posicao;
	}


	@Override
	public View getView(int posicao, View view, ViewGroup group) {
		
		Cliente cliente = clientes.get(posicao);
		
		 // LayoutInflater permite instanciar uma View a partir de um arquivo de layout XML
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// Cria a view a partir do arquivo linha_dado_cliente.xml
		View linha = inflater.inflate(br.ufpb.lp3.gerenciamento_fiado.R.layout.linha_dados_cliente, null);
		
		// Atualiza o valor dos campos da tela
	    TextView nome = (TextView) linha.findViewById(br.ufpb.lp3.gerenciamento_fiado.R.id.textViewNomeLinhaDadosCliente);
	    nome.setText(cliente.getNome());

	    TextView telefone = (TextView) linha.findViewById(br.ufpb.lp3.gerenciamento_fiado.R.id.textViewTelefoneLinhaDadosCliente);
	    telefone.setText(cliente.getTelefone());
		
		return linha;
	}
	
}
