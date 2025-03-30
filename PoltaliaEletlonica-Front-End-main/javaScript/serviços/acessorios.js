import { requisicaoGet, requisicaoPut } from "./centralRequisições.js";

export function exibirMensagemErro(mensagem) {
  document.getElementById("mensagemErro").style.display = "flex";

  document.getElementById("mensagemErro").innerHTML = "<h3>" + mensagem + "</h3>";

  setTimeout(function () {

    document.getElementById("mensagemErro").innerHTML = ""
    document.getElementById("mensagemErro").style.display = "none"
  }

    , "4000");
}

export function exibirMensagemSucesso(mensagem) {
  document.getElementById("mensagemSucesso").style.display = "flex";
  document.getElementById("mensagemSucesso").innerHTML = "<h3>" + mensagem + "</h3>";

  setTimeout(function () {

    document.getElementById("mensagemSucesso").innerHTML = ""
    document.getElementById("mensagemSucesso").style.display = "none"
  }

    , "4000");
}

export async function metodoEntrarESair(item, elemento) {
  if (item.entrou == false) {
    let resultado = await requisicaoPut("/funcionario/" + item.id + "/definir/entrada")
    if (resultado.status == "s") {


      elemento.classList.add("dentro")

      exibirMensagemSucesso(resultado.conteudo)
      item.entrou = true
    } else exibirMensagemErro(resultado.conteudo)

  }
  else if (item.entrou == true) {
    let resultado = await requisicaoPut("/funcionario/" + item.id + "/definir/saida")
    if (resultado.status == "s") {
      elemento.classList.remove("dentro")

      exibirMensagemSucesso(resultado.conteudo)
      item.entrou = false
    } else exibirMensagemErro(resultado.conteudo)

  }

}

export async function montarTabela(elemento, dadosElemento) {

  // coletando dados de entrada
  let resultado = await requisicaoGet("/funcionario/" + dadosElemento.id + "/dados");

  if (resultado.status == "s") {
    // Exibindo horarios

    document.getElementById("bcad").classList.remove("selecionado");

    document.getElementById("bhora").classList.add("selecionado");

    document.getElementById("formCadastro").style.display = "none";
    document.getElementById("horarios").style.display = "flex";

    // definindo nome da vitima

    document.getElementById("nomeTabela").innerText = dadosElemento.nome


    // configurando bão de entrar e sair

    let entrar = document.getElementById("entrar")
    entrar.style.display = "none"

    let sair = document.getElementById("sair")
    sair.style.display = "none"
    

      let sair2 = sair.cloneNode(true);

      sair.parentNode.replaceChild(sair2, sair);

      sair2.id = "sair"

    sair2.addEventListener("click", async function (params) {

      metodoEntrarESair(dadosElemento, elemento)

      this.style.display = "none"
      document.getElementById("entrar").style.display = "inline-block"

      let resultado2 = await requisicaoGet("/funcionario/" + dadosElemento.id + "/dados");

      if (resultado2.status == "s") {
        let dados2 = await resultado2.conteudo.json();
        console.log("chegou aqui sair")
        semideiapranome(dados2)
      }
      dadosElemento.entrou = false
    })


    let entrar2 = entrar.cloneNode(true);

    entrar.parentNode.replaceChild(entrar2, entrar);

    entrar2.id = "entrar"

    entrar2.addEventListener("click", async function (params) {


      metodoEntrarESair(dadosElemento, elemento)

      this.style.display = "none"
      document.getElementById("sair").style.display = "inline-block"

      let resultado2 = await requisicaoGet("/funcionario/" + dadosElemento.id + "/dados");

      if (resultado2.status == "s") {
        let dados2 = await resultado2.conteudo.json();
        console.log("chegou aqui entrar")

        semideiapranome(dados2)
      }

      dadosElemento.entrou = true

    })

    if (dadosElemento.entrou == false) {
      

      entrar2.style.display = "inline-block"


    }
    else if (dadosElemento.entrou == true) {

      sair2.style.display = "inline-block"

    }

    // preenchendo tabela
    let dados = await resultado.conteudo.json();
    semideiapranome(dados)


  } else if (resultado.status == "f") {

    document.getElementById("formCadastro").style.display = "flex"
    document.getElementById("horarios").style.display = "none"
    exibirMensagemErro("Algo deu errado ao consultar as informaçoes de entrada")
  }
}

function semideiapranome(dados) {
  let corpo = document.getElementById("corpoTabela")
  corpo.innerHTML = "";
  for (const item of dados) {
    corpo.innerHTML +=
      "<tr>"
      + "<td>" + item.indice + "</td>"
      + "<td>" + item.tipo + "</td>"
      + "<td>" + item.data + "</td>"
      + "<td>" + item.hora + "</td>"
      + "</tr>"
  }
}
