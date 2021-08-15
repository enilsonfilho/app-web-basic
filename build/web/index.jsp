<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SUAUTORAL</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/datepicker3.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>

        <div class="panel-body">
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">

                <div id="logo"><p align="center"><img src="img/logo.fw.png" class="img-fluid img-thumbnail"></p></div>

                <form action="${pageContext.request.contextPath}/LogarPessoa" method="POST" name="logar" tabindex="1">

                    <div class="form-group">
                        <input class="form-control" placeholder="Ex: nome@gmail.com" name="login" type="email" required="" autofocus="" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <input class="form-control" placeholder="Senha..." name="senha" type="password" required="" autofocus="" autocomplete="off">
                    </div>

                    <input type="hidden" name="acao" value="logar"/>
                    <p align="center"><button type="submit"  name="logar" value="Entrar" class="form-contact-button">Entrar</button></p>

                </form>
                <div id="descLogin">${msglogar}</div>
            </div>
        </div><!-- /.col-->

        <div class="col-sm-12">
            <p class="back-link">@<b>SUAUTORAL</b> Todos os Direitos Reservados! - já possui <a href="ListarPessoaTelaDeCadastro"><b>cadastro?</b></a></p>
        </div>
    </body>
</html>