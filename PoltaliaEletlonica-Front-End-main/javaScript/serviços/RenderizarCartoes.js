import { exibirMensagemErro, montarTabela } from "./acessorios.js";
import { requisicaoGet } from "./centralRequisições.js";


export async function preencherTela() {
  try {


    let resultado = await requisicaoGet("/funcionario/listar");

    if (resultado.status == "s") {
      document.getElementById("sublista").innerText = "";
      document.getElementById("sublista").innerHTML = "";

      let dados = await resultado.conteudo.json();

      for (const item of dados) {

        let card = document.createElement("div");

        if (item.entrou == true) {
          card.classList.add("dentro");
        }
        card.classList.add("card");

        let elemento = '<section> <img src="' + item.urlPerfil + '" alt="foto de perfil"> </section>';
        elemento += '<section class="dados">';
        elemento += '  <p> nome: ' + item.nome + '</p>';
        elemento += ' <p> cargo: ' + item.cargo + '</p>';
        elemento += ' </section>';

        card.innerHTML = elemento;

        // card.addEventListener("click",  function (event) {
        //   metodoEntrarESair(item, this) 
        // })

        card.addEventListener("click", function (params) {
         
          montarTabela(this, item)
        });

        document.getElementById("sublista").appendChild(card);
      }
    }
    else {
      exibirMensagemErro(resultado.conteudo);
    }

  } catch (error) {
    console.log(error);
    exibirMensagemErro("Erro 500");

  }


}
