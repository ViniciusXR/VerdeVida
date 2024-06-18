const req = document.getElementById('reqcards')
const url = 'http://localhost:8080/mesas'
var requisicoes;

async function getCardsArray() {
    const response = await fetch(url)
    requisicoes = await response.json()
    return requisicoes
}


async function printCards() {
    let reqs = await getCardsArray()
    let html = ''

    reqs.forEach((house) => {
        html += cardTemplate(house)
    })
    req.innerHTML = html
}

function cardTemplate(house) {
    let imovel
    
    if (house.requisicao != null) {
         imovel = `<div class="card">
      <h3>Mesa ${house.id}</h3>
      <small><b>Nome do Cliente: </b>${house.requisicao.cliente.nome}</small><br>
      <small><b>Capacidade: </b>${house.capacidade}</small><br>
      <small><b>Está cheia?</b> ${house.estaCheia}</small><br>
    </div>`
    }
    else {
         imovel = `<div class="card">
      <h3>Mesa ${house.id}</h3>
      <small><b>Nome do Cliente: </b></small><br>
      <small><b>Capacidade: </b>${house.capacidade}</small><br>
      <small><b>Está cheia?</b> ${house.estaCheia}</small><br>
    </div>`
    }
    return imovel;

}

//CHAMANDO AS FUNÇÕES
document.addEventListener('DOMContentLoaded', function () {
    printCards()
})