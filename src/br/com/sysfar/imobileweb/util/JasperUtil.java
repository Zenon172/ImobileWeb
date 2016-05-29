package br.com.sysfar.imobileweb.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import br.com.topsys.exception.TSSystemException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class JasperUtil {

	private final String CAMINHO_RELATORIO = "WEB-INF" + File.separator + "relatorios" + File.separator;

	public String gerarRelatorio(String jasper, Map<String, Object> parametros) {

		return this.gerarRelatorioCaminhoCompleto(TSFacesUtil.getServletContext().getRealPath(CAMINHO_RELATORIO + jasper), parametros);

	}

	public void gerarRelatorioExcel(String jasper, Map<String, Object> parametros, String nomeArquivo) {

		this.gerarRelatorioCaminhoCompletoExcel(TSFacesUtil.getServletContext().getRealPath(CAMINHO_RELATORIO + jasper), parametros, nomeArquivo);

	}

	public String gerarRelatorioCaminhoCompleto(String caminhoCompleto, Map<String, Object> parametros) {

		Connection con = null;
		FileFilter filter = null;
		File[] dirFiles = null;
		String caminhoCriacao = null;

		try {

			con = ConnectionFactory.getConnection();

			JasperPrint impressao = JasperFillManager.fillReport(caminhoCompleto, parametros, con);

			if (!TSUtil.isEmpty(impressao)) {

				String destino = Utilitario.getUsuarioLogado().getId() + "_" + TSUtil.gerarId() + ".pdf";

				RequestContext.getCurrentInstance().addCallbackParam("arquivoImpressao", destino);

				caminhoCriacao = TSFacesUtil.getServletContext().getRealPath(File.separator + destino);

				filter = new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.getName().startsWith(Utilitario.getUsuarioLogado().getId() + "_") && pathname.getName().endsWith(".pdf");
					}
				};

				dirFiles = new File(TSFacesUtil.getServletContext().getRealPath(File.separator)).listFiles(filter);
				for (int i = 0; i < dirFiles.length; i++) {
					new File(TSFacesUtil.getServletContext().getRealPath(File.separator) + dirFiles[i].getName()).delete();
				}
				

				JasperExportManager.exportReportToPdfFile(impressao, caminhoCriacao);

			}

		} catch (Exception e) {

			throw new TSSystemException(e);

		} finally {
			
			filter = null;
			dirFiles = null;

			ConnectionFactory.closeConnection(con);
			
		}

		return caminhoCriacao;

	}

	public void gerarRelatorio(byte[] hash) {

		Connection con = null;

		FileOutputStream fileOutputStream = null;

		try {

			String destino = Utilitario.getUsuarioLogado().getId() + ".pdf";

			String caminhoCriacao = TSFacesUtil.getServletContext().getRealPath(File.separator + destino);

			fileOutputStream = new FileOutputStream(caminhoCriacao);

			fileOutputStream.write(hash);

		} catch (Exception e) {

			throw new TSSystemException(e);

		} finally {

			ConnectionFactory.closeConnection(con);
			try {
				fileOutputStream.close();
			} catch (IOException e) {
			}

		}

	}

	public byte[] gerarRelatorioByte(String jasper, Map<String, Object> parametros) {

		Connection con = null;
		byte[] retorno = null;
		try {

			con = ConnectionFactory.getConnection();

			jasper = TSFacesUtil.getServletContext().getRealPath(CAMINHO_RELATORIO + jasper);

			JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, con);

			if (!TSUtil.isEmpty(impressao)) {

				retorno = JasperExportManager.exportReportToPdf(impressao);
			}

		} catch (Exception e) {

			throw new TSSystemException(e);

		} finally {

			ConnectionFactory.closeConnection(con);

		}

		return retorno;
	}

	public void gerarRelatorioTela(String jasper, Map<String, Object> parametros) {

		Connection con = null;

		try {

			con = ConnectionFactory.getConnection();

			jasper = TSFacesUtil.getServletContext().getRealPath(CAMINHO_RELATORIO + jasper);

			JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, con);

			if (!TSUtil.isEmpty(impressao)) {

				ExternalContext econtext = TSFacesUtil.getFacesContext().getExternalContext();

				HttpServletResponse response = (HttpServletResponse) econtext.getResponse();

				response.setContentType("APPLICATION/PDF");

				response.setHeader("Content-Disposition", "inline");

				JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

				TSFacesUtil.getFacesContext().responseComplete();

			}

		} catch (Exception e) {

			throw new TSSystemException(e);

		} finally {

			ConnectionFactory.closeConnection(con);

		}

	}

	public void gerarRelatorioDireto(String jasper, Map<String, Object> parametros) {

		Connection con = null;

		try {

			con = ConnectionFactory.getConnection();

			jasper = TSFacesUtil.getServletContext().getRealPath(CAMINHO_RELATORIO + jasper);

			ExternalContext econtext = TSFacesUtil.getFacesContext().getExternalContext();

			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();

			response.setContentType("APPLICATION/PDF");

			response.setHeader("Content-Disposition", "inline");

			byte[] pdf = JasperRunManager.runReportToPdf(jasper, parametros, con);

			response.getOutputStream().write(pdf);

		} catch (Exception e) {

			throw new TSSystemException(e);

		} finally {

			ConnectionFactory.closeConnection(con);

		}

	}

	public <T extends Serializable> void gerarRelatorio(String jasper, Map<String, Object> parametros, List<T> lista) {

		try {

			jasper = TSFacesUtil.getServletContext().getRealPath(CAMINHO_RELATORIO + jasper);

			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(lista);

			JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, source);

			if (!TSUtil.isEmpty(impressao)) {

				String destino = Utilitario.getUsuarioLogado().getId() + "_" + TSUtil.gerarId() + ".pdf";

				RequestContext.getCurrentInstance().addCallbackParam("arquivoImpressao", destino);

				String caminhoCriacao = TSFacesUtil.getServletContext().getRealPath(File.separator + destino);

				File f = new File(caminhoCriacao);

				f.delete();

				JasperExportManager.exportReportToPdfFile(impressao, caminhoCriacao);

			}

		} catch (Exception e) {

			throw new TSSystemException(e);

		}

	}

	public void gerarRelatorioCaminhoCompletoExcel(String caminhoCompleto, Map<String, Object> parametros, String nomeArquivo) {

		Connection con = null;

		try {

			con = ConnectionFactory.getConnection();

			JasperPrint impressao = JasperFillManager.fillReport(caminhoCompleto, parametros, con);

			if (!TSUtil.isEmpty(impressao)) {

				String destino = nomeArquivo + ".xls";

				OutputStream output = null;

				parametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

				output = TSFacesUtil.getResponse().getOutputStream();

				TSFacesUtil.getResponse().setHeader("Content-Type", "application/vnd.ms-excel");
				TSFacesUtil.getResponse().setHeader("Content-Disposition", "attachment; filename=" + destino);

				JRXlsExporter exporter = new JRXlsExporter();
				
				exporter.setExporterInput(new SimpleExporterInput(impressao));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				
				configuration.setOnePagePerSheet(Boolean.FALSE);
				configuration.setDetectCellType(Boolean.TRUE);
				configuration.setCollapseRowSpan(Boolean.TRUE);
				configuration.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
				configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
				configuration.setWhitePageBackground(Boolean.FALSE);
				configuration.setIgnoreGraphics(Boolean.TRUE);
				
				exporter.setConfiguration(configuration);

				exporter.exportReport();

				TSFacesUtil.getFacesContext().renderResponse();
				TSFacesUtil.getFacesContext().responseComplete();

			}

		} catch (Exception e) {

			throw new TSSystemException(e);

		} finally {

			ConnectionFactory.closeConnection(con);

		}

	}

	public void gerarRelatorioRelatec(Long visaoId, Map<String, Object> parametros) {

		Connection con = null;

		try {

			con = ConnectionFactory.getConnection();

			JasperReport jasperReport = new br.com.relatec.util.JasperUtil().gerarRelatorioPorId(visaoId, parametros);

			JasperPrint impressao = JasperFillManager.fillReport(jasperReport, parametros, con);

			if (!TSUtil.isEmpty(impressao)) {

				String destino = Utilitario.getUsuarioLogado().getId() + ".pdf";

				String caminhoCriacao = TSFacesUtil.getServletContext().getRealPath(File.separator + destino);

				File f = new File(caminhoCriacao);

				f.delete();

				JasperExportManager.exportReportToPdfFile(impressao, caminhoCriacao);

				// TSFacesUtil.getResponse().sendRedirect(TSFacesUtil.getRequest().getContextPath()
				// + "/imprimir?arquivo=" + destino);

			}

		} catch (Exception e) {

			throw new TSSystemException(e);

		} finally {

			ConnectionFactory.closeConnection(con);

		}

	}

}
