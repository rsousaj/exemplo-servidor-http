# exemplo-servidor-http

Pessoal, como tivemos um debate interessante acerca dos conceitos do Apache Tomcat (Container) e Servlets ontem, trouxe pra nosso debate esse experimento. Esse código simples usa alguns classes do pacote com.sun.net.httpserver para implementar, de maneira didática, como eu compreendo o funcionamento do Apache Tomcat.

O código é simples e está todo comentado. Minha intenção não é afirmar que esse protótipo está correto mas aprofundar ainda mais os nossos debates.

Para utilizar, basta clonar esse repositório e abrir como projeto no Eclipse. Caso dê alguma inconsistência, basta criar um projeto Java (não precisar ser Projeto Web, apenas um Projeto Java) e copiar o conteúdo da classe Principal.java

A classe Principal.java ao ser executada irá "criar" o nosso servidor, como se fosse o Tomcat, e aceitará requisições nos seguintes endereços: http://localhost:8080/1 e http://localhost:8080/1

Qualquer dúvida, vamos discutir lá pelo Chat!

Grande abraço!
