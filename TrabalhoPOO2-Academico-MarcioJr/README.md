# Sistemas Acadêmico Desenvolvido em JAVAFX

Este trabalho tem por objetivo aplicar os conceitos estudados sobre Elementos de Interface, Padrões de Projeto e Programação Distribuída.

Podemos dividi-lo em quatro etapas, a saber:

Projeto JavaFX MVC: CRUD 
Projeto JavaFX MVC: Gráfico
Projeto JavaFX MVC: Relatório
Projeto JavaFX MVC: Threads e Sockets (
Projeto JavaFX MVC: Web Services - RESTful JSON 
 

Etapa 1: Projeto JavaFX MVC: CRUD

Nesta etapa deve ser elaborado um projeto utilizando a plataforma de software multimídia JavaFX, o padrão de arquitetura de software MVC, o padrão de persistência de dados DAO e o padrão de instanciação de objetos Factory.

Para este desenvolvimento siga os passos:

Escolha um tema para o projeto;
Elabore o script de banco de dados;
Utilizaremos o banco de dados PostgreSQL;
Seu script deve conter todas as tabelas referentes ao domínio da aplicação;
Seu script deve conter a inserção de, pelo menos, dois registros para cada tabela;
Escolha uma entidade da categoria de manutenção de cadastros e implemente o CRUD;
Implemente as validações de acordo com os tipos de campos do banco de dados;
Escolha um processo de negócio e implemente sua inserção, alteração e remoção;
Implemente as validações de acordo com os tipos de campos do banco de dados;
Implemente as validações de acordo com as regras de negócio (pelo menos uma);
Obs.: para nivelar a complexidade dos processos de negócio entre todos os alunos, considere a implementação de, pelo menos, cinco transações com o banco de dados. Por exemplo, o Processo de Negócio Venda (Projeto Exemplo) possui, na inserção, cinco transações com o banco de dados: selecionar clientes; selecionar produtos, inserir venda, inserir itens de venda e alterar produto;
Etapa 2: Projeto JavaFX MVC: Gráfico

Nesta etapa deve ser elaborado um gráfico de barras utilizando o componente visual BarChar (ou outro tipo de gráfico qualquer).

Para este desenvolvimento siga os passos:

Escolha uma entidade do banco de dados (pode ser a entidade do seu CRUD);
Elabore um gráfico de barras simples (ou outro tipo de gráfico);
Seu gráfico pode possuir apenas uma série de dados;
 

Etapa 3: Projeto JavaFX MVC: Relatório

Nesta etapa deve ser elaborado um relatório utilizando o framework Jasper Reports, mais especificamente a ferramenta Jaspersoft Studio.

Para este desenvolvimento siga os passos:

Escolha uma entidade do banco de dados (pode ser o seu processo de negócio);
Elabore uma tela de listagem simples utilizando FXML;
Nesta tela insira um botão ‘Imprimir’ que, quando acionado, irá gerar o relatório;
Crie o layout (arquivo JRXML) do seu relatório utilizando a ferramenta Jaspersoft Studio;
Seu layout pode ser baseado em qualquer template disponível;
Seu relatório deve possuir: pelo menos uma função de agregação (count, sum, avg, etc.); e pelo menos uma junção entre tabelas (where, join, etc.);
Implemente a ação do botão ‘Imprimir’ para gerar o relatório;
Seu relatório deve ser gerado em uma nova tela;

Etapa 4: Projeto JavaFX MVC: Threads e Sockets

Nesta etapa devem ser adicionadas ao projeto funcionalidades relacionadas a programação concorrente (Threads) e a programação distribuída (Sockets).

Você deverá criar uma nova tela ao seu projeto. Esta tela deverá exibir notícias. As notícias serão obtidas, via socket, de uma aplicação servidora. O IP da máquina na qual a aplicação servidora está instalada é 35.199.121.110. A porta é 12345.

As notícias devem ser obtidas como objeto do tipo List<String>.

Na tela você deverá exibir uma notícia de cada vez. Cada notícia deve aparecer de 3 em 3 segundos (utilize Thread para isso).

Observe a figura para criar sua tela:
