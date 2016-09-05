package br.com.sysfar.imobileweb.util;

public class GerenciadorCaminhoArquivoUtil {

	private static String PASTA_UPLOAD_ARQUIVO;
	private static String PASTA_UPLOAD_RELATORIO;
	
	private GerenciadorCaminhoArquivoUtil() {
	}
	
	public static String getPastaUploadArquivo(){
		return PASTA_UPLOAD_ARQUIVO;
	}
	
	public static void setPastaCaminhoArquivo(String pastaUploadArquivo){
		PASTA_UPLOAD_ARQUIVO = pastaUploadArquivo;
	}
	
	public static String getPastaUploadRelatorio(){
		return PASTA_UPLOAD_RELATORIO;
	}
	
	public static void setPastaCaminhoRelatorio(String pastaUploadRelatorio){
		PASTA_UPLOAD_RELATORIO = pastaUploadRelatorio;
	}
	
}
