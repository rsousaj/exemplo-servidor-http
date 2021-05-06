package com.zup.orangetalents;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;



/* Essa é uma classe que visa simular, didaticamente, o comportamento do Apache Tomcat (Container)
 * e os Servlets. 
 * 
 * O que tá escrito aqui são abstrações para facilitar o entendimento do funcionamento dos Servlets*/
public class Principal {
	
	/* Assim como esse programa de exemplo, o Tomcat também é uma aplicação escrita em Java e que 
	 * também tem o seu método main. 
	 * 
	 * Como sabemos, todo programa Java deve ter o seu método main. */
	
	public static void main(String[] args) throws IOException {
		/* Nesse trecho de código, nós configuramos para o nosso programa ficar ouvindo na porta 8080
		 * da nossa máquina (localhost).
		 * 
		 *  Assim como está sendo feito nesse programa de exemplo, o Tomcat também faz isso ao ser 
		 *  iniciado. Acontece que o Tomcat é uma aplicação robusta então as configurações necessárias
		 *  também são mais complexas. */
		HttpServer servidor = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
		
		/* Para fins didático, sugiro abstrair esse trecho de código */
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		
		/* Nesse trecho é configurado os contextos. Exatamente como acontece quando adicionamos a
		 * anotação @WebServlet( urlPatterns = "/1" ).  
		 * 
		 * No caso de exemplo, criei dois contextos e cada contexto é atendimento por um "Servlet" diferente.
		 * 
		 * ATENÇÃO: Não estamos usando Servlet de verdade aqui, é apenas uma simulação didática!!! */
		servidor.createContext("/1", new Servlet1());
		servidor.createContext("/2", new Servlet2());
		
		servidor.setExecutor(threadPoolExecutor);
		
		/* Nesse trecho de código nós iniciamos efetivamente o servidor e fazemos com que ele fique
		 * o tempo todo escutando as requisições que forem feitas em http://localhost:8080/ */
		System.out.println("Servidor sendo preparado pra escutar na porta 8080");
		servidor.start();
		System.out.println("Servidor iniciado na porta 8080");
	}
	
	/* As classes abaixo, apesar do nome, NÃO são Servlets. Utilizei os nomes apenas para fins 
	 * didáticos. */
	
	/* Esse é o Servlet1 resposável por toda requisição no endereço: http://localhost:8080/1 */
	
	static class Servlet1 implements HttpHandler {

		@Override
		public void handle(HttpExchange conexao) throws IOException {
			/* O importante abstrair aqui é que o Servlet1 é o responsável por atender a requisição
			 * em http://localhost:8080/1 e retornar o html com "Ola, eu sou o Servlet1" */
			
			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append("<html>")
				.append("<body>")
				.append("<p> Ola, eu sou o Servlet1 </p>")
				.append("</body>")
				.append("</html");
			
			String response = htmlBuilder.toString();
            conexao.sendResponseHeaders(200, response.length());
            OutputStream os = conexao.getResponseBody();
            os.write(response.getBytes());
            os.flush();
            os.close();
			
		}
	}
	
	/* Esse é o Servlet2 resposável por toda requisição no endereço: http://localhost:8080/2 */
	
	static class Servlet2 implements HttpHandler {

		@Override
		public void handle(HttpExchange conexao) throws IOException {
			/* O importante abstrair aqui é que o Servlet2 é o responsável por atender a requisição
			 * em http://localhost:8080/2 e retornar o html com "Ola, eu sou o Servlet2" */
			
			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append("<html>")
				.append("<body>")
				.append("<p> Ola, eu sou o Servlet2 </p>")
				.append("</body>")
				.append("</html");
			
			String response = htmlBuilder.toString();
            conexao.sendResponseHeaders(200, response.length());
            OutputStream os = conexao.getResponseBody();
            os.write(response.getBytes());
            os.flush();
            os.close();
			
		}
	}
}