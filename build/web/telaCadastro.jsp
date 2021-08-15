<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SUAUTORAL</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/datepicker3.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <script type="text/javascript" src="js/1.5.2.js"></script>
        <script type="text/javascript" src="js/jquery.maskedinput-1.3.min.js"></script>
    </head>
    <body>

        <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading" align="center">Cadastro de usuário</div>
                <div class="panel-body">

                    <fieldset>
                        <form name="cadastrar" action="${pageContext.request.contextPath}/SalvarPessoa" method="POST">
                            <div class="form-group">
                                <input class="form-control" placeholder="Id" name="idpessoa" type="number" autofocus="" value="${pessoa.idPessoa}" data-ls-module="charCounter" readonly>
                            </div>
                            <div class="form-group">
                                <label for="psw"> Nome</label><br/>
                                <input class="form-control" placeholder="Nome" name="nomepessoa" type="text" autofocus="" value="${pessoa.nomePessoa}" data-ls-module="charCounter" maxlength="50" required="" autocomplete="off">
                            </div>
                            <div class="form-group">
                                <label for="psw"> Telefone</label><br/>
                                <input class="form-control" id="tel" placeholder="Telefone" name="telefonepessoa" type="text" value="${pessoa.telefonePessoa}"  size="60px" data-ls-module="charCounter" autocomplete="off">
                            </div>
                            <div class="form-group">
                                <label for="psw"> Celular</label><br/>
                                <input class="form-control" id="cel" placeholder="Celular" name="celularpessoa" type="text" value="${pessoa.celularPessoa}" data-ls-module="charCounter" required="" autocomplete="off">
                            </div>

                            <div class="form-group">
                                <label for="psw"> E-mail</label><br/>
                                <input class="form-control" placeholder="E-mail" name="loginpessoa" type="email" value="${pessoa.loginPessoa}" required="" autocomplete="off">
                            </div>
                            <div class="form-group">
                                <label for="psw"> Senha</label><br/>
                                <input class="form-control" placeholder="Senha" name="senhapessoa" type="password" value="${pessoa.senhaPessoa}" required="" autocomplete="off">
                            </div>
                            <div class="panel-body">

                                <input type="submit" type="button" class="btn btn-lg btn-primary" name="cadastrar" value="Cadastrar" />

                                <a class="btn btn-lg btn-warning" href="index.jsp">
                                    Cancelar
                                </a>

                            </div>
                            <p align="center">${mensagem}</p>
                        </form>
                </div>
            </div>
        </div><!-- /.col-->

        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/chart.min.js"></script>
        <script src="js/chart-data.js"></script>
        <script src="js/easypiechart.js"></script>
        <script src="js/easypiechart-data.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>
