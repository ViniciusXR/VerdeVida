const req = document.getElementById('reqcards')
const url = 'http://localhost:8080/requisicoes'
var requisicoes;

async function getHousesArray() {
    const response = await fetch(url)
    requisicoes = await response.json()
    return requisicoes
}


async function printCards() {
    let reqs = await getHousesArray()
    let html = ''

    reqs.forEach((house) => {
        html += cardTemplate(house)
    })
    req.innerHTML = html
}

function cardTemplate(house) {

    let imovel = `<div class="card">
      <h3>${house.cliente}</h3>
      <small><b>ID da requisição:</b> ${house.id}</small><br>
      <small><b>Quantidade de Pessoas: </b>${house.quantidade_pessoas}</small><br>
      <small><b>Mesa:</b> ${house.mesa}</small><br>
    </div>`
    return imovel;

}

//CHAMANDO AS FUNÇÕES
document.addEventListener('DOMContentLoaded', function () {
    printCards()
})