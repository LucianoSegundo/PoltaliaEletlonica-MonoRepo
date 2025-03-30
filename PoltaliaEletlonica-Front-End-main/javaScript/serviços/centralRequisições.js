
export async function requisicaoPost(dto, endPoint) {
  try {


    const resposta = await fetch('http://localhost:8080' + endPoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(dto)
    });

    if (resposta.ok) {
      return {
        conteudo: "Cadastro Bem Sucedido",
        status: "s"
      }
    }
    else return {
      conteudo: "Cadastro Falhou",
      status: "f"
    }

  } catch (error) {
    return {
      conteudo: "Erro no sistema",
      status: "f"
    }
  }
}

export async function requisicaoGet(endPoint) {

  try {
    const resposta = await fetch('http://localhost:8080' + endPoint, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      },
    });

    if (resposta.ok) {
      
      return {
        conteudo: resposta,
        status: "s"
      }
    }
    else return {
      conteudo: "Consulta Falhou",
      status: "f"
    }

  } catch (error) {
    return {
      conteudo: "Erro no sistema",
      status: "f"
    }
  }

}
export async function requisicaoPut(endPoint) {
  try {
    const resposta = await fetch('http://localhost:8080' + endPoint, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
    });

    if (resposta.ok) {
      return {
        conteudo: "Mudança na Presença Bem Sucedida",
        status: "s"
      }
    }

    else return {
      conteudo: "Mudança na Presença Falhou",
      status: "f"
    }

  } catch (error) {
    return {
      conteudo: "Erro no sistema",
      status: "f"
    }
  }
}

