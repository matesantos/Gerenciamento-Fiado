package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Produto;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoDAO;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterProduto extends BaseAdapter{
	List<Produto> produtos = null;
	
	ProdutoDAO pro = null;
	
	Context context = null;
	
	public AdapterProduto(Context context, Cursor c, List<Produto> produtos) {
		this.produtos = produtos;
		this.context = context;
		
	}
	

	@Override
	public int getCount() {
		return produtos.size();
	}


	@Override
	public Object getItem(int posicao) {
		return produtos.get(posicao);
	}


	@Override
	public long getItemId(int posicao) {
		return posicao;
	}


	@Override
	public View getView(int posicao, View view, ViewGroup group) {
		
		Produto produto = produtos.get(posicao);
		
		 // LayoutInflater permite instanciar uma View a partir de um arquivo de layout XML
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// Cria a view a partir do arquivo linha_dado_cliente.xml
		View linha = inflater.inflate(br.ufpb.lp3.gerenciamento_fiado.R.layout.linha_dados_produto, null);
		
		// Atualiza o valor dos campos da tela
	    TextView nome = (TextView) linha.findViewById(br.ufpb.lp3.gerenciamento_fiado.R.id.textViewNomeLinhaDadosProduto);
	    nome.setText(produto.getNome());

	    TextView preco = (TextView) linha.findViewById(br.ufpb.lp3.gerenciamento_fiado.R.id.textViewPrecoLinhaDadosProduto);
	    preco.setText(String.valueOf(produto.getPreco()));
		
		return linha;
	}
}
