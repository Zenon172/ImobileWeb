package br.com.sysfar.imobileweb.util;

public class Constantes {

	public static final String PASTA_RELATORIO = "relatorios";
	public static final String OPERACAO_SUCESSO = "OPERACAO_SUCESSO";
	
	public static final String SESSION_USUARIO_LOGADO = "usuarioLogado";
	public static final String SESSION_CAPTACAO_ATUAL = "captacaoAtual";
	public static final String SESSION_IMOVEL_ATUAL_ID = "imovelAtualId";
	public static final String SESSION_CLIENTE_ATUAL_ID = "clienteAtualId";
	public static final String SESSION_FLAG_ACESSO_JAR = "flagAcessoJar";
	
	public static final String MENU_FACES = "menuFaces";
	public static final String IMOVEL_FACES = "imovelFaces";
	public static final String CLIENTE_FACES = "clienteFaces";
	
	public static final Long TIPO_IMOVEL_APARTAMENTO = 1L;
	public static final Long TIPO_IMOVEL_CASA = 2L;
	public static final Long TIPO_IMOVEL_TERRENO = 3L;
	
	public static final Long STATUS_CAPTACAO_NOVA = 1L;
	public static final Long STATUS_CAPTACAO_EM_ANDAMENTO = 2L;
	public static final Long STATUS_CAPTACAO_PENDENTE = 3L;
	public static final Long STATUS_CAPTACAO_CONCLUIDA = 4L;
	public static final Long STATUS_CAPTACAO_CANCELADA = 5L;
	
	public static final Long STATUS_ATIVIDADE_NOVA = 1L;
	public static final Long STATUS_ATIVIDADE_PENDENTE = 2L;
	public static final Long STATUS_ATIVIDADE_CONCLUIDA = 3L;
	public static final Long STATUS_ATIVIDADE_CANCELADA = 4L;
	
	public static final Long MENU_IMOVEL = 15L;
	public static final Long MENU_ATIVIDADE = 16L;
	public static final Long MENU_CLIENTE = 19L;
	
	public static final Long TIPO_RESPOSTA_TEXTO = 1L;
	public static final Long TIPO_RESPOSTA_INTEIRO = 2L;
	public static final Long TIPO_RESPOSTA_PONTO_FLUTUANTE = 3L;
	public static final Long TIPO_RESPOSTA_INPUT_TEXT = 4L;
	public static final Long TIPO_RESPOSTA_BOOLEAN = 5L;
	
	public static final Long CONFIGURACAO_MARGEM_VALOR_IMOVEL_PESQUISA = 1L;
	public static final Long CONFIGURACAO_QUANTIDADE_DIAS_SEM_CONTATO_CLIENTE = 2L;
	public static final Long CONFIGURACAO_PASTA_UPLOAD_ARQUIVO = 3L;
	public static final Long CONFIGURACAO_PASTA_UPLOAD_RELATORIO = 4L;
	
	public static final String PASTA_DOWNLOAD_ARQUIVO = "../../arquivos/arquivos_imobile/";
	public static final String PASTA_DOWNLOAD_ARQUIVO_SITE = "../arquivos/arquivos_imobile/";

	public static final String EXTENSAO_FOTOS = "JPG";
}
