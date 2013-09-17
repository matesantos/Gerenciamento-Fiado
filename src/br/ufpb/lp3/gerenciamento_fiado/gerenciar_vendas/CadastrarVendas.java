package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendas;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.models.Produto;
import br.ufpb.lp3.gerenciamento_fiado.models.Venda;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoBD;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBD;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;

public class CadastrarVendas extends Activity {
	
	private Button cancelar = null;
	private Button finalizarVenda = null;
	private ImageButton pesquisar = null;
	private Button calcular = null;
	
	private EditText codigoDescricaoProduto = null;
	private EditText quantidade = null;
	
	private TextView descricao = null;
	private TextView cpf = null;
	private TextView preco = null;
	private TextView valorTotal = null;
	static Float valor = (float) 0;
	static Float valorParcial = (float) 0;
	
	private Produto prod =  null;
	private Vendedor vendedor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_vendas);
		
		Bundle dados = getIntent().getExtras();
	    vendedor = (Vendedor)dados.getSerializable("vendedor");		
		
	    cancelar		 = (Button)findViewById(R.id.buttonCancelarTelaCadastrarVenda);
		finalizarVenda   = (Button)findViewById(R.id.buttonFinalizarVendaTelaCadastrarVenda);
		pesquisar 		 = (ImageButton)findViewById(R.id.imageButtonPesquisarProdutoTelaBuscarVenda);
		calcular 		 = (Button)findViewById(R.id.buttonCalcularValorTotalTelaCadastrarVenda);
		
		codigoDescricaoProduto = (EditText)findViewById(R.id.editTextCodigoDescricaoProdutoTelaCadastrarVenda);
		quantidade = (EditText)findViewById(R.id.editTextQuantidadeTelaCadastrarVenda);
		quantidade.setEnabled(false);
		
		descricao 		= (TextView)findViewById(R.id.textViewDescricaoProdutoTelaCadastrarVenda);
		cpf 			= (TextView)findViewById(R.id.editTextCpfClienteTelaCadastrarVenda);
		preco 			= (TextView)findViewById(R.id.precoProdutoTelaCadastrarVenda2);
		valorTotal 		= (TextView)findViewById(R.id.textViewValorTotalTelaCadastrarVenda2);
		
		cancelar.setOnClickListener(new CancelarButton());
		finalizarVenda.setOnClickListener(new finalizarVenda());
		pesquisar.setOnClickListener(new PesquisarButton());
		calcular.setOnClickListener(new CalcularValor());
		
	}
	
	private class CalcularValor implements OnClickListener{

		@Override
		public void onClick(View v) {
			valorParcial = (float) (Integer.valueOf((quantidade.getText().toString())) * Float.valueOf(preco.getText().toString())); 
			valorTotal.setText(String.valueOf(valorParcial));
		}
		
	}
	
	private class PesquisarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			pesquisarProduto();
		}
		
	}
	
	private void pesquisarProduto(){
		Cursor produtoCurso = null;
		ProdutoBD produtoDao = ProdutoBDFactory.getProdutoBD(this);
		long codigo;
		String nome;
		try{
		
			codigo = Long.valueOf(codigoDescricaoProduto.getText().toString());
			
			produtoCurso = produtoDao.getProduto(codigo);
			
			if(produtoCurso.isBeforeFirst()){
				produtoCurso.moveToFirst();
			}
			
			if(produtoCurso.getCount() == 0){
				return;
			}
			
			startManagingCursor(produtoCurso);

			prod = new Produto(produtoDao.getID(produtoCurso), produtoDao.getNome(produtoCurso), Float.parseFloat(produtoDao.getPreco(produtoCurso)));
			
			descricao.setText(prod.getNome());
			preco.setText(String.valueOf(prod.getPreco()));
			quantidade.setEnabled(true);
			
			
		}catch (NumberFormatException num) {
			
			nome = codigoDescricaoProduto.getText().toString();
			
			produtoCurso = produtoDao.buscarprodutoPorNome(nome);
			
			if(produtoCurso.isBeforeFirst()){
				produtoCurso.moveToFirst();
			}
			
			if(produtoCurso.getCount() == 0){
				return;
			}
			
			prod = new Produto(produtoDao.getID(produtoCurso), produtoDao.getNome(produtoCurso), Float.parseFloat(produtoDao.getPreco(produtoCurso)));
			
			descricao.setText(prod.getNome());
			preco.setText(String.valueOf(prod.getPreco()));
			quantidade.setEnabled(true);
		
		}
		
	}
	
	private class CancelarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			retornarTela();
		}
	}
	
	
	private void retornarTela(){
		codigoDescricaoProduto.setText(" ");
		quantidade.setText(" ");
		descricao.setText("");
		cpf.setText("");
		
		Utils.goToActivityVendedor(this, GerenciarVendaActivity.class,vendedor);
	}
	
	private class finalizarVenda implements OnClickListener{
		@Override
		public void onClick(View v) {
			salvarVenda();
		}
	}
	
	private void salvarVenda(){
		
		Cursor cli= null;
		VendasBD vendaBD = VendasBDFactory.getVendasBD(this);
		ClienteBD clienteBD = ClienteBDFactory.getClienteBD(this);
		cli = clienteBD.buscarClientePorCPF(cpf.getText().toString());
		
		if(cli.getCount() == 0){
			Utils.mostrarMensagens(this, "O cliente não existe, certifique-se se o cpf está correto");
			return;
		}
		
		if(cli.isBeforeFirst()){
			cli.moveToFirst();
		}
		
		Cliente cliente = new Cliente();
		
		cliente.setNome(clienteBD.getNome(cli));
		cliente.setCpf(cpf.getText().toString());
		
		Venda venda = new Venda(cliente,prod,Float.valueOf(valorTotal.getText().toString()));
		
		
		if(vendaBD.salvar(venda)){
			Utils.mostrarMensagens(this, "Venda realizada com Sucesso.");
			retornarTela();
		}else{
			Utils.mostrarMensagens(this, "ERROR, não foi possível realizar a venda.");
			return;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_vendas, menu);
		return true;
	}

}
