import FuncionarioDTO from "./entidades/FuncionarioDTO.js";
import { exibirMensagemErro, exibirMensagemSucesso } from "./serviços/acessorios.js";
import { requisicaoPost } from "./serviços/centralRequisições.js";
import { preencherTela } from "./serviços/RenderizarCartoes.js";

document.getElementById("formCadastro").addEventListener('submit', async function (event) {
    event.preventDefault();
    let nome = document.getElementById("nome").value;
    let cargo = document.getElementById("cargo").value;
    let urlPerfil = document.getElementById("urlPerfil").value;

    const dados = new FuncionarioDTO(nome, cargo, urlPerfil);

    let resultado = await requisicaoPost(dados, "/funcionario/cadastrar");

    if (resultado.status == "s") {
        exibirMensagemSucesso(resultado.conteudo);

        preencherTela();
    } else {
        exibirMensagemErro(resultado.conteudo);
    }
})

document.getElementById("bcad").addEventListener("click",function(params) {
    document.getElementById("bcad").classList.add("selecionado")

    document.getElementById("bhora").classList.remove("selecionado")

    
    document.getElementById("formCadastro").style.display = "flex"
    document.getElementById("horarios").style.display = "none"



    
})

document.addEventListener("DOMContentLoaded", function() {
    preencherTela()
});

