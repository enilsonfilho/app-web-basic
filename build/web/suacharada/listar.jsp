<%@page import="br.com.interdisciplinar.model.Pessoa"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<%
    if (request.getSession(true).getAttribute("pessoa") != null) {
        Pessoa pessoa = (Pessoa) request.getSession(true).getAttribute("pessoa");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SUAUTORAL</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/datepicker3.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">

        <!-- Links do Modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    </head>
    <body>

        <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span></button>
                    <a class="navbar-brand" href="#"><span>SUA</span>UTORAL</a>

                </div>
            </div><!-- /.container-fluid -->
        </nav>
        <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
            <div class="profile-sidebar">
                <div class="profile-userpic">
                    <img src="img/logoMini.jpg" class="img-responsive" alt="">
                </div>
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name"> Olá ${pessoa.nomePessoa}! </div>
                    <div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="divider"></div>

            <ul class="nav menu">

                <!--<li class=""><a href="ListarPessoaTelaDeCadastro"><em class="fa fa-users">&nbsp;</em> Colaboradores</a></li>-->
                <li class=""><a href="ListarMusica"><em class="fa fa-music">&nbsp;</em> Músicas</a></li>    
                <li class=""><a href="ListarPoesia"><em class="fa fa-heart-o">&nbsp;</em> Poemas</a></li>
                <li class=""><a href="ListarFrase"><em class="fa fa-comments">&nbsp;</em> Frases</a></li>
                <li class=""><a href="ListarPiada"><em class="fa fa-users">&nbsp;</em> Piadas</a></li>
                <li class="active"><a href="ListarCharada"><em class="fa fa-object-group">&nbsp;</em> Charadas</a></li>
                    <%
                        if (pessoa.isAdm()) {
                    %>
                <li><a href="${pageContext.request.contextPath}/CarregarPessoa?idpessoa=${pessoa.idPessoa}"><em class="fa fa-user-o">&nbsp;</em> Perfil</a></li>
                    <%
                    } else {
                    %>            
                <li><a href="${pageContext.request.contextPath}/CarregarPessoa?idpessoa=${pessoa.idPessoa}"><em class="fa fa-user-o">&nbsp;</em> Perfil</a></li>
                    <%
                        }
                    %>
                <li><a href="${pageContext.request.contextPath}/LogarPessoa?acao=logout"  name="logout" method="POST"><em class="fa fa-reply-all">&nbsp;</em> Logout </a></li> 
            </ul>
        </div><!--/.sidebar-->

        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">
                            <em class="fa fa-home"></em>
                        </a></li>
                    <li class="active">Charadas</li>
                </ol>
            </div><!--/.row-->

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Publique a sua charada</h1>
                    <div class="descDeExclusao">
                        ${mensagem}
                    </div>
                </div>
            </div><!--/.row-->

           
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">

                            <button type="button" class="btn btn-default btn-lg" id="myBtn"> Nova charada</button>

                        </div>
                    </div>
                </div>
            </div><!--/.row-->
            

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Charadas

                            <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
                        <div class="panel-body table-responsive">
                            <table class="table table-bordered table-dark" id="tabela">
                                <thead>
                                    <tr>

                                        <th scope="col">Charada</th>
                                        <th scope="col">Autor</th>


                                        <%
                                            if (pessoa.isAdm()) {
                                        %>
                                        <th colspan="2" scope="col">Ações</th>
                                            <%
                                                }
                                            %>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="charada" items="${charadas}">
                                        <tr>
                                            <td scope="row">${charada.conteudo}</td> 
                                            <td scope="row">${charada.autor}</td> 
                                            <td scope="row"><fmt:formatDate type="date" value="${charada.data}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                                            <%
                                                if (pessoa.isAdm()) {
                                            %>
                                    <td><a href="${pageContext.request.contextPath}/ExcluirCharada?id=${charada.id}"> Excluir </a></td>
                                    <td><a type="button" data-toggle="modal" data-target="#${charada.id}" > Editar </a></td>  
                                    <%
                                        }
                                    %>

                                    </tr>
                                    <!-- Formulário Modal -->
                                    <div class="container">
                                        <div class="modal fade" id="${charada.id}" role="dialog">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4> <div id="tituloModal">Editar Charada</div></h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form name="cadastrar" action="${pageContext.request.contextPath}/SalvarCharada" role="form" method="post">
                                                            <div class="form-group">
                                                                <label for="usrname"> Id</label>
                                                                <input class="form-control" placeholder="Id" name="id" type="number" autofocus="" value="${charada.id}" data-ls-module="charCounter" readonly="" autocomplete="off">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="psw"> Conteúdo</label>
                                                                <input class="form-control" placeholder="Conteúdo..." name="conteudo" type="text" autofocus="" value="${charada.conteudo}" data-ls-module="charCounter" maxlength="200" required="" autocomplete="off">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="psw"> Autor</label>
                                                                <input class="form-control" placeholder="Autor..." name="autor" type="text" autofocus="" value="${charada.autor}" data-ls-module="charCounter" maxlength="50" required="" autocomplete="off">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="psw"> Data de Publicação</label>
                                                                <input class="form-control" placeholder="00/00/0000" name="data" type="date" autofocus="" value="${charada.data}" data-ls-module="charCounter" maxlength="50" required="" autocomplete="off">
                                                            </div>

                                                            <button type="submit" class="btn btn-lg btn-primary"> Alterar </button>
                                                            <button type="submit" class="btn btn-lg btn-warning" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>

                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">

                                                    </div>
                                                </div>
                                            </div>
                                        </div> 
                                    </div>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div><!--/.row-->

            <!-- Botões paginação -->
            <button class="btn btn-primary btn-sm" id="anterior" disabled>&lsaquo; Anterior</button>
            <span id="numeracao"></span>
            <button class="btn btn-primary btn-sm" id="proximo" disabled>Próximo &rsaquo;</button>

            <div class="col-sm-12">
                <p class="back-link">@<b>SUAUTORAL</b> Todos os Direitos Reservados! - Desenvolvido por Enilson Filho & Oseas de Lima</p>
            </div>
        </div><!-- /.row -->

        <!-- Formulário Modal -->
        <div class="container">
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4> <div id="tituloModal">Nova charada</div></h4>
                        </div>
                        <div class="modal-body">
                            <form name="cadastrar" action="${pageContext.request.contextPath}/SalvarCharada" role="form" method="post">
                                <div class="form-group">
                                    <label for="usrname"> Id</label>
                                    <input class="form-control" placeholder="Id" name="id" type="number" autofocus="" data-ls-module="charCounter" readonly="" autocomplete="off">
                                </div>

                                <div class="form-group">
                                    <label for="psw"> Conteúdo</label>
                                    <input class="form-control" placeholder="Conteúdo..." name="conteudo" type="text" autofocus="" data-ls-module="charCounter" maxlength="200" required="" autocomplete="off">
                                </div>

                                <div class="form-group">
                                    <label for="psw"> Autor</label>
                                    <input class="form-control" placeholder="Autor..." name="autor" type="text" autofocus="" data-ls-module="charCounter" maxlength="50" required="" autocomplete="off">
                                </div>

                                <div class="form-group">
                                    <label for="psw"> Data de Publicação</label>
                                    <input class="form-control" placeholder="00/00/0000" name="data" type="date" autofocus="" data-ls-module="charCounter" maxlength="50" required="" autocomplete="off">
                                </div>

                                <button type="submit" class="btn btn-lg btn-primary"> Cadastrar </button>
                                <button type="submit" class="btn btn-lg btn-warning" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>

                            </form>
                        </div>
                        <div class="modal-footer">

                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <!-- Script Modal -->                   
        <script>
            $(document).ready(function () {
                $("#myBtn").click(function () {
                    $("#myModal").modal();
                });
            });
        </script>

        <script src="js/complementos.js"></script>
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/chart.min.js"></script>
        <script src="js/chart-data.js"></script>
        <script src="js/easypiechart.js"></script>
        <script src="js/easypiechart-data.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/custom.js"></script>

        <!-- Paginação -->
        <script>
            var dados = [
            <c:forEach var="charada" items="${charadas}">
                ['${charada.conteudo}', 'De <b>${charada.autor}</b> publicada em <b>${charada.dataFormatada}</b>',
                        '<a href="${pageContext.request.contextPath}/ExcluirCharada?id=${charada.id}">Excluir</a> / <a type="button" data-toggle="modal" data-target="#${charada.id}" > Editar</a>'],
            </c:forEach>
            ];

            var tamanhoPagina = 4; //Número de elementos listados por página
            var pagina = 0; //Determina número de páginas

            function paginar() {
                $('table > tbody > tr').remove();
                var tbody = $('table > tbody');
                for (var i = pagina * tamanhoPagina; i < dados.length && i < (pagina + 1) * tamanhoPagina; i++) {
                    tbody.append(
                            $('<tr>')
                            .append($('<td>').append(dados[i][0]))
                            .append($('<td>').append(dados[i][1]))

            <%
                if (pessoa.isAdm()) {
            %>
                    .append($('<td>').append(dados[i][2]))

            <%
                }
            %>
                    );
                }
                $('#numeracao').text('Página ' + (pagina + 1) + ' de ' + Math.ceil(dados.length / tamanhoPagina));
            }

            function ajustarBotoes() {
                $('#proximo').prop('disabled', dados.length <= tamanhoPagina || pagina > dados.length / tamanhoPagina - 1);
                $('#anterior').prop('disabled', dados.length <= tamanhoPagina || pagina == 0);
            }

            $(function () {
                $('#proximo').click(function () {
                    if (pagina < dados.length / tamanhoPagina - 1) {
                        pagina++;
                        paginar();
                        ajustarBotoes();
                    }
                });
                $('#anterior').click(function () {
                    if (pagina > 0) {
                        pagina--;
                        paginar();
                        ajustarBotoes();
                    }
                });
                paginar();
                ajustarBotoes();
            });
        </script>
    </body>
</html>
<%
    } else {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
