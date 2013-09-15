package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import java.util.ArrayList;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.models.Produto;

public class ProdutoCP {
	
	public long id = 0;
	private String descricao;
	private float preco = 0;

	public ProdutoCP() {
	}
	
	public ProdutoCP(ContentValues values){
		id = values.getAsLong(ProdutosColunas._ID);
		descricao = values.getAsString(ProdutosColunas.DESCRICAO);
		preco = values.getAsFloat(ProdutosColunas.PRECO);
	}
	
	public ProdutoCP( String descricao, float preco){
		super();
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public ProdutoCP(long id, String descricao, float preco){
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	ContentValues toValues() {
		ContentValues values = new ContentValues();
		values.put(ProdutosColunas._ID, id);
		values.put(ProdutosColunas.DESCRICAO, descricao);
		values.put(ProdutosColunas.PRECO, preco);
		return values;
	}
	
	// Retorna um produto a partir de um cursor
	// O cursor já deve estar na posicao do filme desejado
	public static ProdutoCP fromCursor(Cursor c) {

			long id = 0;
			String descricao = null;
			float preco = 0;
			
			int index = 0;
			
			index = c.getColumnIndex(ProdutosColunas._ID);
			if (index >= 0) id = c.getLong(index);

			index = c.getColumnIndex(ProdutosColunas.DESCRICAO);
			if (index >= 0) descricao = c.getString(index);

			index = c.getColumnIndex(ProdutosColunas.PRECO);
			if (index >= 0) preco = c.getFloat(index);

			return new ProdutoCP(id, descricao, preco);
		}
	
	public static final class ProdutosColunas implements BaseColumns{
		public static final String DESCRICAO = "descricao";
		public static final String PRECO = "preco";
		
		public static final String[] colunas ={
			ProdutosColunas.DESCRICAO,ProdutosColunas.PRECO,
		};
		
		// Daqui para baixo é usado para provedor de conteúdo (ContentProvider)
		public static final String AUTHORITY = "br.ufpb.lp3.gerencimento_vendas.produtos";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/produtos");
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
		public static Uri getUriId(long id) {
			Uri uri = ContentUris.withAppendedId(CONTENT_URI, id);
			return uri;
		}
		
	}

}
